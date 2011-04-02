package de.jhamel.wdtranslator.xlf;

import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class XlfFileCollectorTest {


    @Test
    public void process() {
        XlfFileCollector collector = new XlfFileCollector();
        collector.processFile(new File(FixtureConstants.SAMPLE_FILE));
        assertThat(collector.words().size(), equalTo(3));
        assertThat(collector.getWordsByLanguageKey(Locale.GERMAN).size(), equalTo(0));
        collector.processFile(new File(FixtureConstants.SAMPLE_FILE_DE));
        assertThat(collector.words().size(), equalTo(3));
        System.out.println(collector.words());
        assertThat(collector.getWordsByLanguageKey(Locale.GERMAN).size(), equalTo(3));
        System.out.println(collector.words());
    }
}
