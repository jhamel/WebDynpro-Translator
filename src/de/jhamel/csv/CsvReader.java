package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import org.apache.log4j.Logger;

import java.io.FileReader;

public class CsvReader {
        // constants

    private static Logger log = Logger.getLogger(CsvReader.class);

    // fiel

    private String filename;
    private CsvLineProcessor csvLineProcessor;

    public CsvReader(String filename, CsvLineProcessor csvLineProcessor) {
        this.filename = filename;
        this.csvLineProcessor = csvLineProcessor;

    }

    public void readFile() throws Exception {
        String[][] lines = CSVParser.parse(new FileReader(filename), ';');
        for (String[] line : lines) {
            csvLineProcessor.processLine(line);
        }
    }

}
