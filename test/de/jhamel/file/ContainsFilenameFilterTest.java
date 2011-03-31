package de.jhamel.file;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContainsFilenameFilterTest {
    @Test
    public void filtersEverythingWithNullFilterString() {
         ContainsFilenameFilter filter = new  ContainsFilenameFilter(null);
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void filtersEverythingWithEmptyFilterString() {
         ContainsFilenameFilter filter = new  ContainsFilenameFilter("");
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void doNotFilterIfEndsWithGivenString() {
         ContainsFilenameFilter filter = new  ContainsFilenameFilter(File.pathSeparator+"bin"+File.pathSeparator);
        assertThat(filter.skip(new File("/sample.txt")), equalTo(false));
    }

    @Test
    public void doFilterIfNotEndsWithGivenString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter("bin");
        assertThat(filter.skip(new File("/bin/sample.doc")), equalTo(true));
    }

}
