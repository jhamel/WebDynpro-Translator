package de.jhamel;

import de.jhamel.constants.AppConstants;
import de.jhamel.wdtranslator.XlfTranslator;

import java.util.Locale;
//import de.jhamel.csv.GermanZeroEnglishOne;

public class Translator {


	/**
	 *
	 * @param csvInputFile
	 * @param locale
	 * @param wdProjectDir
	 * @param defaultLangColumn
	 * @param translateLangColumn
	 * @param charset
	 * @throws Exception
	 */
    public void doMagic(String csvInputFile, Locale locale, String wdProjectDir, int defaultLangColumn, int translateLangColumn,
						String charset) throws Exception {
		XlfTranslator xlfTranslator = new XlfTranslator(wdProjectDir);
		xlfTranslator.translate(csvInputFile, locale, defaultLangColumn, translateLangColumn, charset);
    }

    public static void main(String[] args) throws Exception {
        new Translator().doMagic(AppConstants.CSV_INPUT, AppConstants.CURRENT_LOCALE, AppConstants.WDDIR, AppConstants.DEFAULT_LANG_COLUMN, AppConstants.TRANSLATE_LANG_COLUMN,
				AppConstants.DEFAULT_CSV_CHARSET
				);
    }
}
