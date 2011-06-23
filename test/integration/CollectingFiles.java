package integration;

import de.jhamel.wdtranslator.xlf.XlfDirectoryTraverser;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CollectingFiles {

    @Test
    public void collectFiles() {
        XlfFileCollector xlfFileCollector = XlfDirectoryTraverser.collectWords(FixtureConstants.BASEDIR);
        assertThat(xlfFileCollector.words().size(), equalTo(4));
    }
}
