package integration;

import java.io.File;

public class FixtureConstants {
    public static final String BASEDIR = "./testdata/WebDynproProject".replaceAll("/", File.separator);
    public static final String SAMPLE_FILE = BASEDIR + "/src/packages/org/example/SampleComponentView.wdview.xlf".replaceAll("/", File.separator);
        public static final String SAMPLE_FILE_DE = BASEDIR + "/src/packages/org/example/SampleComponentView.wdview_de.xlf".replaceAll("/", File.separator);
}
