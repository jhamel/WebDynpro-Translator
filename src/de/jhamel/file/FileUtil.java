package de.jhamel.file;

import java.io.File;


public class FileUtil {
    public static final String DEFAULT_LANGUAGE_KEY = "default_lang".toLowerCase();

    public static String removeLanguageSuffixOfAbsolutePath(File file) {
        String absolutePath = file.getAbsolutePath();
        int indexOfLanguageSuffix = absolutePath.lastIndexOf("_" + languageKeyForFile(file));
        int indexOfLastDot = absolutePath.lastIndexOf(".");
        if(indexOfLastDot<0) return absolutePath;
        if (indexOfLanguageSuffix > 0) {
            return absolutePath.substring(0, indexOfLanguageSuffix);
        }
        return absolutePath.substring(0, indexOfLastDot);
    }

    public static String languageKeyForFile(File file) {
        String filename = file.getName();
        int posUnderscore = filename.lastIndexOf("_");
        int posDot = filename.lastIndexOf(".");
        if (hasUndescoreBeforeDot(posUnderscore, posDot)) {
            return stringBetweenUnderscoreAndDot(filename, posUnderscore, posDot).toLowerCase();
        }
        return DEFAULT_LANGUAGE_KEY;
    }

    private static boolean hasUndescoreBeforeDot(int posUnderscore, int posDot) {
        return posUnderscore > 0 && posUnderscore < posDot;
    }

    // private

    private static String stringBetweenUnderscoreAndDot(String filename, int posUnderscore, int posDot) {
        return filename.substring(posUnderscore + 1, posDot);
    }
}
