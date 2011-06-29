#!/bin/sh

# set the input parameters here
#wdBaseDir=~/Downloads/wdtrans/
wdBaseDir="/private/tmp/14731/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd/"
csvOutputFile=./resources/ExSP_Frontend_DO_NOT_CHANGE_FORMAT_out.csv
language=nl


cpath=lib/develop/hamcrest-all-1.1.jar:lib/develop/junit-4.8.2.jar:lib/develop/mockito-all-1.8.5.jar:lib/deploy/commons-io-2.0.1.jar:lib/deploy/jaxen.jar:lib/deploy/jdom.jar:lib/deploy/log4j-1.2.16.jar:lib/deploy/ostermillerutils_1_07_00.jar:lib/deploy/saxpath.jar:lib/deploy/xalan.jar:lib/deploy/xerces.jar:lib/deploy/xml-apis.jar:lib/deploy/commons-cli-1.2.jar:out/production/WebDynpro-Translator


java -cp "$cpath" Main -a out -w "$wdBaseDir" -o "$csvOutputFile" -l $language
