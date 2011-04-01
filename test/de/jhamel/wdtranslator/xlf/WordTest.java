package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import integration.FixtureConstants;
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
    public void shouldBeDefaultLanguage() {
        assertThat(Language.languageOfFile(new File("Menge_3NK.dtsimpletype.xlf")), equalTo(Language.DEFAULT));
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

    @Test(expected = TechnicalException.class)
    public void storingFileWithUnsetFileThrowsTechnicalException() {
        Word word = new Word();
        word.store();
    }

    @Test
    public void storingWord() {
        Word word = sampleWord();
        assertThat(word.getText(), equalTo("SampleComponentView"));
        word.setText("newText");
        word.store();

        word = sampleWord();
        assertThat(word.getText(), equalTo("newText"));

        word.setText("SampleComponentView");
        word.store();
        assertThat(word.getText(), equalTo("SampleComponentView"));

    }

    private Word sampleWord() {

        TransUnitToWordConverter converter = new TransUnitToWordConverter(new File(FixtureConstants.SAMPLE_FILE));
        return converter.convertTransUnitsToWords().get(0);
    }
}
