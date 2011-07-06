package de.jhamel.csv;

import de.jhamel.wdtranslator.xlf.Word;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CsvLogger {
    private static Logger log = Logger.getLogger(CsvLogger.class);

    public  void warnInCaseOfDuplicateEntries(String[] line) {
        List<String> entries = new ArrayList<String>();
        for (String entry : line) {
            if (entries.contains(entry.trim())) {
                log.warn("Line '" + line + "' has a duplicate entry '" + entry + "'.");
            }
            entries.add(entry.trim());
        }
    }

    public  void warnInCaseOfDuplicateEntries(Word word) {
        warnInCaseOfDuplicateEntries(word.toCsv().split(";"));
    }
}