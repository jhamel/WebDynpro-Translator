package de.jhamel.wdtranslator.xlf;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LanguageTest {

    @Test(expected = IllegalArgumentException.class)
    public void getInstanceByAbbreviationForUnknownAbbreviationThrowsIllegalArgumentException() {
        Language.getInstance("not a language");
    }

    @Test
    public void getInstanceByAbbreviationReturnsLanguage() {
        assertThat(Language.getInstance("de"), equalTo(Language.GERMAN));
        assertThat(Language.getInstance("en"), equalTo(Language.ENGLISH));
        assertThat(Language.getInstance("def"), equalTo(Language.DEFAULT));
    }

    @Test
    public void getInstanceByAbbreviationIgnoresCase() {
        assertThat(Language.getInstance("de"), equalTo(Language.getInstance("DE")));
    }

    @Test
    public void languageOfFileMatchesFilenameEnding() {
        assertThat(Language.languageOfFile(new File("abc.txt")), equalTo(Language.DEFAULT));
        assertThat(Language.languageOfFile(new File("abc_de.txt")), equalTo(Language.GERMAN));
        assertThat(Language.languageOfFile(new File("abc_en.txt")), equalTo(Language.ENGLISH));
    }
}
