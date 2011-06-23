package de.jhamel.csv;

import de.jhamel.wdtranslator.xlf.Word;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;

import java.util.List;
import java.util.Locale;

public class NewLanguages implements CsvLineProcessor {

    private XlfFileCollector xlfFileCollector;

    public NewLanguages(XlfFileCollector xlfFileCollector) {
        this.xlfFileCollector = xlfFileCollector;
    }

    public void processLine(String[] line) {
        String germanDefault = line[0];
        String english = line[1];
        System.out.println("englisch: "+line[3]+",  french: "+line[4]+", dutch: "+line[5]);
        List<Word> wordsByLocale = xlfFileCollector.getWordsByLocale(Locale.GERMAN);
        for (Word word : wordsByLocale){
        }

        //xlfFileCollector.replaceTranslationsForGivenDefaultWord(germanDefault, Locale.ENGLISH, english);
    }

}
