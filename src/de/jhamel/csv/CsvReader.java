package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import de.jhamel.wdtranslator.xlf.Word;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

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
