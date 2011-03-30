package de.jhamel.wdtranslator.xlf;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class WordTest {

    @Test
    public void language() {
        Word word = new Word();
        word.setFile(new File("abc_de.txt"));
        assertThat(word.getLanguage(), equalTo(Language.GERMAN));
    }

    @Test
    public void keyGen() {
        Word word = new Word();
        word.setKey("key");
        word.setFile(new File("abc_de.txt"));
        assertThat(word.getLanguage(), equalTo(Language.GERMAN));
    }

    @Test
    public void translationByLanguage() {
        Word wordEn = new Word();
        wordEn.setKey("keyEn");
        wordEn.setText("textEn");
        wordEn.setFile(new File("abc_en.txt"));

        Word word = new Word();
        word.setFile(new File("abc.txt"));
        word.addTranslation(wordEn);

        assertThat(word.getTranslationByLanguage(Language.ENGLISH).getKey(), equalTo("keyEn"));

        System.out.println(word);
    }
}
