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

}
