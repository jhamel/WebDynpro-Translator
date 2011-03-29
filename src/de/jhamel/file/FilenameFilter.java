package de.jhamel.file;

import java.io.File;

/** Filters files based on filename */
public interface FilenameFilter {

    /** Skips file if true. */
    boolean skip(File file);
}
