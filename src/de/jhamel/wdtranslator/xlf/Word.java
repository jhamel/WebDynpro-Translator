package de.jhamel.wdtranslator.xlf;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

public class Word {

    // constants

    // fields

    private File file;

    private String key = "";
    private String text = "";
    private HashMap<Language, Word> translations = new HashMap<Language, Word>();

    // public methods

    public static String keyGen(Language language, String key) {
        return language + "_" + key;
    }

    public void addTranslation(Word word) {
        translations.put(word.getLanguage(), word);
    }

    public Word getTranslationByLanguage(Language language) {
        return translations.get(language);
    }

    public Language getLanguage() {
        return Language.languageOfFile(file);
    }

    public String getId() {
        return keyGen(getLanguage(), getKey());
    }

    // toString

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("language=").append(getLanguage()).append(",");
        builder.append("key=").append(getKey()).append(",");
        builder.append("text=").append(getText()).append(",");
        for (Language language : translatedLanguages()) {
            builder.append("[");
            builder.append(getTranslationByLanguage(language));
            builder.append("]");
        }
        return builder.toString();
    }

    // private methods

    private Set<Language> translatedLanguages() {
        return this.translations.keySet();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Word word = (Word) o;

        if (key != null ? !key.equals(word.key) : word.key != null) return false;
        if (translations != null ? !translations.equals(word.translations) : word.translations != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (translations != null ? translations.hashCode() : 0);
        return result;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void store() {
        new XlfFileValueReplacer().storeWord(this);
    }

    public String toCsv() {
        final String SEPERATOR = ";";
        StringBuilder builder = new StringBuilder();
        builder.append(getText()).append(SEPERATOR);
        for (Language language : translatedLanguages()) {
            builder.append(getTranslationByLanguage(language).getText()).append(SEPERATOR);
        }
        return builder.toString();
    }
}
