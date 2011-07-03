package integration;

import java.io.File;

public class FixtureConstants {
    public static final String BASEDIR = "./testdata/WebDynproProject".replace("/", File.separator);
    public static final String CSV_FILE_WITH_GERMAN_UMLAUTE = "." + File.separator + "testdata" + File.separator + "file_with_german_umlaute.csv";
    public static final String CSV_FILE_TRANSLATION_PROBLEM1 = "." + File.separator + "testdata" + File.separator + "translation_problem1.csv";
        public static final String XLF_FILE_WITH_QUOTES = "." + File.separator + "testdata" + File.separator + "XLF_file_with_quotes.xlf";
    public static final String SAMPLE_FILE = BASEDIR + "/src/packages/org/example/SampleComponentView.wdview.xlf".replace("/", File.separator);
    public static final String SAMPLE_FILE_DE = BASEDIR + "/src/packages/org/example/SampleComponentView.wdview_de.xlf".replace("/", File.separator);
}
