import com.Ostermiller.util.CSVParser;
import de.jhamel.Translator;
import de.jhamel.constants.AppConstants;
import de.jhamel.csv.CsvLogger;
import de.jhamel.csv.CsvWriter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.file.filters.ContainsFilenameFilter;
import de.jhamel.file.filters.EndsWithFilenameFilter;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Locale;

public class Main {
    private static Logger log = Logger.getLogger(Main.class);

    private static Options options = null;

    //    private static final String BASEDIR_WD = "/private/tmp/22116/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/";
    //	private static final String WDDIR = "/private/tmp/22116/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd";
    //	public static final String BASEDIR_PROJECT = "/Users/helmut/projekte/EON/EIS/WD Translator/";
    //    public static final String CSV = "/Users/helmut/projekte/EON/EIS/WD Translator/translation_20110330_DE-EN-FR-NL_VDE.csv";

    public static void main(String[] args) throws Exception {
        options = Main.buildOptions();
        // create the parser
        CommandLineParser parser = new PosixParser();
        try {
            // parse the command line arguments
            CommandLine cmd = parser.parse(options, args);

            // if help is selected at commandline just show the help screen
            if (cmd.hasOption("help")) {
                Main.showHelp();
            } else {
                System.out.println("Please be patient. Have a look at the log files if you want to know whats going on.");
                if (cmd.hasOption("a")) {
                    if (cmd.getOptionValue("a").equals("in")) {
                        // Here we go to the import task
                        importCSV(cmd);
                    } else if (cmd.getOptionValue("a").equals("out")) {
                        // Here we export the language strings of the WD projects into a csv file
                        createCSV(cmd);
                    } else if (cmd.getOptionValue("a").equals("check")) {
                        checkTranslation(cmd);
                    } else {
                        Main.showHelp();
                    }
                } else {
                    Main.showHelp();
                }
            }

        } catch (ParseException exp) {
            // oops, something went wrong
            System.err.println("Parsing failed.  Reason: " + exp.getMessage());
        }
    }

