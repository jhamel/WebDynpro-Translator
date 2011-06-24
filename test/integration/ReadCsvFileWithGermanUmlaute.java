package integration;

import de.jhamel.csv.CsvLineProcessor;
import de.jhamel.csv.CsvReader;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ReadCsvFileWithGermanUmlaute {
    @Test
    public void readUmlaute() throws Exception {

        CsvReader csvReader = new CsvReader(new CsvLineProcessor() {
            public void processLine(String[] line) {
                assertThat(line[0], equalTo("äbc"));
            }
        });
        csvReader.readFile(FixtureConstants.CSV_FILE_WITH_GERMAN_UMLAUTE);
    }
}
