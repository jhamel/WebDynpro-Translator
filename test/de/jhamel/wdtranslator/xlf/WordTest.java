package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;
import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

public class WordTest {

    @Test
    public void shouldBeDefaultLanguage() {
        assertThat(LocaleUtil.localeOfFile(new File("Menge_3NK.dtsimpletype.xlf")), equalTo(Locale.getDefault()));
    }

    @Test
    public void uniqueId() {
        Word word = new Word();
        word.setKey("key");
        word.setLanguage(Locale.GERMAN);
        word.setFile(new File("abc_de.txt"));
        assertThat(word.getUniqueId(), equalTo("de_key"));
    }

    @Test
    public void translationByLanguage() {
        Word wordEn = new Word();
        wordEn.setKey("keyEn");
        wordEn.setText("textEn");
        wordEn.setLanguage(Locale.ENGLISH);
        wordEn.setFile(new File("abc_en.txt"));

        Word word = new Word();
        word.setFile(new File("abc.txt"));
        wordEn.setLanguage(Locale.ENGLISH);

        word.addTranslation(wordEn);

        assertThat(word.getTranslationByLocale(Locale.ENGLISH).getKey(), equalTo("keyEn"));


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

    @Test
    public void storeTranslation() {
        Word word = mock(Word.class);
        word.setText("abc");
        word.setFile(new File("abc.txt"));
        word.setLanguage(Locale.GERMAN);

        Word wordFr = mock(Word.class);
        wordFr.setText("le abc");
        wordFr.setFile(new File("abc_fr.txt"));
        wordFr.setLanguage(Locale.FRENCH);

        word.store();

        verify(word, times(1)).store();
        verify(wordFr, times(1)).store();
    }


    @Test
    public void storingWordTranslation() {
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

    @Test
    public void wordToCsv() {
        Word word = new Word();
        assertThat(word.toCsv(), equalTo(""));
        word.setText("default");
        assertThat(word.toCsv(), equalTo("default"));

        Word wordEn = new Word();
        wordEn.setText("translation");
        word.addTranslation(wordEn);

        assertThat(word.toCsv(), equalTo("default;translation;"));

    }

    @Test
    public void addTranslation() {
        Word word = new Word();
        word.setFile(new File("file.xlf"));
        word.setKey("key");
        word.setText("word");
        word.addTranslation(Locale.FRENCH, "le word");

        assertThat(word.getTranslationByLocale(Locale.FRENCH).getKey(), equalTo("key"));
        assertThat(word.getTranslationByLocale(Locale.FRENCH).getText(), equalTo("le word"));
        assertThat(word.getTranslationByLocale(Locale.FRENCH).getFile().getName(), equalTo("file_fr.xlf"));


    }


    private Word sampleWord() {
        File sampleFile = new File(FixtureConstants.SAMPLE_FILE);
        assertThat(sampleFile.exists(), equalTo(true));

        TransUnitToWordConverter converter = new TransUnitToWordConverter(sampleFile);
        return converter.convertTransUnitsToWords().get(0);
    }
}
