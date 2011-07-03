package integration;


import de.jhamel.wdtranslator.xlf.TransUnitToWordConverter;
import de.jhamel.wdtranslator.xlf.Word;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CollectingWords {

    @Test
    public void findKeys() {
        TransUnitToWordConverter converter = new TransUnitToWordConverter(new File(FixtureConstants.SAMPLE_FILE));
        List<Word> words = converter.convertTransUnitsToWords();
        assertEquals(3, words.size());
    }

    @Test
    public void handleQuotes() {
        TransUnitToWordConverter converter = new TransUnitToWordConverter(new File(FixtureConstants.XLF_FILE_WITH_QUOTES));
        List<Word> words = converter.convertTransUnitsToWords();
        assertEquals("&quot;Sample&quot;",words.get(0).getText());
    }



}
