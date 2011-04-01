package de.jhamel.wdtranslator.xlf;

import org.junit.Test;

import java.io.File;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.equalToIgnoringCase;

public class LanguageTest {

    @Test
    public void getInstanceByAbbreviationForUnknownAbbreviationReturnsUndefinedLanguage() {
        assertThat(Language.getInstance("not a language"), equalTo(Language.UNDEFINED));
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

        @Test
    public void locale() {
        assertThat(Locale.GERMANY.getLanguage(), equalToIgnoringCase("de"));

    }
}
