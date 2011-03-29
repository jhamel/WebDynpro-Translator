package integration;


import de.jhamel.wdtranslator.xlf.XlfKeyWordCollector;
import de.jhamel.wdtranslator.xlf.XlfWord;
import integration.FixtureConstants;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class CollectingWords {
    private static final String SAMPLE_FILE = FixtureConstants.BASEDIR + "/" + "_comp/src/packages/com/eonis/eea/bpexd/wd/berichte/AusplattformShowDetails.wdview.xlf";

    @Test
    public void findKeys() {
        XlfKeyWordCollector collector = new XlfKeyWordCollector();
        List<XlfWord> xlfWords = collector.scanFile(new File(SAMPLE_FILE));
        assertEquals(9, xlfWords.size());
    }

}
