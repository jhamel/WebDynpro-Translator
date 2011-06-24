package de.jhamel.file.filters;

import org.junit.Test;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContainsFilenameFilterTest {
    @Test
    public void filtersEverythingWithNullFilterString() {
        ContainsFilenameFilter filter = new ContainsFilenameFilter(null);
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void filtersEverythingWithEmptyFilterString() {
        ContainsFilenameFilter filter = new ContainsFilenameFilter("");
        assertThat(filter.skip(new File("sample.txt")), equalTo(true));
    }

    @Test
    public void doNotFilterIfNotContainsGivenString() {
        ContainsFilenameFilter filter = new ContainsFilenameFilter(File.separator + "bin" + File.separator);
        assertThat(filter.skip(new File("/sample.txt")), equalTo(false));
    }

    @Test
    public void doFilterIfContainsGivenString() {
        EndsWithFilenameFilter filter = new EndsWithFilenameFilter("bin");
        assertThat(filter.skip(new File(File.separator+"bin"+File.separator+"sample.doc")), equalTo(true));
        assertThat(filter.skip(new File("."+File.separator+"testdata"+File.separator+"WebDynproProject"+File.separator+"bin"+File.separator+"org"+File.separator+"example"+File.separator+"SampleComponentView.wdview.xlf")), equalTo(true));

    }

}
