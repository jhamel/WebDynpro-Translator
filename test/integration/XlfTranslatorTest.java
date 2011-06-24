package integration;

import de.jhamel.wdtranslator.XlfTranslator;
import org.junit.Test;

import java.util.Locale;

public class XlfTranslatorTest {
    private static final String BASEDIR = "C:\\Dokumente und Einstellungen\\J19727\\.dtc\\0\\DCs\\eonis.com\\eea\\bpexd\\wd";
    public static final String CSV = "C:/Dokumente und Einstellungen/J19727/Desktop/jhamel-WebDynpro-Translator-126e5a2/translation_20110330_DE-EN-FR-NL_VDE.csv";

    @Test
    public void translate() {
        XlfTranslator xlfTranslator = new XlfTranslator(BASEDIR);
        xlfTranslator.translate(CSV, Locale.FRENCH, 0, 4, "UTF-8");


    }

}
