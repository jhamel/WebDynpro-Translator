package de.jhamel;

import de.jhamel.constants.AppConstants;
import de.jhamel.wdtranslator.XlfTranslator;

import java.util.Locale;
//import de.jhamel.csv.GermanZeroEnglishOne;

public class Translator {


    public void doMagic(String csvInputFile, Locale locale, String wdProjectDir, int defaultLangColumn, int translateLangColumn) throws Exception {
		XlfTranslator xlfTranslator = new XlfTranslator(wdProjectDir);
		xlfTranslator.translate(csvInputFile, locale, defaultLangColumn, translateLangColumn);
    }

    public static void main(String[] args) throws Exception {
        new Translator().doMagic(AppConstants.CSV_INPUT, AppConstants.CURRENT_LOCALE, AppConstants.WDDIR, AppConstants.DEFAULT_LANG_COLUMN, AppConstants.TRANSLATE_LANG_COLUMN);
    }
}
