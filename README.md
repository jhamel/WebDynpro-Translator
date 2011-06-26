#Web Dynpro Translator

This application enables you to exchange the content of the resource files of a SAP Web Dynpro project with a
translation office, get the translated file back and import it into the resource files of the Web Dynpro project.

## How it works:
The program

* traverses all directories of one or more Web Dynpro projects (all projects it finds beneath the baseDirWebDynpro)
* extracts all phrases from all xlf-files
* removes all duplicate entries and
* save the results into an csv file

In the csv file there is one column for each language (<xxx>_<lang>.xlf) that was found. In the first column you find
the value of the original Web Dynpro project language (<xxx>.xlf)  
You then send the csv file to a translation office. It adds the translations for all not yet translated phrases and
sends the file back to you.  
You then use the program to

* import the altered csv file and
* update all xlf files in the Web Dynpro project (if the xlf files for the language haven´t been created yet the program
creates them.)

## Usage:
You can use the program by executing the Main class with a java runtime. For your convenience there are a few shell
script in the root folder of the project that you can use as a template.
### Using the java program

	usage: Main [-adhilotw]
         This application imports a csv file and then updates the xlf files of a WD project
         or extracts the items of the xlf files of a WD project and exports a csv file that can be send to a
         translation office
	-a,--action <action>                    defines the action which should be executed. <in> for import of a csv file and
                                      updating the WD projects xlf files, <out> for export of a csv file that can be.
	-c,--charset <Charset>                  Charset of the csv file that is read (a==in) or written (a==out). If not
                                         defined UTF-8 will be used.
                                         Allowed values are those that are supported by
                                         java.nio.charset.Charset.availableCharsets().
                                         Most common are ISO-8859-1, UTF-8
	-d,--defaultLangColumn <columnNumber>   0 based number of column in which the default language can be found
                                      REQUIRED when option a == in
	-h,--help                               show this help
	-i,--csvinputfile <csvInputFile>        filename of the csv file to import.
                                      REQUIRED when option a == in
	-l,--language <isoLanguage>             language that is to be used by the action. Use the iso language code, e.g. de,
                                      fr, uk, us
                                      REQUIRED when option a == in
	-o,--csvOutputFile <csvOutputFile>      filename including path of the csv file that is generated.
                                      REQUIRED when a == out
	-t,--transLangColumn <columnNumber>     0 based number of column in which the translated language can be found.
                                      REQUIRED when option a == in
	-w,--bWD <baseDirWebDynpro>             base directory of the Web Dynpro project(s). e.g.: C:/Dokumente und
                                      Einstellungen/your-user/.dtc/1/DCs/yourcompany.com/path1/path2/wd
                                      REQUIRED
### Using the shell scripts
There are three shell scripts available yet that make handling of the java program a bit easier.

* showhelp.sh
* importcsv.sh
* exportcsv.sh
#### showhelp.sh
Use this script to see the calling options of the program. You can also see this information in this description some lines above these.
#### importcsv.sh
Use this script to import a csv file into your Web Dynpro project(s), aka replace all xlf files of your Web Dynpro project(s) with the content
of the csv file  
At the beginning of the script you define the parameters of the java program like *baseDirWebDynpro*, *csvinputfile* etc.
#### exportcsv.sh
Use this script to export the content of the xlf files of your Web Dynpro project(s) into a csv file  
At the beginning of the script you define the parameters of the java program like *baseDirWebDynpro*, *csvoutputfile* ect.
