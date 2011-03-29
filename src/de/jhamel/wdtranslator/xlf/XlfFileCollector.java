package de.jhamel.wdtranslator.xlf;

import de.jhamel.file.FileProcessor;
import de.jhamel.file.FileUtil;
import de.jhamel.file.TraverseDirectory;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class XlfFileCollector implements FileProcessor {

    // constants

    private static Logger log = Logger.getLogger(TraverseDirectory.class);

    // fields

    private XlfKeyWordCollector xlfKeyWordCollector = new XlfKeyWordCollector();
    private Hashtable<File, List<XlfWord>> xlfWordsByFile = new Hashtable<File, List<XlfWord>>();
    private Hashtable<String, List<XlfWord>> xlfWordsByLanguage = new Hashtable<String, List<XlfWord>>();
    private Hashtable<String, List<XlfWord>> xlfWordsByWord = new Hashtable<String, List<XlfWord>>();

    // public methods

    public void processFile(File file) {
        log.trace("Processing file '" + file.getName() + "'");
        addByFile(file);  // must be first add, because others depend on it
        addByLanguage(file);
        addByWord(file);
    }

    public List<XlfWord> getWordsByLanguageKey(String languageKey) {
        List<XlfWord> xlfWordsList = xlfWordsByLanguage.get(languageKey);
        if (xlfWordsList == null) {
            xlfWordsList = new ArrayList<XlfWord>();
        }
        xlfWordsByLanguage.put(languageKey, xlfWordsList);
        return xlfWordsList;
    }

    public List<XlfWord> getWordsByFile(File file) {
        List<XlfWord> xlfWordsList = xlfWordsByFile.get(file);
        if (xlfWordsList == null) {
            xlfWordsList = new ArrayList<XlfWord>();
        }
        xlfWordsByFile.put(file, xlfWordsList);
        return xlfWordsList;
    }

    public List<XlfWord> getWordsByWord(String word) {
        List<XlfWord> xlfWordsList = xlfWordsByWord.get(word);
        if (xlfWordsList == null) {
            xlfWordsList = new ArrayList<XlfWord>();
        }
        xlfWordsByWord.put(word, xlfWordsList);
        return xlfWordsList;
    }

    // private methods

    private List<XlfWord> retrieveWordsOfFile(File file) {
        return xlfKeyWordCollector.scanFile(file);
    }

    private void addByFile(File file) {
        List<XlfWord> xlfWordsList = getWordsByFile(file);
        xlfWordsList.addAll(retrieveWordsOfFile(file));
    }

    private void addByLanguage(File file) {
        String languageKey = FileUtil.languageKeyForFile(file);
        List<XlfWord> xlfWordsList = getWordsByLanguageKey(languageKey);
        xlfWordsList.addAll(getWordsByFile(file));
    }

    private void addByWord(File file) {
        List<XlfWord> words = getWordsByFile(file);
        for (XlfWord word : words) {
            List<XlfWord> xlfWordsList = getWordsByWord(word.getWord());
            xlfWordsList.add(word);
            xlfWordsByWord.put(word.getWord(), xlfWordsList);
        }
    }

}
