package integration;


import de.jhamel.wdtranslator.xlf.TransUnitToWordConverter;
import de.jhamel.wdtranslator.xlf.Word;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CollectingWords {
    private static final String SAMPLE_FILE = FixtureConstants.BASEDIR + "/" + "_comp/src/packages/com/eonis/eea/bpexd/wd/berichte/AusplattformShowDetails.wdview.xlf";

    @Test
    public void findKeys() {
        TransUnitToWordConverter converter = new TransUnitToWordConverter(new File(SAMPLE_FILE));
        List<Word> words = converter.convertTransUnitsToWords();
        assertEquals(9, words.size());
    }

}
