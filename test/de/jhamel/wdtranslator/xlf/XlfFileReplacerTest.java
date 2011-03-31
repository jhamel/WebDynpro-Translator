package de.jhamel.wdtranslator.xlf;

import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class XlfFileReplacerTest {
    private static final String SAMPLE_FILE = FixtureConstants.BASEDIR + "/" + "_comp/src/packages/com/eonis/eea/bpexd/wd/berichte/AusplattformShowDetails.wdview.xlf";

    @Test
    public void test() {
        XlfFileValueReplacer replacer = new XlfFileValueReplacer();
        WordCollector collector = new WordCollector();
        List<Word> words = collector.scanFile(new File(SAMPLE_FILE));
        Word word = words.get(0);
         replacer.storeWord(words.get(0));
        System.out.println(word);
        word.setText("new");
                System.out.println(word);
        replacer.storeWord(words.get(0));
    }

}
