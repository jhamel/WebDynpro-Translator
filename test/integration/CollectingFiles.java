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
    public void collectFilesEndingWithXLFWithDefaultLanguage() {
        XlfFileCollector xlfFileCollector = new XlfFileCollector();
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
        assertThat(xlfFileCollector.getWordsByLanguageKey(Language.DEFAULT).size(), equalTo(52));
    }

    @Test
    public void collectFilesEndingWithXLFWithEnLanguage() {
        XlfFileCollector xlfFileCollector = new XlfFileCollector();
        TraverseDirectory traverseDirectory = new TraverseDirectory(BASEDIR, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.processFiles();
        assertThat(xlfFileCollector.getWordsByLanguageKey(Language.ENGLISH).size(), equalTo(53));
    }
}
