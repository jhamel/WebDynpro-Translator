package de.jhamel.wdtranslator.xlf;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.xpath.XPath;
import org.jdom.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XlfKeyWordCollector {

    // constants

    private static Logger log = Logger.getLogger(XlfKeyWordCollector.class);

    // fields

    private SAXBuilder builder = new SAXBuilder();

    // public methods

    public List<XlfWord> scanFile(File file) {
        List<XlfWord> xlfWords = new ArrayList<XlfWord>();
        try {
            Document doc = builder.build(file);
            List<Element> transUnitNodes = getTransUnitNodes(doc);
            if (transUnitNodes == null) return xlfWords;
            for (Element element : transUnitNodes) {
                XlfWord xlfWord = transUnitToXlfWord(file, element);
                if (xlfWord == null) {
                    log.warn("'" + file.getAbsoluteFile() + "' has 'trans-unit' element without 'id' or 'source'.");
                   break;
                }
                xlfWords.add(xlfWord);
            }
            return xlfWords;
        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return xlfWords;
    }

    //private methods

    private XlfWord transUnitToXlfWord(File file, Element element) {
        String id = elementAttributeValue(element, "id");
        String source = elementChildValue(element, "source");

        if (id == null || source == null) return null;

        XlfWord xlfWord = new XlfWord();
        xlfWord.setKey(id);
        xlfWord.setFile(file);
        xlfWord.setWord(source);

        return xlfWord;
    }

    private String elementChildValue(Element element, String name) {
       if (element == null) return null;
        if (element.getChild(name) == null) return null;
        return element.getChild(name).getValue();
    }

    private String elementAttributeValue(Element element, String attributeName) {
        if (element == null) return null;
        if (element.getAttribute(attributeName) == null) return null;
        return element.getAttribute(attributeName).getValue();
    }

    private List<Element> getTransUnitNodes(Document doc) throws JDOMException {
        XPath xpath = XPath.newInstance("//trans-unit");
        return xpath.selectNodes(doc);
    }
}
