package de.jhamel.wdtranslator.xlf;

import de.jhamel.file.TraverseDirectory;
import de.jhamel.file.filters.ContainsFilenameFilter;
import de.jhamel.file.filters.EndsWithFilenameFilter;

import java.io.File;

public class XlfDirectoryTraverser {

    public static XlfFileCollector collectWords(String basedir) {

        XlfFileCollector xlfFileCollector = new XlfFileCollector();

        TraverseDirectory traverseDirectory = new TraverseDirectory(basedir, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.addFilenameFilter(new ContainsFilenameFilter(File.separator + "bin" + File.separator));
        traverseDirectory.processFiles();

        xlfFileCollector.words();

        return xlfFileCollector;
    }
}
