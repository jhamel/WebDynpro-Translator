package de.jhamel.file.filters;

import java.io.File;

public class EndsWithFilenameFilter implements FilenameFilter {

    private String endsWith;

    public EndsWithFilenameFilter(String endsWith) {
        this.endsWith = endsWith;
    }

    public boolean skip(File file) {
        if (file == null) return true;
        if (endsWith == null) return true;
        if (endsWith.length() == 0) return true;

        return !file.getName().toLowerCase().endsWith(endsWith);
    }
}