    /**
     * checks the translation or lets better say checks if there are inconsistencies in the translated
     * entries.
     * <p/>
     * After importing a translated csv file into your Web Dynpro project you should export one and check this
     * exported file with this script.
     * It compares two columns of the file. Usually the column of the projects default language
     * and one translated language.
     * If it finds equal values in those columns it protocols those
     * A protocol entry is also written if the value of the translation language is empty.
     * @param cmd CommandLine object
     */
    private static void checkTranslation(CommandLine cmd) {
        try {
            log.info("starting csv file check");
            String csvFile = cmd.getOptionValue("i");
            int defaultLangColumn = Integer.parseInt(cmd.getOptionValue("d"));
            int translateLangColumn = Integer.parseInt(cmd.getOptionValue("t"));
            String charset = cmd.getOptionValue("c");
            if (charset == null) {
                charset = AppConstants.DEFAULT_CSV_CHARSET;
            }

            StringBuilder builder = new StringBuilder();
            builder.append("Parameters: \n");
            builder.append("csvInputFile: ").append(csvFile).append("\n");
            builder.append("defaultLangColumn: ").append(defaultLangColumn).append("\n");
            builder.append("translateLangColumn: ").append(translateLangColumn).append("\n");
            builder.append("charset: ").append(charset).append("\n");
            log.info(builder.toString());

            String[][] lines = CSVParser.parse(new InputStreamReader(new FileInputStream(csvFile), Charset.forName(charset)), AppConstants.CSV_ENTRY_SEPERATOR);

            System.out.println("Checking the file " + csvFile + " for correct translations");
            System.out.println("Comparing column " + defaultLangColumn + " (default language) and column " + translateLangColumn + " (translated language)");
            System.out.println("");
            System.out.println("equal values start with ==, empty tranlations with <<\n");
            System.out.println("line\tvalue");

            int lineCount = 0;
            int lineCountDiffSame = 0;
            int lineCountDiffEmpty = 0;
            for (String[] line : lines) {
                lineCount++;
                try {
                    if (line[defaultLangColumn].equals(line[translateLangColumn])) {
                        lineCountDiffSame++;
                        System.out.println(lineCount + ":\t== " + line[defaultLangColumn]);
                    }
                    if (line[translateLangColumn].trim().isEmpty()) {
                        lineCountDiffEmpty++;
                        System.out.println(lineCount + ":\t<< " + line[defaultLangColumn]);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    System.out.println("An ArrayIndexOutOfBoundsException occured in line " + lineCount + " of the file. Please check it");
                }
            }

            System.out.println("Number of checked rows: " + lineCount);
            System.out.println("Number of rows with same values in default language column and translated language column: " + lineCountDiffSame);
            System.out.println("Number of rows with empty translation: " + lineCountDiffEmpty);

        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    /**
     * Import the CSV-File and create the XLF-Files in the WD project(s)
     * @param cmd
     * @throws Exception
     */
    private static void importCSV(CommandLine cmd) throws Exception {
		try {
			log.info("starting import of csv file");
			String csvInputFile = cmd.getOptionValue("i");
			String language = cmd.getOptionValue("l");
			String baseDirWebDynpro = cmd.getOptionValue("w");
			int defaultLangColumn = Integer.parseInt(cmd.getOptionValue("d"));
			int translateLangColumn = Integer.parseInt(cmd.getOptionValue("t"));
			String charset = cmd.getOptionValue("c");
			if(charset == null) {
				charset = AppConstants.DEFAULT_CSV_CHARSET;
			}

			StringBuilder builder = new StringBuilder();
			builder.append("Parameters: \n");
			builder.append("csvInputFile: ").append(csvInputFile).append("\n");
			builder.append("language: ").append(language).append("\n");
			builder.append("baseDirWebDynpro: ").append(baseDirWebDynpro).append("\n");
			builder.append("defaultLangColumn: ").append(defaultLangColumn).append("\n");
			builder.append("translateLangColumn: ").append(translateLangColumn).append("\n");
			builder.append("charset: ").append(charset).append("\n");
			log.info(builder.toString());

			if( csvInputFile != null && language != null && baseDirWebDynpro != null && defaultLangColumn != translateLangColumn) {
				Locale locale = new Locale(language);
				// Here we do the main work
				new Translator().translate(csvInputFile, locale, baseDirWebDynpro, defaultLangColumn, translateLangColumn, charset);
				log.info("import of csv file finished");
			} else {
				Main.showHelp();
			}
		} catch (Exception ex) {
			log.error("The following error occurred:", ex);
			Main.showHelp();
		}
    }

    /**
     * Parse the XLF files of the WD project(s) and export the csv file for translation
     * @param cmd
     */
    private static void createCSV(CommandLine cmd) {
        log.info("starting export of csv file");
        String csvOutputFile = cmd.getOptionValue("o");
        String baseDirWebDynpro = cmd.getOptionValue("w");
        String language = cmd.getOptionValue("l");
        String charset = cmd.getOptionValue("c");
        if (charset == null) {
            charset = AppConstants.DEFAULT_CSV_CHARSET;
        }
        //String baseDirProject = cmd.getOptionValue("d");

        StringBuilder builder = new StringBuilder();
        builder.append("Parameters: \n");
        builder.append("csvOutputFile: ").append(csvOutputFile).append("\n");
        builder.append("language: ").append(language).append("\n");
        builder.append("baseDirWebDynpro: ").append(baseDirWebDynpro).append("\n");
        builder.append("charset: ").append(charset).append("\n");
        log.info(builder.toString());

        if (csvOutputFile != null && baseDirWebDynpro != null && language != null) {
            Locale locale = new Locale(language);

            XlfFileCollector xlfFileCollector = scanFilesForWords(baseDirWebDynpro);

            new  CsvWriter(new CsvLogger()).writeToCsvFile(csvOutputFile, xlfFileCollector.wordsWithoutDuplicates(), locale, charset);
            log.info("export of csv file finished");
        } else {
            Main.showHelp();
        }
    }

    /** show the help screen of the application */
    private static void showHelp() {
        HelpFormatter formatter = new HelpFormatter();
        formatter.setWidth(120);
        formatter.printHelp("Main [-adhilotw]\nThis application imports a csv file and then updates the xlf files of a WD project \nor extracts the items of the xlf files " + "of a WD project and exports a csv file that can be send to a translation office", options);
    }

    /**
     * Optionen für die Aufrufparamter bauen s. http://commons.apache.org/cli/usage.html
     * @return gibt das Options Objekt mit allen Startparametern zurück.
     */
    private static Options buildOptions() {
        // create Options object
        Options options = new Options();

        // help option to show the help screen
        options.addOption("h", "help", false, "show this help");
        // action option
        Option action = OptionBuilder.withDescription("defines the action to be executed.\n" + "<in> for import of a csv file and updating the WD projects xlf files,\n" + "<out> for export of a csv file that can be.\n" + "<check> to check the translation" + "REQUIRED").withLongOpt("action").hasArg().withArgName("action").create("a");
        options.addOption(action);

        Option csvInputFile = OptionBuilder.withArgName("csvInputFile").hasArg().withDescription("filename of the csv file to import.\n" + "REQUIRED when option a==in or a==check").withLongOpt("csvinputfile").create("i");
        options.addOption(csvInputFile);

        Option outputFile = OptionBuilder.withArgName("csvOutputFile").hasArg().withDescription("filename including path of the csv file that is generated.\n" + "REQUIRED when a==out").withLongOpt("csvOutputFile").create("o");
        options.addOption(outputFile);

        Option baseDirWebDynpro = OptionBuilder.withArgName("baseDirWebDynpro").hasArg().withDescription("base directory of the Web Dynpro project(s). e.g.: C:/Dokumente und\n" + "Einstellungen/your-user/.dtc/1/DCs/yourcompany.com/path1/path2/wd\n" + "REQUIRED").withLongOpt("bWD").create("w");
        options.addOption(baseDirWebDynpro);

        Option language = OptionBuilder.withArgName("isoLanguage").hasArg().withDescription("language that is to be used by the action. Use the iso language code, e.g. de, fr, uk, us\n" + "REQUIRED when option a == in").withLongOpt("language").create("l");
        options.addOption(language);

        Option defaultLangColumn = OptionBuilder.withArgName("columnNumber").hasArg().withDescription("0 based number of column in which the default language can be found\n" + "REQUIRED when option a==in or a==check").withLongOpt("defaultLangColumn").create("d");
        options.addOption(defaultLangColumn);

        Option translateLangColumn = OptionBuilder.withArgName("columnNumber").hasArg().withDescription("0 based number of column in which the translated language can be found.\n" + "REQUIRED when option a==in or a==check").withLongOpt("transLangColumn").create("t");
        options.addOption(translateLangColumn);

        Option charset = OptionBuilder.withArgName("Charset").hasArg().withDescription("Charset of the csv file that is read (a==in), written (a==out) or checked (a==check). " + "If not defined UTF-8 will be used.\nAllowed values are those that are supported by " + "java.nio.charset.Charset.availableCharsets().\nMost common are " + "ISO-8859-1, UTF-8").withLongOpt("charset").create("c");
        options.addOption(charset);

        return options;
    }

    public static XlfFileCollector scanFilesForWords(String basedir) {
        XlfFileCollector xlfFileCollector = new XlfFileCollector();
        TraverseDirectory traverseDirectory = new TraverseDirectory(basedir, xlfFileCollector);
        traverseDirectory.addFilenameFilter(new EndsWithFilenameFilter(".xlf"));
        traverseDirectory.addFilenameFilter(new ContainsFilenameFilter(File.separator + "bin" + File.separator));
        traverseDirectory.processFiles();
        return xlfFileCollector;
    }
}
