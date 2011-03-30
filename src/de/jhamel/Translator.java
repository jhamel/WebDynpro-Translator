package de.jhamel;

import de.jhamel.csv.CsvReader;
import de.jhamel.csv.GermanZeroEnglishOne;
import de.jhamel.file.EndsWithFilenameFilter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.wdtranslator.xlf.*;

import java.util.List;

public class Translator {
    private static final String BASEDIR = "../test/testdata";
    private XlfFileCollector xlfFileCollector = new XlfFileCollector(BASEDIR);

    public void doMagic() {
        xlfFileCollector.scanXlfFiles();
        CsvReader csvReader = new CsvReader("file.csv",new GermanZeroEnglishOne());



        List<Word> words = xlfFileCollector.words();

        for (Word word : words) {
            System.out.println(word);
        }

    }



    public static void main(String[] args) {
        new Translator().doMagic();
    }
}
