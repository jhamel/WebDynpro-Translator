package de.jhamel.file.filters;

import java.io.File;

public interface FilenameFilter {
    boolean skip(File file);
}
