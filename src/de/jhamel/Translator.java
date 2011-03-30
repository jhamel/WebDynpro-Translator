package de.jhamel;

import de.jhamel.file.EndsWithFilenameFilter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.wdtranslator.xlf.*;

import java.util.List;

public class Translator {
    XlfFileCollector xlfFileCollector = new XlfFileCollector();
    private static final String BASEDIR = "../test/testdata";

    public void doMagic() {
        scanXlfFiles();
        List<Word> words = xlfFileCollector.words();
        for (Word word : words) {
            System.out.println(word);
        }

    }

    private void scanXlfFiles() {
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
    }



    public static void main(String[] args) {
        new Translator().doMagic();
    }
}
