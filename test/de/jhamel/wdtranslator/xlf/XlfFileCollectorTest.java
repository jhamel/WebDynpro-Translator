package de.jhamel.wdtranslator.xlf;

import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class XlfFileCollectorTest {
    private static final String SAMPLE_FILE = FixtureConstants.BASEDIR + "/" + "_comp/src/packages/com/eonis/eea/bpexd/wd/berichte/AusplattformShowDetails.wdview.xlf";
    private static final String SAMPLE_FILE_EN = FixtureConstants.BASEDIR + "/" + "_comp/src/packages/com/eonis/eea/bpexd/wd/berichte/AusplattformShowDetails.wdview_en.xlf";

    @Test
    public void process() {
        XlfFileCollector collector = new XlfFileCollector();
        collector.processFile(new File(SAMPLE_FILE));
        assertThat(collector.words().size(), equalTo(9));
        assertThat(collector.getWordsByLanguageKey(Language.ENGLISH).size(), equalTo(0));
        collector.processFile(new File(SAMPLE_FILE_EN));
        assertThat(collector.words().size(), equalTo(9));
        System.out.println(collector.words());
        assertThat(collector.getWordsByLanguageKey(Language.ENGLISH).size(), equalTo(9));
        System.out.println(collector.words());
    }
}
