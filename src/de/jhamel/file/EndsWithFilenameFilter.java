package de.jhamel.file;

import java.io.File;

public class EndsWithFilenameFilter implements FilenameFilter {

    // fields

    private String endsWith;

    // constructor

    public EndsWithFilenameFilter(String endsWith) {
        this.endsWith = endsWith;
    }

    // public methods

    public boolean skip(File file) {
        if (file == null) return true;
        if (endsWith == null) return true;
        if (endsWith.length() == 0) return true;

        return !file.getName().toLowerCase().endsWith(endsWith);
    }
}
