package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import de.jhamel.wdtranslator.xlf.XlfWord;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<XlfWord> readWords(String filename) throws Exception {
        List<XlfWord> result = new ArrayList<XlfWord>();
        String[][] lines = CSVParser.parse(new FileReader(filename), ';');
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i];
            XlfWord word = new XlfWord();
            if (line[3] != null && line[3].trim().length() > 0) {
                XlfWord translation = new XlfWord();
                translation.setWord(line[3]);
                word.setTranslation(translation);
            }
            word.setWord(line[2]);
            result.add(word);
        }
        return result;

    }

    public static void main(String[] args) throws Exception {
        CsvReader.readWords("translation.csv");
    }
}
