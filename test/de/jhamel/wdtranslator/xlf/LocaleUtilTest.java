package de.jhamel.wdtranslator.xlf;

import org.junit.Test;

import java.io.File;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LocaleUtilTest {

    @Test
    public void languageOfFileMatchesFilenameEnding() {
        assertThat(LocaleUtil.localeOfFile(new File("abc.txt")), equalTo(Locale.getDefault()));
        assertThat(LocaleUtil.localeOfFile(new File("abc_de.txt")), equalTo(Locale.GERMAN));
        assertThat(LocaleUtil.localeOfFile(new File("abc_en.txt")), equalTo(Locale.ENGLISH));
    }

    @Test
    public void localizeFilename() {
        assertThat(LocaleUtil.localizeFilename("abc.txt",Locale.FRENCH), equalTo("abc_fr.txt"));
    }

}
