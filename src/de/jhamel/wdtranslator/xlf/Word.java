package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;

import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;

/** Word within in a XLF-file. */
public class Word {
    /** File within the word was found. */
    private File file;
    /** Value of xpath "//trans-unit[@id]" for the current word within te XLF-file */
    private String key = "";
    /** Value of xpath "//trans-unit/source" for the current word within te XLF-file */
    private String text = "";
    /** Translations tat were found */
    private HashMap<Locale, Word> translations = new HashMap<Locale, Word>();

    /** Generates unique id for word. */
    public static String generateUniqueIdentifier(Locale locale, String key) {
        return locale.getLanguage() + "_" + key;
    }

    public void addTranslation(Word word) {
        translations.put(word.getLanguage(), word);
    }

    public Word getTranslationByLocale(Locale locale) {
        return translations.get(locale);
    }

    public Locale getLanguage() {
        return LocaleUtil.localeOfFile(file);
    }

    public String getUniqueId() {
        return generateUniqueIdentifier(getLanguage(), getKey());
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("language=").append(getLanguage()).append(",");
        builder.append("key=").append(getKey()).append(",");
        builder.append("text=").append(getText()).append(",");
        for (Locale language : translatedLanguages()) {
            builder.append("[");
            builder.append(getTranslationByLocale(language));
            builder.append("]");
        }
        return builder.toString();
    }

    private Set<Locale> translatedLanguages() {
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
        if (file == null) throw new TechnicalException(this + " can not be stored, because it has no file");
        new XlfXmlHelper(getFile()).replaceValueOfSourceElement(getKey(), getText());
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
}
