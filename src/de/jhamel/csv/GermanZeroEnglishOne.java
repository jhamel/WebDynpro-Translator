package de.jhamel.csv;

import de.jhamel.wdtranslator.xlf.Language;
import de.jhamel.wdtranslator.xlf.Word;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;

import java.util.List;

public class GermanZeroEnglishOne implements CsvLineProcessor {
    private static final String BASEDIR = "../test/testdata";
    private XlfFileCollector xlfFileCollector = new XlfFileCollector(BASEDIR);

    public GermanZeroEnglishOne() {
        xlfFileCollector.scanXlfFiles();

    }

    public void processLine(String[] line) {
        String german = line[0];
        String english = line[1];
        List<Word> words = xlfFileCollector.getWordsByWord(german);
        for(Word word: words){
            Word wordEn = word.getTranslationByLanguage(Language.ENGLISH);
            wordEn.setText(english);
            word.store();
        }
    }
}
