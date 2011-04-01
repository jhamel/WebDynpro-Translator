package integration;

import de.jhamel.file.filters.EndsWithFilenameFilter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.wdtranslator.xlf.Language;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.junit.Test;

import static integration.FixtureConstants.BASEDIR;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class CollectingFiles {

    @Test
    public void collectFiles() {
        XlfFileCollector xlfFileCollector = new XlfFileCollector(FixtureConstants.BASEDIR);
        xlfFileCollector.scanXlfFiles();
        assertThat(xlfFileCollector.words().size(), equalTo(4));
    }
}
