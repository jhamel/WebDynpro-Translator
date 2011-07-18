package integration;

import de.jhamel.constants.AppConstants;
import de.jhamel.csv.CsvLineProcessor;
import de.jhamel.csv.CsvLogger;
import de.jhamel.csv.CsvReader;
import de.jhamel.wdtranslator.XlfTranslator;
import org.junit.Test;
import org.mockito.cglib.core.Local;
import sun.util.resources.LocaleNames_nl;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TranslationProblem1 {
    @Test
    public void readCsv() throws Exception {

        CsvReader csvReader = new CsvReader(new CsvLineProcessor() {
            public void processLine(String[] line) {
                assertThat(line[0], equalTo("Beschreibungstext des Kontierungselementes"));
                assertThat(line[1], equalTo("Description (text) of account assignment element"));
                assertThat(line[2], equalTo("Description (texte) de l'élément du compte d'affectation"));
                assertThat(line[3], equalTo("Beschrijving (tekst) van het element rubricering"));
            }
        }, new CsvLogger());
        csvReader.readFile(FixtureConstants.CSV_FILE_TRANSLATION_PROBLEM1);
    }

    @Test
    public void translate() {
             XlfTranslator xlfTranslator = new XlfTranslator(FixtureConstants.BASEDIR);
        xlfTranslator.translate(FixtureConstants.CSV_FILE_TRANSLATION_PROBLEM1, Locale.FRANCE, 0, 3, AppConstants.DEFAULT_CSV_CHARSET);
    }
}

