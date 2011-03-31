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

public class WordCollector {

    // constants

    private static Logger log = Logger.getLogger(WordCollector.class);

    // fields

    private SAXBuilder builder = new SAXBuilder();

    // public methods

    public List<Word> scanFile(File file) {
        List<Word> words = new ArrayList<Word>();
        try {
            Document doc = builder.build(file);
            List<Element> transUnitNodes = getTransUnitNodes(doc);
            if (transUnitNodes == null) return words;
            for (Element element : transUnitNodes) {
                Word word = transUnitToXlfWord(file, element);
                if (word == null) {
                    log.warn("'" + file.getAbsoluteFile() + "' has 'trans-unit' element without 'id' or 'source'.");
                   break;
                }
                words.add(word);
            }
            return words;
        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        return words;
    }

    //private methods

    private Word transUnitToXlfWord(File file, Element element) {
        String id = elementAttributeValue(element, "id");
        String source = elementChildValue(element, "source");

        if (id == null || source == null) return null;

        Word word = new Word();
        word.setKey(id);
        word.setFile(file);
        word.setText(source);

        return word;
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
