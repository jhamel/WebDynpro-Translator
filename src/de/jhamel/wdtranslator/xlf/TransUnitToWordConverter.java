package de.jhamel.wdtranslator.xlf;

import org.apache.log4j.Logger;
import org.jdom.Element;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransUnitToWordConverter {

    private static Logger log = Logger.getLogger(TransUnitToWordConverter.class);

    private File file;
    private XlfXmlHelper xlfXmlHelper;

    public TransUnitToWordConverter(File file) {
        this.file = file;
        xlfXmlHelper = new XlfXmlHelper(file);
    }

    public List<Word> convertTransUnitsToWords() {
        List<Word> words = new ArrayList<Word>();

        List<Element> transUnitNodes = xlfXmlHelper.findTransUnitElements();
        if (transUnitNodes == null) return words;

        for (Element element : transUnitNodes) {
            Word word = convertTransUnitToWord(element);
            if (word == null) {
                log.warn("'" + file.getAbsoluteFile() + "' has 'trans-unit' element without 'id' or 'source'.");
                continue;
            }
            words.add(word);
        }
        return words;
    }

    public Word convertTransUnitToWord(Element element) {
        if (element == null) return null;
        if (element.getAttribute("id") == null) return null;
        if (element.getChild("source") == null) return null;

        String id = element.getAttribute("id").getValue();
        String source = element.getChild("source").getValue();

        if (id == null || source == null) return null;

        Word word = new Word();
        word.setKey(id);
        word.setFile(file);
        word.setText(source);

        return word;
    }

}