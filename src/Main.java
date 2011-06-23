import com.sun.tools.javac.util.Log;
import de.jhamel.Translator;
import de.jhamel.constants.AppConstants;
import de.jhamel.csv.CsvWriter;
import de.jhamel.file.TraverseDirectory;
import de.jhamel.file.filters.ContainsFilenameFilter;
import de.jhamel.file.filters.EndsWithFilenameFilter;
import de.jhamel.wdtranslator.xlf.XlfFileCollector;
import org.apache.commons.cli.*;
import org.apache.log4j.Logger;

import javax.crypto.CipherInputStream;
import java.io.File;
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
			CommandLine cmd = parser.parse( options, args );

			// if help is selected at commandline just show the help screen
			if(cmd.hasOption("help")) {
				Main.showHelp();
			} else {
				if(cmd.hasOption("a")) {
					System.out.println(cmd.getOptionValue("a"));
					if(cmd.getOptionValue("a").equals("in")) {
						// Here we go to the import task
						//System.out.println("OK. Ich importiere jetzt die CSV-Datei");
						importCSV(cmd);
					} else if(cmd.getOptionValue("a").equals("out")) {
						// Here we export the language strings of the WD projects into a csv file
						createCSV(cmd);
					} else {
						Main.showHelp();
					}
				} else {
					Main.showHelp();
				}
			}

		}
		catch( ParseException exp ) {
			// oops, something went wrong
			System.err.println( "Parsing failed.  Reason: " + exp.getMessage() );
		}
    }

	/**
	 * Import the CSV-File and create the XLF-Files in the WD project(s)
	 * @param cmd
	 * @throws Exception
	 */
    private static void importCSV(CommandLine cmd) throws Exception {
		try {
			String csvInputFile = cmd.getOptionValue("i");
			String language = cmd.getOptionValue("l");
			String baseDirWebDynpro = cmd.getOptionValue("w");
			int defaultLangColumn = Integer.parseInt(cmd.getOptionValue("d"));
			int translateLangColumn = Integer.parseInt(cmd.getOptionValue("t"));

			if( csvInputFile != null && language != null && baseDirWebDynpro != null && defaultLangColumn != translateLangColumn) {
				Locale locale = new Locale(language);
				// Here we do the main work
				new Translator().doMagic(csvInputFile, locale, baseDirWebDynpro, defaultLangColumn, translateLangColumn);
			} else {
				Main.showHelp();
			}
		} catch (Exception ex) {
			Main.showHelp();
		}
    }

	/**
	 * Parse the XLF files of the WD project(s) and export the csv file for translation
	 * @param cmd
	 */
    private static void createCSV(CommandLine cmd) {
		String csvOutputFile = cmd.getOptionValue("o");
		String baseDirWebDynpro = cmd.getOptionValue("w");
		String language = cmd.getOptionValue("l");
		//String baseDirProject = cmd.getOptionValue("d");

		if(csvOutputFile != null && baseDirWebDynpro != null && language != null) {
			Locale locale = new Locale(language);

			XlfFileCollector xlfFileCollector = scanFilesForWords(baseDirWebDynpro);

			CsvWriter.writeToCsvFile(csvOutputFile, xlfFileCollector.wordsWithoutDuplicates(), locale);
		} else {
			Main.showHelp();
		}
    }

	/**
	 * show the help screen of the application
	 */
	private static void showHelp() {
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(120);
		formatter.printHelp("Main [-adhilotw]\nThis application imports a csv file and then updates the xlf files of a WD project \nor extracts the items of the xlf files " +
							"of a WD project and exports a csv file that can be send to a translation office", options );
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
		Option action = OptionBuilder.withDescription("defines the action which should be executed. " +
													"<in> for import of a csv file and updating the WD projects xlf files, " +
													"<out> for export of a csv file that can be.\n" +
													"REQUIRED").withLongOpt("action").hasArg().withArgName("action").create("a");
		options.addOption(action);

		Option csvInputFile   = OptionBuilder.withArgName( "csvInputFile" )
                                .hasArg()
                                .withDescription("filename of the csv file to import.\n" +
												"REQUIRED when option a == in")
								.withLongOpt("csvinputfile")
                                .create("i");
		options.addOption(csvInputFile);

		Option outputFile   = OptionBuilder.withArgName( "csvOutputFile" )
                                .hasArg()
                                .withDescription("filename including path of the csv file that is generated.\n" +
												"REQUIRED when a == out")
								.withLongOpt("csvOutputFile")
                                .create("o");
		options.addOption(outputFile);

		Option baseDirWebDynpro   = OptionBuilder.withArgName( "baseDirWebDynpro" )
                                .hasArg()
                                .withDescription("base directory of the Web Dynpro project(s). e.g.: C:/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd\n" +
												"REQUIRED")
								.withLongOpt("bWD")
                                .create("w");
		options.addOption(baseDirWebDynpro);

		Option language   = OptionBuilder.withArgName( "isoLanguage" )
                                .hasArg()
                                .withDescription("language that is to be used by the action. Use the iso language code, e.g. de, fr, uk, us\n" +
												"REQUIRED when option a == in")
								.withLongOpt("language")
                                .create("l");
		options.addOption(language);

		Option defaultLangColumn   = OptionBuilder.withArgName( "columnNumber" )
                                .hasArg()
                                .withDescription("0 based number of column in which the default language can be found\n" +
												"REQUIRED when option a == in")
								.withLongOpt("defaultLangColumn")
                                .create("d");
		options.addOption(defaultLangColumn);

		Option translateLangColumn   = OptionBuilder.withArgName( "columnNumber" )
                                .hasArg()
                                .withDescription("0 based number of column in which the translated language can be found.\n" +
												"REQUIRED when option a == in")
								.withLongOpt("transLangColumn")
                                .create("t");
		options.addOption(translateLangColumn);

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
