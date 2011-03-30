package de.jhamel.csv;

import de.jhamel.wdtranslator.WebDynproTranslatorException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CsvWriter {

    public static void writeToCsvFile(String fileName, List<? extends CSVable> entries) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new FileWriter(fileName));
            for (CSVable csv : entries) {
                out.write(csv.toCsv());
                out.newLine();
            }

        } catch (IOException e) {
            throw new WebDynproTranslatorException("Could not write to file '" + fileName + "'. (" + e.getMessage() + ")", e);
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
