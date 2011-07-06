package de.jhamel.csv;

import de.jhamel.wdtranslator.TechnicalException;
import de.jhamel.wdtranslator.xlf.Word;
import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Locale;

public class CsvWriter {
    private static Logger log = Logger.getLogger(CsvWriter.class);
    private CsvLogger csvLogger;

    public CsvWriter(CsvLogger csvLogger) {
        this.csvLogger = csvLogger;
    }

    public void writeToCsvFile(String filename, Collection<Word> words, Locale locale, String charset) {
        BufferedWriter out = null;
        try {
            //			out = new BufferedWriter(new FileWriter(filename));
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), Charset.forName(charset)));
            for (Word word : words) {
                log.trace("Writing line '" + word.toCsv() + "' to '" + filename + "'.");
                csvLogger.warnInCaseOfDuplicateEntries(word);
                out.write(word.toCsv());
                out.newLine();
            }
        } catch (IOException e) {
            throw new TechnicalException("Could not write to file '" + filename + "'. (" + e.getMessage() + ")", e);
        } finally {
            closeWriter(out);
        }
    }

    private static void closeWriter(BufferedWriter out) {
        try {
            out.close();
        } catch (IOException e) {
            // at least we tried
        }
    }

}
