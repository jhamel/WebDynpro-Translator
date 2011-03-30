package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import java.io.IOException;
import java.util.List;

public class XlfFileValueReplacer {

    XMLOutputter outputter = new XMLOutputter();
    // fields

    private SAXBuilder builder = new SAXBuilder();

    public void storeWord(Word word) {
        try {
            Document doc = builder.build(word.getFile());
            XPath xpath = XPath.newInstance("//trans-unit[@id='" + word.getKey() + "']/source");
            List<Element> nodes = xpath.selectNodes(doc);
            if (nodes.size() != 1)
                throw new TechnicalException("Expected  one node for Word (" + word + "), but recieced " + nodes.size() + " nodes.");
            nodes.get(0).setText(word.getText());
            outputter.output(doc, System.out);
            //outputter.output(doc, new FileWriter(word.getFile()));

        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

}
