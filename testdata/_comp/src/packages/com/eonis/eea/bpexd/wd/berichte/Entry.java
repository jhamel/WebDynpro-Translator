package com.eonis.eea.bpexd.wd.berichte;

public class Entry {
	public Entry() {}
	
	public static final String BESTELLUNG = "BESTELLUNG";
	public static final String POSITION = "POSITION";
	
	private static final String ENTRY[][] =
	{
	  
	  // ID | Level | Bestellnr | bestDatum | einkauefer | gesBestellwert | Währung | aufmaßart | pos | posKurztext | Parent Id
	  {  "1000", BESTELLUNG, "B100100000", "10.10.2006", "Hr. Lehne", "1000,00", "€", "Leistungsaufmass", "","","ROOT"},
	  {  "1001", POSITION, "", "", "", "", "","","P1234567", "Kurztext für die Position Nummer 1", "1000"},
	  {  "1002", POSITION, "", "", "", "", "","","P1234568", "Kurztext für die Position Nummer 2", "1000"},
	  {  "1003", POSITION, "", "", "", "", "","","P1234569", "Kurztext für die Position Nummer 3", "1000"},
	  
	  {  "2000", BESTELLUNG, "B200100000", "20.11.2006", "Hr. Lehne", "1000,00", "€", "Leistungsaufmass", "","","ROOT"},
	  {  "2001", POSITION, "", "", "", "", "","","P2234567", "Kurztext für die Position Nummer 1", "2000"},
	  {  "2002", POSITION, "", "", "", "", "","","P2234568", "Kurztext für die Position Nummer 2", "2000"},
	  {  "2003", POSITION, "", "", "", "", "","","P2234569", "Kurztext für die Position Nummer 3", "2000"},
	  
	  {  "3000", BESTELLUNG, "B300100000", "10.12.2006", "Hr. Lehne", "1000,00", "€", "Leistungsaufmass", "","","ROOT"},
	  {  "3001", POSITION, "", "", "", "", "","","P3234567", "Kurztext für die Position Nummer 1", "3000"},
	  {  "3002", POSITION, "", "", "", "", "","","P3234568", "Kurztext für die Position Nummer 2", "3000"},
	  {  "3003", POSITION, "", "", "", "", "","","P3234569", "Kurztext für die Position Nummer 3", "3000"},
	  
	  {  "4000", BESTELLUNG, "B400100000", "11.09.2006", "Hr. Hübner", "1000,00", "€", "Leistungsaufmass", "","","ROOT"},
	  {  "4001", POSITION, "", "", "", "", "","","P4234567", "Kurztext für die Position Nummer 1", "4000"},
	  {  "4002", POSITION, "", "", "", "", "","","P4234568", "Kurztext für die Position Nummer 2", "4000"},
	  {  "4003", POSITION, "", "", "", "", "","","P4234569", "Kurztext für die Position Nummer 3", "4000"},
	  
	  {  "5000", BESTELLUNG, "B500100000", "12.08.2006", "Fr. Söder", "1000,00", "€", "Leistungsaufmass", "","","ROOT"},
	  {  "5001", POSITION, "", "", "", "", "","","P5234567", "Kurztext für die Position Nummer 1", "5000"},
	  {  "5002", POSITION, "", "", "", "", "","","P5234568", "Kurztext für die Position Nummer 2", "5000"},
	  {  "5003", POSITION, "", "", "", "", "","","P5234569", "Kurztext für die Position Nummer 3", "5000"}
	};

	public static int getLength() {
	  return ENTRY.length;
	}

	public static String getENTRY(int i, int j) {
	  return ENTRY[i][j];
	}

//	ID | Level | Bestellnr | bestDatum | einkauefer | gesBestellwert | Währung | aufmaßart | pos | posKurztext | Parent Id
	
	public static String getENTRYID(int i) {
	  return ENTRY[i][0];
	}

	public static String getENTRYTYPE(int i) {
	  return ENTRY[i][1];
	}

	public static String getBESTNR(int i) {
	  return ENTRY[i][2];
	}
	
	public static String getBESTDATE(int i) {
		return ENTRY[i][3];
	}
	
	public static String getPURCHASER(int i) {
		return ENTRY[i][4];
	}

	public static String getTOTALBESTVALUE(int i) {
	  return ENTRY[i][5];
	}
		
	public static String getCURRENCY(int i) {
	  return ENTRY[i][6];
	}
	
	public static String getAUFMASSART(int i) {
		return ENTRY[i][7];
	}
		
	public static String getPOS(int i) {
		return ENTRY[i][8];
	}
		
	public static String getPOSSHORTTEXT(int i) {
		return ENTRY[i][9];
	}
	
	public static String getPARENTID(int i) {
		return ENTRY[i][10];
	}
}
