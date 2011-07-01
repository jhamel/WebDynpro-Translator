package de.jhamel;

import de.jhamel.constants.AppConstants;
import de.jhamel.wdtranslator.XlfTranslator;

import java.util.Locale;

public class Translator {

    public void translate(String csvInputFile, Locale locale, String wdProjectDir, int defaultLangColumn, int translateLangColumn, String charset) throws Exception {
        XlfTranslator xlfTranslator = new XlfTranslator(wdProjectDir);
        xlfTranslator.translate(csvInputFile, locale, defaultLangColumn, translateLangColumn, charset);
    }

}
