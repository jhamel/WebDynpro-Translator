package de.jhamel;


import de.jhamel.file.EndsWithFilenameFilter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.file.FileUtil;
import de.jhamel.wdtranslator.xlf.*;

import java.util.List;

public class Translator {
    XlfFileCollector xlfFileCollector = new XlfFileCollector();
    private static final String BASEDIR = "../test/testdata";

    public void doMagic() {
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
        List<XlfWord> wordsEn = xlfFileCollector.getWordsByLanguageKey("en");

        for (XlfWord xlfWord : xlfFileCollector.getWordsByLanguageKey(FileUtil.DEFAULT_LANGUAGE_KEY)) {
            xlfWord.setTranslation(findByKey(wordsEn, xlfWord.getKey()));
        }

        // CsvWriter.writeToCsvFile("translation.csv", wordsDef);

    }

    private XlfWord findByKey(List<XlfWord> xlfWords, String key) {
        for (XlfWord xlfWord : xlfWords) {
            if (xlfWord.getKey().equals(key)) {
                return xlfWord;
            }
        }
        return null;

    }


    public static void main(String[] args) {
        new Translator().doMagic();
    }
}
