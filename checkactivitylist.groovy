/**
 * This little groovy script checks the existence of all files that are listed in the
 * file fileName.
 * This file can be generated by extracting the activity from dtr via Web UI for instance.
 * It has to look something like this (One line per file that´s in the activity):
 * /DCs/xyz.com/zzz/bpexd/wd/aufmasse/_comp/src/packages/com/xyz/zzz/bpexd/wd/aufmasse/materialrueckgabe/MaterialRueckbuchungView.wdview.xlf 
 * /DCs/xyz.com/zzz/bpexd/wd/model/_comp/src/packages/com/xyz/zzz/bpexd/wd/model/simpletypes/Lieferanschrift.dtsimpletype.xlf 
 * /DCs/xyz.com/zzz/bpexd/wd/main/_comp/src/packages/com/xyz/zzz/bpexd/wd/main/PopupVersion.wdview.xlf 
 * ...
 * 
 *
 * It prints each file thats in the activity but not in the filesystem to the console.
 * You then can delete this files from the activity. Otherwise you can´t check it into the dtr.
 */

def wdBaseDir = "/private/tmp/14731/C/Dokumente und Einstellungen/helmut/.dtc/52"
def fileName = "resources/activitylist.txt"

def file = new File(fileName)
def count = 0
def countNotExists = 0
def checkName
def checkFile
file.eachLine {line ->
	if(line =~ /.*xlf/) {
		count++
		checkName = wdBaseDir + line
		checkFile = new File(checkName)
		if(!checkFile.exists()) {
			countNotExists++
			println line
		}
	}
}

println "$count: $countNotExists"
