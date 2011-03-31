package de.jhamel.wdtranslator.xlf;

import org.apache.log4j.Logger;
import org.jdom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WordCollector {

    // constants

    private static Logger log = Logger.getLogger(WordCollector.class);

    // public methods

    public List<Word> scanFile(File file) {
        XlfXmlHelper xlfXmlHelper = new XlfXmlHelper(file);
        List<Element> transUnitNodes = xlfXmlHelper.findTransUnitElements();

        List<Word> words = new ArrayList<Word>();
        if (transUnitNodes == null) return words;

        for (Element element : transUnitNodes) {
            Word word = convertTransUnitToWord(file, element);
            if (word == null) {
                log.warn("'" + file.getAbsoluteFile() + "' has 'trans-unit' element without 'id' or 'source'.");
                continue;
            }
            words.add(word);
        }
        return words;
    }

    //private methods

    private Word convertTransUnitToWord(File file, Element element) {
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

}
