package de.jhamel.constants;

import java.util.Locale;

/**
 * Created by IntelliJ IDEA.
 * User: helmut
 * Date: 16.06.11
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public interface AppConstants {
	/** Basisverzeichnis des zu übersetzenden Projektes */
	public static final String BASEDIR_WD = "/private/tmp/22116/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/";
	/** Verzeichnis, in dem die Web Dynpro Projekte zu finden sind */
	public static final String WDDIR = "/private/tmp/22116/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd";
	//public static final String WDDIR = "/Users/helmut/Downloads/WDTranslator/";
	/** Basisverzeichnis dieses Java-Projektes, d.h. das Verz., in dem dieses Projekt auf der lokalen Festplatte liegt */
	public static final String BASEDIR_PROJECT = "/Users/helmut/projekte/EON/EIS/WD Translator/";
	/** CSV-Datei, in die die extrahierten zu übersetzenden Wörter geschrieben werden */
	//public static final String CSV_OUTPUT = "/Users/helmut/projekte/EON/EIS/WD Translator/translation_20110330_DE-EN-FR-NL_VDE.csv";
	/** CSV-Importdatei. Datei aus der die Übersetzungen gelesen werden */
	public static final String CSV_INPUT = "/Users/helmut/projekte/EON/EIS/WD Translator/resources/ExSP_Frontend.csv";
	/** Dateiname der CSV Datei, in die die extrahierten Begriffe geschrieben werden und die zum Übersetzen rausgegeben werden kann. */
	public static final String CSV_OUTPUT_FILE = "ExSP_Frontend_now.csv";

	/** Aktuell zu übersetzende Sprache bzw. das Locale dazu */
	public static final Locale CURRENT_LOCALE = Locale.FRENCH;
	/** Spalte der CSV-Datei, in der das Wort / der Begriff der Standardsprache (deutsch) steht */
	public static final int DEFAULT_LANG_COLUMN = 0;
	/** Spalte der CSV-Datei, in der das Wort / der der übersetzte Begriff steht */
	public static final int TRANSLATE_LANG_COLUMN = 1;

}
