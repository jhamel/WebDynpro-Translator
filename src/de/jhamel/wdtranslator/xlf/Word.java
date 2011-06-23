package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/**
 * Word within in a XLF-file. It´s not just one word but a whole phrase<br/>
 * Each phrase has either a parent, if it´ a translation or a list of translations that are
 * also Word objects
 *
 */
public class Word {

    // fields

    /**
     * Word that this word is a translation of
     */
    private Word parent;

    /**
     * File within the word was found.
     */
    private File file;

    /**
     * Value of xpath "//trans-unit[@id]" for the current word within te XLF-file
     */
    private String key = "";

    /**
     * Value of xpath "//trans-unit/source" for the current word within te XLF-file
     */
    private String text = "";

    /**
     * Language of word
     */
    private Locale language;

    /**
     * Translations tat were found
     */
    private HashMap<Locale, Word> translations = new HashMap<Locale, Word>();

    // public methods

    /**
     * Generates unique id for word.
     */
    public static String generateUniqueIdentifier(Locale locale, String key) {
        return locale.getLanguage() + "_" + key;
    }


    public void addTranslation(Word word) {
        word.setParent(this);
        translations.put(word.getLanguage(), word);
    }

    public boolean isTranslation() {
        return parent != null;
    }

    public Word getTranslationByLocale(Locale locale) {
        return translations.get(locale);
    }

    public String getUniqueId() {
        return generateUniqueIdentifier(getLanguage(), getKey());
    }

    public void store() {
        createFileBasedOnDefaultFile();
        translateWordInFile();
        storeTranslations();
    }

    public String toCsv() {
        final String CSV_SEPERATOR = ";";
        StringBuilder builder = new StringBuilder();
        builder.append(getText());
        if (translatedLanguages().size() > 0) builder.append(CSV_SEPERATOR);
        for (Locale language : translatedLanguages()) {
            builder.append(getTranslationByLocale(language).getText()).append(CSV_SEPERATOR);
        }
        return builder.toString();
    }


    public void addTranslation(Locale language, String translation) {
        Word word = new Word();
        word.setText(translation);
        word.setKey(getKey());
        word.setLanguage(language);
        String localizedFilename = LocaleUtil.localizeFilename(getFile().getAbsolutePath(), language);
        word.setFile(new File(localizedFilename));

        addTranslation(word);
    }

    // private

    private Set<Locale> translatedLanguages() {
        return this.translations.keySet();
    }


    private void storeTranslations() {
        for (Word word : translations.values()) {
            word.store();
        }
    }

    private boolean isNonExistingTranslationFile() {
        return !file.exists() && isTranslation();
    }

    private void translateWordInFile() {
        if(getText().equals("W�hrung") && getFile().getAbsolutePath().startsWith("AufmasseMe")){
                                          int a = 0;
        }
        new XlfXmlHelper(getFile()).replaceValueOfSourceElement(getKey(), getText());
    }

    private void createFileBasedOnDefaultFile() {
        if (isNonExistingTranslationFile()) {
            copyParentFileToThisFile();
        }
    }

    private void copyParentFileToThisFile() {
        try {
            FileUtils.copyFile(getParent().getFile(), getFile());
        } catch (IOException e) {
            throw new TechnicalException("Could not copy file '" + getParent().getFile() + "' to '" + getFile() + "'", e);
        }
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

    // toString

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("language=").append(getLanguage()).append(",");
        builder.append("key=").append(getKey()).append(",");
        builder.append("text=").append(getText()).append(",");
        builder.append("file=").append(getFile()).append(",");
        for (Locale language : translatedLanguages()) {
            builder.append("[");
            builder.append(getTranslationByLocale(language));
            builder.append("]\n");
        }
        return builder.toString();
    }

    // getter + setter

    public Word getParent() {
        return parent;
    }

    public void setParent(Word parent) {
        this.parent = parent;
    }

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

    public void setLanguage(Locale language) {
        this.language = language;
    }

    public Locale getLanguage() {
        if (language == null) setLanguage(LocaleUtil.localeOfFile(getFile()));
        return language;
    }
}
