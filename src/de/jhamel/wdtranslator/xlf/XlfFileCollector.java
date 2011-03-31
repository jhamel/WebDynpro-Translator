package de.jhamel.wdtranslator.xlf;

import de.jhamel.file.ContainsFilenameFilter;
import de.jhamel.file.EndsWithFilenameFilter;
import de.jhamel.file.FileProcessor;
import de.jhamel.file.TraverseDirectory;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class XlfFileCollector implements FileProcessor {

    // constants

    private static Logger log = Logger.getLogger(XlfFileCollector.class);

    // fields

    private List<Word> words = new ArrayList<Word>();
    private HashMap<File, List<Word>> xlfWordsByFile = new HashMap<File, List<Word>>();
    private HashMap<Language, List<Word>> xlfWordsByLanguage = new HashMap<Language, List<Word>>();
    private HashMap<String, List<Word>> xlfWordsByWord = new HashMap<String, List<Word>>();
    private HashMap<String, Word> xlfWordByLanguagePlusKey = new HashMap<String, Word>();
    private String basedir;

    public XlfFileCollector() {
    }

    public XlfFileCollector(String basedir) {
        this.basedir = basedir;
    }

    // public methods

    public void replaceTranslationsForGivenDefaultWord(String defaultWordText, Language language, String translation) {
        List<Word> words = getWordByDefaultText(defaultWordText);
        for (Word word : words) {
            Word wordEn = word.getTranslationByLanguage(language);
            if (wordEn == null) {
                continue;
            }
            wordEn.setText(translation);
            wordEn.store();
        }
    }

    private List<Word> getWordByDefaultText(String defaultWordText) {
        List<Word> wordsDefault = new ArrayList<Word>();
        for (Word word : words()) {
            if (word.getText().equals(defaultWordText)) {
                wordsDefault.add(word);
            }
        }
        return wordsDefault;
    }


    public void scanXlfFiles() {
        TraverseDirectory traverseDirectory = new TraverseDirectory(basedir, this);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.addFilenameFilter(new ContainsFilenameFilter(File.pathSeparator+"bin"+File.pathSeparator));
        traverseDirectory.processFiles();
    }

    public void processFile(File file) {
        log.trace("Processing file '" + file.getName() + "'");
        addByFile(file);  // must be first add, because others depend on it
        addByLanguage(file);
        addByWord(file);
        addByLanguagePlusKey(file);
    }

    private void addByLanguagePlusKey(File file) {
        for (Word word : wordsInFile(file)) {
            xlfWordByLanguagePlusKey.put(word.getId(), word);
        }
    }

    public List<Word> words() {
        if (words.size() > 0) return words;
        words = getWordsByLanguageKey(Language.DEFAULT);

        for (Language language : translationLanguages()) {
            for (Word word : words) {
                Word translatedWord = xlfWordByLanguagePlusKey.get(Word.keyGen(language, word.getKey()));
                if (translatedWord != null) word.addTranslation(translatedWord);
            }
        }
        return words;
    }

    private Set<Language> translationLanguages() {
        Set<Language> translationLanguages = xlfWordsByLanguage.keySet();
        translationLanguages.remove(Language.DEFAULT);
        return translationLanguages;
    }

    public List<Word> getWordsByLanguageKey(Language language) {
        List<Word> wordsList = xlfWordsByLanguage.get(language);
        if (wordsList == null) {
            wordsList = new ArrayList<Word>();
        }
        xlfWordsByLanguage.put(language, wordsList);
        return wordsList;
    }

    public List<Word> wordsInFile(File file) {
        List<Word> wordsList = xlfWordsByFile.get(file);
        if (wordsList == null) {
            wordsList = new ArrayList<Word>();
        }
        xlfWordsByFile.put(file, wordsList);
        return wordsList;
    }

    public List<Word> getWordsByWord(String word) {
        List<Word> wordsList = xlfWordsByWord.get(word);
        if (wordsList == null) {
            wordsList = new ArrayList<Word>();
        }
        xlfWordsByWord.put(word, wordsList);
        return wordsList;
    }

    // private methods

    private List<Word> retrieveWordsOfFile(File file) {
        return new TransUnitToWordConverter(file).convertTransUnitsToWords();
    }

    private void addByFile(File file) {
        List<Word> wordsList = wordsInFile(file);
        wordsList.addAll(retrieveWordsOfFile(file));
    }

    private void addByLanguage(File file) {
        Language languageKey = Language.languageOfFile(file);
        List<Word> wordsList = getWordsByLanguageKey(languageKey);
        wordsList.addAll(wordsInFile(file));
    }

    private void addByWord(File file) {
        List<Word> words = wordsInFile(file);
        for (Word word : words) {
            List<Word> wordsList = getWordsByWord(word.getText());
            wordsList.add(word);
            xlfWordsByWord.put(word.getText(), wordsList);
        }
    }

}
