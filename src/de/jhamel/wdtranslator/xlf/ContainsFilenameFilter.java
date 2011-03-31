package de.jhamel.wdtranslator.xlf;

import de.jhamel.file.FilenameFilter;

import java.io.File;

public class ContainsFilenameFilter implements FilenameFilter {
    private String contains;

    public ContainsFilenameFilter(String contains) {
        this.contains = contains;
    }

    public boolean skip(File file) {
        return file.getAbsolutePath().contains(contains);
    }
}
