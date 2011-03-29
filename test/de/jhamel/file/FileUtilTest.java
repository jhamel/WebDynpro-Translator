package de.jhamel.file;


import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static de.jhamel.file.FileUtil.*;

public class  FileUtilTest {

    @Test
    public void returnLanguageKey() {
        assertThat(languageKeyForFile(new File("abc")), equalTo(DEFAULT_LANGUAGE_KEY));
        assertThat(languageKeyForFile(new File("abc_en.txt")),equalTo("en"));
        assertThat(languageKeyForFile(new File("abc_EN.txt")), equalTo("en"));
    }

    @Test
    public void returnAbsolutePathWithoutLanguageSuffix() {
        assertThat(removeLanguageSuffixOfAbsolutePath(new File("abc")), endsWith("abc"));
        assertThat(removeLanguageSuffixOfAbsolutePath(new File("abc.txt")), endsWith("abc"));
        assertThat(removeLanguageSuffixOfAbsolutePath(new File("abc_de.txt")), endsWith("abc"));
        assertThat(removeLanguageSuffixOfAbsolutePath(new File("en_.txt_abc_us.txt")), endsWith("en_.txt_abc"));
    }

}
