package de.jhamel.csv;

import com.Ostermiller.util.CSVParser;
import de.jhamel.wdtranslator.xlf.Word;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public static List<Word> readWords(String filename) throws Exception {
        List<Word> result = new ArrayList<Word>();
        String[][] lines = CSVParser.parse(new FileReader(filename), ';');
        for (int i = 0; i < lines.length; i++) {
            String[] line = lines[i];
            Word word = new Word();
            if (line[3] != null && line[3].trim().length() > 0) {
                Word translation = new Word();
                translation.setText(line[3]);
              //  word.setTranslation(translation);
            }
            word.setText(line[2]);
            result.add(word);
        }
        return result;

    }

    public static void main(String[] args) throws Exception {
        CsvReader.readWords("translation.csv");
    }
}
