package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import org.apache.log4j.Logger;

import java.io.FileReader;

public class CsvReader {

    private static Logger log = Logger.getLogger(CsvReader.class);

    private CsvLineProcessor csvLineProcessor;

    public CsvReader( CsvLineProcessor csvLineProcessor) {
        this.csvLineProcessor = csvLineProcessor;

    }

    public void readFile(String filename) throws Exception {
        String[][] lines = CSVParser.parse(new FileReader(filename), ';');
        for (String[] line : lines) {
            csvLineProcessor.processLine(line);
        }
    }

}
