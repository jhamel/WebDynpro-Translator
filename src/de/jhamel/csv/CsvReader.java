package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import de.jhamel.constants.AppConstants;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/** A class that reads a csv file and submits the result to a CsvLineProcessor. */
public class CsvReader {

    private static Logger log = Logger.getLogger(CsvReader.class);
    private static final char CSV_ENTRY_SEPERATOR = ';';

    private CsvLineProcessor csvLineProcessor;

    /**
     * Constructor of the class. A CsvLineProcessor has to be given to this constructor. This
     * CsvLineProcessor is used in the readFile method.
     * @param csvLineProcessor a Processor for a line of the csv file. It has to have a processLine method.
     */
    public CsvReader(CsvLineProcessor csvLineProcessor) {
        this.csvLineProcessor = csvLineProcessor;
    }

    public void readFile(String filename) throws Exception {
        readFile(filename, AppConstants.DEFAULT_CSV_CHARSET);
    }

    /**
     * This method parses the csv file and submits each line to the CsvLineProcessor that was given to the
     * constructor of this class.
     * @param filename name of the csv file
     * @param charset  Charset for reading the csv file
     * @throws Exception
     */
    public void readFile(String filename, String charset) throws Exception {
        //		String[][] lines = CSVParser.parse(new FileReader(filename), CSV_ENTRY_SEPERATOR);
        String[][] lines = CSVParser.parse(new InputStreamReader(new FileInputStream(filename), Charset.forName(charset)), CSV_ENTRY_SEPERATOR);
        for (String[] line : lines) {
            log.trace("Reading line '" + logString(line) + "' of'" + filename + "'.");
            warnInCaseOfDuplicateEntries(line);
            // call the csvLineProcessors processLine method so that it can do something with the data of the line
            csvLineProcessor.processLine(line);
        }
    }

    private void warnInCaseOfDuplicateEntries(String[] line) {
        List<String> entries = new ArrayList<String>();
        for(String entry : line){
            if(entries.contains(entry.trim())){
                log.warn("Line '"+line+"' has a duplicate entry '"+entry+"'.");
            }
            entries.add(entry.trim());
        }
    }

    /**
     * Creates a log-Statement for the given line
     * @param line
     * @return
     */
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
