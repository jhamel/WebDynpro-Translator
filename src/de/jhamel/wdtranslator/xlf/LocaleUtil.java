package de.jhamel.wdtranslator.xlf;

import java.io.File;
import java.util.Locale;

public class LocaleUtil {

    public static Locale localeOfFile(File file) {
        if(file == null) return null;
        String localeAbbreviation = localeAbbreviationOfFile(file);
        if (localeAbbreviation.equals("")) return Locale.getDefault();
        return new Locale(localeAbbreviationOfFile(file));
    }

    private static String localeAbbreviationOfFile(File file) {
        String localeAbbreviation = "";
        String filename = file.getName();
        int posUnderscore = filename.lastIndexOf("_");
        int posDot = filename.lastIndexOf(".");
        if (hasUnderscoreBeforeDot(posUnderscore, posDot) && (posDot - posUnderscore) == 3) {
            localeAbbreviation = stringBetweenUnderscoreAndDot(filename, posUnderscore, posDot).toLowerCase();
        }
        return localeAbbreviation;
    }

    private static boolean hasUnderscoreBeforeDot(int posUnderscore, int posDot) {
        return posUnderscore > 0 && posUnderscore < posDot;
    }

    private static String stringBetweenUnderscoreAndDot(String filename, int posUnderscore, int posDot) {
        return filename.substring(posUnderscore + 1, posDot);
    }
}



