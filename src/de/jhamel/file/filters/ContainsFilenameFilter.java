package de.jhamel.file.filters;

import java.io.File;

public class ContainsFilenameFilter implements FilenameFilter {
    private String contains;

    public ContainsFilenameFilter(String contains) {
        this.contains = contains;
    }

    public boolean skip(File file) {
        if (file == null) return true;
        if (contains == null) return true;
        if (contains.length() == 0) return true;
        return file.getAbsolutePath().contains(contains);
    }
}
