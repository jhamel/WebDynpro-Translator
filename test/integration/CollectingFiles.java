package integration;

import de.jhamel.file.EndsWithFilenameFilter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.file.FileUtil;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.junit.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static integration.FixtureConstants.BASEDIR;

public class CollectingFiles {

    @Test
    public void collectFilesEndingWithXLFWithDefaultLanguage() {
        XlfFileCollector xlfFileCollector = new XlfFileCollector();
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
        assertThat(xlfFileCollector.getWordsByLanguageKey(FileUtil.DEFAULT_LANGUAGE_KEY).size(), equalTo(52));
    }

    @Test
    public void collectFilesEndingWithXLFWithEnLanguage() {
        XlfFileCollector xlfFileCollector = new XlfFileCollector();
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
        assertThat(xlfFileCollector.getWordsByLanguageKey("en").size(), equalTo(53));
    }
}
