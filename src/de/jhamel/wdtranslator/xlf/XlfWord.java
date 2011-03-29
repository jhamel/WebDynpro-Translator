package de.jhamel.wdtranslator.xlf;

import de.jhamel.csv.CSVable;

import java.io.File;

public class XlfWord implements CSVable {

    // constants

    private static final String SEPERATOR = ";";

    // fields

    private File file;
    private String key = "";
    private String word = "";
    private XlfWord translation ;

    // public methods

    public String toCsv() {
        String trans = getTranslation() == null ? "" : SEPERATOR + getTranslation().getWord();
        return getKey() + SEPERATOR + getWord() + trans;
    }

    // getter + setter

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public XlfWord getTranslation() {
        return translation;
    }

    public void setTranslation(XlfWord translation) {
        this.translation = translation;
    }
}
