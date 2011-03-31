package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class XlfFileValueReplacer {
    // constants

    private static Logger log = Logger.getLogger(XlfFileValueReplacer.class);

    XMLOutputter outputter = new XMLOutputter();
    // fields

    private SAXBuilder builder = new SAXBuilder();

    public void storeWord(Word word) {
        try {
            Document doc = builder.build(word.getFile());
            Element element = elementForWord(word, doc);

            String currentTextValueInFile = element.getText();
            String textWord = word.getText();

            log.trace("Try storing '" + currentTextValueInFile + "' to '" + textWord + "' in " + word.getFile().getAbsolutePath());
            if (currentTextValueInFile.equals(textWord)) {
                log.trace("No change, skip storing.");
                return;
            }
            log.debug("Storing '" + currentTextValueInFile + "' to '" + textWord + "' in " + word.getFile().getAbsolutePath());

            element.setText(textWord);

            // outputter.output(doc, System.out);
           // outputter.output(doc, new FileWriter("C:\\xlf\\" + word.getFile().getName()));
            outputter.output(doc, new FileWriter(word.getFile()));

        } catch (JDOMException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    private Element elementForWord(Word word, Document doc) throws JDOMException {
        XPath xpath = XPath.newInstance("//trans-unit[@id='" + word.getKey() + "']/source");

        List<Element> nodes = xpath.selectNodes(doc);
        if (nodes.size() != 1)
            throw new TechnicalException("Expected  one node for Word (" + word + "), but recieved " + nodes.size() + " nodes.");
        return nodes.get(0);
    }

}
