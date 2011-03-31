package de.jhamel.wdtranslator.xlf;

import java.io.File;

public class Language {

    // constants

    private static final String DEF = "def";
    private static final String UNDEF = "undefined";
    private static final String DE = "de";
    private static final String EN = "en";

    public static final Language DEFAULT = new Language(DEF);
    public static final Language GERMAN = new Language(DE);
    public static final Language ENGLISH = new Language(EN);
    public static final Language UNDEFINED = new Language(UNDEF);
    // fields

    private final String languageAbbreviation;

    // constructor

    public Language(String languageAbbreviation) {
        this.languageAbbreviation = languageAbbreviation;
    }

    public static Language getInstance(String languageAbbreviation) {
        if (languageAbbreviation.equalsIgnoreCase(DEF)) return DEFAULT;
        if (languageAbbreviation.equalsIgnoreCase(DE)) return GERMAN;
        if (languageAbbreviation.equalsIgnoreCase(EN)) return ENGLISH;
        return UNDEFINED;

    }

    public static Language languageOfFile(File file) {
        return getInstance(languageAbbreviationOfFile(file));
    }

    public String toString() {
        return languageAbbreviation;
    }

    // private

    private static String languageAbbreviationOfFile(File file) {
        if (file == null) return UNDEF;
        String filename = file.getName();
        int posUnderscore = filename.lastIndexOf("_");
        int posDot = filename.lastIndexOf(".");
        if (hasUndescoreBeforeDot(posUnderscore, posDot) && (posDot - posUnderscore) == 3) {
            return stringBetweenUnderscoreAndDot(filename, posUnderscore, posDot).toLowerCase();
        }
        return DEF;
    }

    private static boolean hasUndescoreBeforeDot(int posUnderscore, int posDot) {
        return posUnderscore > 0 && posUnderscore < posDot;
    }

    private static String stringBetweenUnderscoreAndDot(String filename, int posUnderscore, int posDot) {
        return filename.substring(posUnderscore + 1, posDot);
    }
}



