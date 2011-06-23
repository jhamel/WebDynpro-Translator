package de.jhamel.csv;

import de.jhamel.wdtranslator.TechnicalException;
import de.jhamel.wdtranslator.xlf.Word;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Locale;

public class CsvWriter {
    private static Logger log = Logger.getLogger(CsvWriter.class);

    public static void writeToCsvFile(String filename, Collection<Word> words, Locale locale) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(filename));
            for (Word word : words) {
                log.trace("Writing line '" + word.toCsv() + "' to '" + filename + "'.");
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
