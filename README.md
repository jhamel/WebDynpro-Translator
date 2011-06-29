#Web Dynpro Translator

This application enables you to exchange the content of the resource files of a SAP Web Dynpro project with a
translation office, get the translated file back and import it into the resource files of the Web Dynpro project.

## How it works:
The program

* traverses all directories of one or more Web Dynpro projects (all projects it finds beneath the baseDirWebDynpro)
* extracts all phrases from all xlf-files
* removes all duplicate entries and (from the internal cache, not from the xlf files)
* save the results into an csv file

In the csv file there is one column for each language (&lt;xxx>_*&lt;lang>*.xlf) that was found. In the first column you find
the value of the original Web Dynpro project language (&lt;xxx>.xlf)  
You then send the csv file to a translation office. It adds the translations for all not yet translated phrases and
sends the file back to you.  
You then use the program to

* import the altered csv file and
* update all xlf files in the Web Dynpro project (if the xlf files for the language haven´t been created yet the program
creates them.)

### Prerequisites
You must have checked out all .xlf files of your Web Dynpro projects from your DTR/NWDI. The best way to assure this is
to checkout the subtree of the src/packages folder(s). 

## Usage:
You can use the program by executing the Main class with a java runtime. For your convenience there are a few shell
script in the root folder of the project that you can use as a template.
### Using the java program

	usage: Main [-adhilotw]
            This application imports a csv file and then updates the xlf files of a WD project
            or extracts the items of the xlf files of a WD project and exports a csv file that can be send to a
            translation office
	 -a,--action <action>                    defines the action to be executed.
                                         <in> for import of a csv file and updating the WD projects xlf files,
                                         <out> for export of a csv file that can be.
                                         <check> to check the translationREQUIRED
	 -c,--charset <Charset>                  Charset of the csv file that is read (a==in), written (a==out) or checked
                                         (a==check). If not defined UTF-8 will be used.
                                         Allowed values are those that are supported by
                                         java.nio.charset.Charset.availableCharsets().
                                         Most common are ISO-8859-1, UTF-8
	 -d,--defaultLangColumn <columnNumber>   0 based number of column in which the default language can be found
                                         REQUIRED when option a==in or a==check
	 -h,--help                               show this help
	 -i,--csvinputfile <csvInputFile>        filename of the csv file to import.
                                         REQUIRED when option a==in or a==check
	 -l,--language <isoLanguage>             language that is to be used by the action. Use the iso language code, e.g. de,
                                         fr, uk, us
                                         REQUIRED when option a == in
	 -o,--csvOutputFile <csvOutputFile>      filename including path of the csv file that is generated.
                                         REQUIRED when a==out
	 -t,--transLangColumn <columnNumber>     0 based number of column in which the translated language can be found.
                                         REQUIRED when option a==in or a==check
	 -w,--bWD <baseDirWebDynpro>             base directory of the Web Dynpro project(s). e.g.: C:/Dokumente und
                                         Einstellungen/your-user/.dtc/1/DCs/yourcompany.com/path1/path2/wd
                                         REQUIRED
### Using the shell scripts
There are three shell scripts available yet that make handling of the java program a bit easier.

* showhelp.sh
* importcsv.sh
* exportcsv.sh
* checktranslations.sh

#### showhelp.sh
Use this script to see the calling options of the program. You can also see this information in this description some lines above these.
#### importcsv.sh
Use this script to import a csv file into your Web Dynpro project(s), aka replace all xlf files of your Web Dynpro project(s) with the content
of the csv file  
At the beginning of the script you define the parameters of the java program like *baseDirWebDynpro*, *csvinputfile* etc.
#### exportcsv.sh
Use this script to export the content of the xlf files of your Web Dynpro project(s) into a csv file  
At the beginning of the script you define the parameters of the java program like *baseDirWebDynpro*, *csvoutputfile* ect.
#### checktranslation.sh
Use this script to check the translation in your xlf resp. csv file(s)

Usage scenario:  
After importing a translated csv file into your Web Dynpro project you should export one and check this
exported file with this script.  
It compares two columns of the csv file. Usually the column of the projects default language
and one translated language.
If it finds equal values in those columns it protocols those
A protocol entry is also written if the value of the translation language is empty.

## Troubleshooting
If you get any error messages please have a detailed look at the message.  
Some of the most common errors are listed below:

* Often you forgot to check the xlf file out of your DTR/NWDI. You then get an error message that contains the phrase "Permission denied"
* While importing a csv file you might get an error like this "Expected one element  xpath '//trans-unit[@id='...". You then should delete the mentioned file and try importing again

