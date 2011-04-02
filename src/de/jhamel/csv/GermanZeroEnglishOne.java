package de.jhamel.csv;

import de.jhamel.wdtranslator.xlf.LocaleUtil;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;

import java.util.Locale;

public class GermanZeroEnglishOne implements CsvLineProcessor {
    private static final String BASEDIR = "C:\\Dokumente und Einstellungen\\J19727\\.dtc\\2\\DCs\\eonis.com\\eea\\bpexd\\wd";

    private XlfFileCollector xlfFileCollector = new XlfFileCollector(BASEDIR);

    public GermanZeroEnglishOne() {
        xlfFileCollector.scanXlfFiles();
    }

    public void processLine(String[] line) {
        String germanDefault = line[0];
        String english = line[1];
        xlfFileCollector.replaceTranslationsForGivenDefaultWord(germanDefault, Locale.ENGLISH, english);
    }

}
