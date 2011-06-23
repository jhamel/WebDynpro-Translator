package de.jhamel.file;

import de.jhamel.file.filters.FilenameFilter;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Process recursively all files of a given base directory.
 * Processing of files is handled by a FileProcessor.
 * Files that should be ignored can be filtered by FilenameFilters.
 * @see FileProcessor
 * @see de.jhamel.file.filters.FilenameFilter
 */
public class TraverseDirectory {

    private static Logger log = Logger.getLogger(TraverseDirectory.class);

    private final String basedir;
    private FileProcessor fileProcessor;
    private List<FilenameFilter> filenameFilters = new ArrayList<FilenameFilter>();

    /** @throws IllegalArgumentException in case basedir is not an existing directory */
    public TraverseDirectory(String basedir, FileProcessor fileProcessor) {
        checkIsDirectory(new File(basedir)); // fail early
        log.debug("Instantiating TraverseDirectory with basedir='" + basedir + "' and FileProcessor='" + fileProcessor.getClass() + "'");
        this.basedir = basedir;
        this.fileProcessor = fileProcessor;
    }

    public void processFiles() {
        traverseChildren(new File(basedir));
    }

    public void addFilenameFilter(FilenameFilter filenameFilter) {
        log.debug("Adding FilenameFilter of type '" + filenameFilter.getClass() + "'.");
        filenameFilters.add(filenameFilter);
    }

    private void checkIsDirectory(File dir) {
        if (!dir.isDirectory()) {
            String message = "'" + dir.getAbsoluteFile() + "' is not a directory.";
            log.error("'" + dir.getAbsoluteFile() + "' is not a directory.");
            throw new IllegalArgumentException(message);
        }
    }

    private void traverseChildren(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                traverseChildren(child); // recursion!!
            }
        } else {
            if (notFiltered(file)) fileProcessor.processFile(file);
        }
    }

    private boolean notFiltered(File file) {
        return !isFiltered(file);
    }

    private boolean isFiltered(File file) {
        for (FilenameFilter filter : filenameFilters) {
            if (filter.skip(file)) return true;
        }
        return false;
    }

}
