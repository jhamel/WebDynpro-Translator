package de.jhamel.wdtranslator.xlf;

import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class XlfFileCollectorTest {

    @Test
    public void processFile() {
        XlfFileCollector collector = new XlfFileCollector();
        collector.processFile(new File(FixtureConstants.SAMPLE_FILE));
        assertThat(collector.words().size(), equalTo(3));
    }

    @Test
    public void wordsByDefaultLocale() {
        XlfFileCollector collector = new XlfFileCollector();
        collector.processFile(new File(FixtureConstants.SAMPLE_FILE));
        assertThat(collector.getWordsByLocale(Locale.getDefault()).size(), equalTo(3));
    }

    @Test
    public void wordsByLocale() {
        Locale.setDefault(Locale.UK);
        XlfFileCollector collector = new XlfFileCollector();
        collector.processFile(new File(FixtureConstants.SAMPLE_FILE_DE));
        assertThat(collector.getWordsByLocale(Locale.GERMAN).size(), equalTo(3));
    }

    @Test
    public void wordsByLocaleGERMAN() {
        Locale.setDefault(Locale.UK);
        XlfFileCollector collector = XlfDirectoryTraverser.collectWords(FixtureConstants.BASEDIR);

        assertThat(collector.getWordsByLocale(Locale.GERMAN).size(), equalTo(3));
    }

    @Test
    public void translationLanguages() {
        Locale.setDefault(Locale.UK);
        XlfFileCollector collector = XlfDirectoryTraverser.collectWords(FixtureConstants.BASEDIR);
        assertThat(collector.translationLanguages().size(), equalTo(1));
    }

}
