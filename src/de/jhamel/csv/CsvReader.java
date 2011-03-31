package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import org.apache.log4j.Logger;

import java.io.FileReader;

public class CsvReader {

    private static Logger log = Logger.getLogger(CsvReader.class);
    private static final char CSV_ENTRY_SEPERATOR = ';';

    private CsvLineProcessor csvLineProcessor;

    public CsvReader(CsvLineProcessor csvLineProcessor) {
        this.csvLineProcessor = csvLineProcessor;
    }

    public void readFile(String filename) throws Exception {
        String[][] lines = CSVParser.parse(new FileReader(filename), CSV_ENTRY_SEPERATOR);
        for (String[] line : lines) {
            log.trace("Reading line '"+logString(line)+"' of'"+filename+"'.");
            csvLineProcessor.processLine(line);
        }
    }

    private String logString(String[] line) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < line.length; i++) {
            builder.append(line[i]);
            if (isNotLastElement(line.length, i)) builder.append(CSV_ENTRY_SEPERATOR);
        }
        return builder.toString();
    }

    private boolean isNotLastElement(int length, int index) {
        return index + 1 < length;
    }

}
