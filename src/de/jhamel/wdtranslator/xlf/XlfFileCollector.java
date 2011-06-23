package de.jhamel.wdtranslator.xlf;

import de.jhamel.file.FileProcessor;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.*;

public class XlfFileCollector implements FileProcessor {

    private static Logger log = Logger.getLogger(XlfFileCollector.class);

    private List<Word> words = new ArrayList<Word>();

    private HashMap<File, List<Word>> wordsByFile = new HashMap<File, List<Word>>();
    private HashMap<Locale, List<Word>> wordsByLocale = new HashMap<Locale, List<Word>>();
    private HashMap<String, Word> wordByUniqueIdentifier = new HashMap<String, Word>();
    private HashMap<String, List<Word>> wordsByDefaultText = new HashMap<String, List<Word>>();


    public void processFile(File file) {
        log.trace("Processing file '" + file.getName() + "'");
        addByFile(file);  // must be first add, because others depend on it
        addByLocale(file);
        addByUniqueId(file);
    }

    public void replaceTranslationsForGivenDefaultWord(String defaultWordText, Locale locale, String translation) {
        List<Word> words = wordsByDefaultText(defaultWordText);
        for (Word word : words) {
            Word wordTranslation = word.getTranslationByLocale(locale);
            if (wordTranslation == null) continue;
            wordTranslation.setText(translation);
            wordTranslation.store();
        }
    }



    public List<Word> wordsByDefaultText(String text) {
        if (wordsByDefaultText.isEmpty()) {
            initWordsDefaultText();
        }
        List<Word> result = wordsByDefaultText.get(text);
        return result == null? new ArrayList<Word>():result;
    }

    public List<Word> words() {
        if (words.size() > 0) return words;

        // lazy init

        words = getWordsByLocale(Locale.getDefault());

        for (Locale locale : translationLanguages()) {
            for (Word word : words) {
                Word translatedWord = wordByUniqueIdentifier.get(Word.generateUniqueIdentifier(locale, word.getKey()));
                if (translatedWord != null) word.addTranslation(translatedWord);
            }
        }


        return words;
    }

    private void initWordsDefaultText() {
        for (Word word : words) {
            List<Word> wordsByDef = wordsByDefaultText.get(word.getText());
            if (wordsByDef == null) wordsByDef = new ArrayList<Word>();
            wordsByDef.add(word);
            wordsByDefaultText.put(word.getText(), wordsByDef);
        }
    }

    public Collection<Word> wordsWithoutDuplicates() {
        HashMap<String, Word> wordsWithoutDuplication = new HashMap<String, Word>();
        for (Word word : words()) {
            if (wordsWithoutDuplication.containsKey(word.getText())) {
                Word wordPre = wordsWithoutDuplication.get(word.getText());
                if (!wordPre.toCsv().equals(word.toCsv())) {
                    log.error("!!!" + word.toCsv() + " =! " + wordPre.toCsv());
                    log.error("!!!" + word.getFile() + " =! " + wordPre.getFile());
                }
            }
            wordsWithoutDuplication.put(word.getText(), word);
        }
        return wordsWithoutDuplication.values();
    }

    public Set<Locale> translationLanguages() {
        Set<Locale> translationLanguages = wordsByLocale.keySet();
        translationLanguages.remove(Locale.getDefault());
        return translationLanguages;
    }

    public List<Word> getWordsByLocale(Locale locale) {
        List<Word> wordsList = wordsByLocale.get(locale);
        if (wordsList == null) wordsList = new ArrayList<Word>();
        wordsByLocale.put(locale, wordsList);
        return wordsList;
    }

    public List<Word> wordsInFile(File file) {
        List<Word> wordsList = wordsByFile.get(file);
        if (wordsList == null) {
            wordsList = new ArrayList<Word>();
        }
        wordsByFile.put(file, wordsList);
        return wordsList;
    }

    // add methods

    private void addByFile(File file) {
        List<Word> wordsList = wordsInFile(file);
        wordsList.addAll(retrieveWordsOfFile(file));
    }

    private void addByLocale(File file) {
        Locale localeOfFile = LocaleUtil.localeOfFile(file);
        List<Word> wordsList = getWordsByLocale(localeOfFile);
        wordsList.addAll(wordsInFile(file));
    }

    private void addByUniqueId(File file) {
        for (Word word : wordsInFile(file)) {
            wordByUniqueIdentifier.put(word.getUniqueId(), word);
        }
    }

    private List<Word> retrieveWordsOfFile(File file) {
        return new TransUnitToWordConverter(file).convertTransUnitsToWords();
    }

}
