package de.jhamel.csv;

import de.jhamel.wdtranslator.TechnicalException;
import de.jhamel.wdtranslator.xlf.Word;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

        // constants

    private static Logger log = Logger.getLogger(CsvWriter.class);

    public static void writeToCsvFile(String fileName, List<Word> words) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            for (Word word : words) {
                out.write(word.toCsv());
                out.newLine();
            }

        } catch (IOException e) {
            throw new TechnicalException("Could not write to file '" + fileName + "'. (" + e.getMessage() + ")", e);
        } finally {
            closeWriter(out);
        }
    }

    // private methods

    private static void closeWriter(BufferedWriter out) {
        try {
            out.close();
        } catch (IOException e) {
            // at least we tried
        }
    }

}
