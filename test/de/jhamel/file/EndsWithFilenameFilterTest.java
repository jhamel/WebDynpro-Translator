package de.jhamel.file;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class EndsWithFilenameFilterTest {

    @Test
    public void filtersEverythingWithNullFilterString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter(null);
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void filtersEverythingWithEmptyFilterString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter("");
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void doNotFilterIfEndsWithGivenString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter(".txt");
        assertThat(filter.skip(new File("sample.txt")), equalTo(false));
    }

    @Test
    public void doFilterIfNotEndsWithGivenString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter(".txt");
        assertThat(filter.skip(new File("sample.doc")), equalTo(true));
    }

}
