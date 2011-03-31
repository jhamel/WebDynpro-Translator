package de.jhamel;

import de.jhamel.csv.CsvReader;
import de.jhamel.csv.GermanZeroEnglishOne;

public class Translator {
    private static final String BASEDIR = "C:\\Dokumente und Einstellungen\\J19727\\.dtc\\2\\DCs\\eonis.com\\eea\\bpexd\\wd";

    public void doMagic() throws Exception {
        CsvReader csvReader = new CsvReader(new GermanZeroEnglishOne());
        csvReader.readFile("translation_20110330_VDE.csv");
    }

    public static void main(String[] args) throws Exception {
        new Translator().doMagic();
    }
}
