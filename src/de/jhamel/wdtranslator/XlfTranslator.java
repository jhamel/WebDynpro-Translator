package de.jhamel.wdtranslator;

import de.jhamel.constants.AppConstants;
import de.jhamel.csv.CsvLineProcessor;
import de.jhamel.csv.CsvReader;
import de.jhamel.wdtranslator.xlf.Word;
import de.jhamel.wdtranslator.xlf.XlfDirectoryTraverser;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class XlfTranslator {
    private static Logger log = Logger.getLogger(XlfTranslator.class);
    private List<String[]> csvLines = new ArrayList<String[]>();

    private XlfFileCollector xlfFileCollector;

    public XlfTranslator(String basedir) {
        this.xlfFileCollector = XlfDirectoryTraverser.collectWords(basedir);
    }

	/**
	 * reads a csv file and save each line that is a String[] in the List csvLines
	 * @param csvFile name of the csv file to read
	 * @param charset Charset to use for reading the csv file
	 */
    private void readCsvData(String csvFile, String charset) {
		log.trace("entering readCsvData");
        CsvReader csvReader = new CsvReader(new CsvLineProcessor() {
			/**
			 * processes a single line of the csv file. This method is called by the CsvReader
			 * @param line a line of the csv file where each column is transmitted as a separate array element
			 */
            public void processLine(String[] line) {
				// add the line of the csv file to the List of lines
                csvLines.add(line);
            }
        });
        try {
			// Read the csv file. The readFile method call the processLine method 3 lines above this
            csvReader.readFile(csvFile, charset);
        } catch (Exception e) {
            throw new TechnicalException("Could not read CSV file '" + csvFile + "'", e);
        }
		log.trace("exiting readCsvData");
    }
          public void translate(String csvFile, Locale language, int csvColumnDefaultLanguage, int csvColumnTranslationLanguage) {
                    translate(csvFile,language,csvColumnDefaultLanguage,csvColumnTranslationLanguage, AppConstants.DEFAULT_CSV_CHARSET);
          }
	/**
	 * Überträgt die Übersetzungen einer CSV-Datei in XLF-Dateien des Web Dynpro Projektes.
	 * @param csvFile die CSV-Datei mit den Übersetzungen
	 * @param language Locale, deren XLF-Dateien angelegt werden sollen
	 * @param csvColumnDefaultLanguage Spalte in der CSV-Datei, in der das Wort der Standardsprache steht, 0-basiert
	 * @param csvColumnTranslationLanguage Spalte der CSV-Datei, in der das übersetzte Wort steht, 0-basiert
	 * @param charset Charset to use when reading the csv file
	 */
    public void translate(String csvFile, Locale language, int csvColumnDefaultLanguage, int csvColumnTranslationLanguage, String charset) {
		log.trace("entering translate");
		// read the csv file into the list of String[]
        readCsvData(csvFile, charset);
		// traverse the lines of the List
        for (String[] line : csvLines) {
			StringBuilder builder = new StringBuilder();
			for(String str: line) {
				builder.append(str).append("; ");
			}
            log.debug("translating CSV line: " + builder.toString());

            List<Word> wordsByDefaultLanguage = xlfFileCollector.wordsByDefaultText(line[csvColumnDefaultLanguage]);
			// iterate through the list of defaultlanguage words
            for (Word word : wordsByDefaultLanguage) {
				// add the translated value to the word
                word.addTranslation(language, line[csvColumnTranslationLanguage]);
                word.store();
            }
        }
		log.trace("exiting translate");

    }
}
