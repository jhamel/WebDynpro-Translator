#!/bin/sh

# set the input parameters here
csvFile=./resources/ExSP_Frontend_DO_NOT_CHANGE_FORMAT_out.csv
defaultcol=0
transcol=1 #nl=3, fr=2, en=1

# set classpath
cpath=
cpath=$cpath:lib/develop/hamcrest-all-1.1.jar
cpath=$cpath:lib/develop/junit-4.8.2.jar
cpath=$cpath:lib/develop/mockito-all-1.8.5.jar
cpath=$cpath:lib/deploy/commons-io-2.0.1.jar
cpath=$cpath:lib/deploy/jaxen.jar
cpath=$cpath:lib/deploy/jdom.jar
cpath=$cpath:lib/deploy/log4j-1.2.16.jar
cpath=$cpath:lib/deploy/ostermillerutils_1_07_00.jar
cpath=$cpath:lib/deploy/saxpath.jar
cpath=$cpath:lib/deploy/xalan.jar
cpath=$cpath:lib/deploy/xerces.jar
cpath=$cpath:lib/deploy/xml-apis.jar
cpath=$cpath:lib/deploy/commons-cli-1.2.jar
cpath=$cpath:out/production/WebDynpro-Translator


java -cp "$cpath" Main -a check -i "$csvFile" -d $defaultcol -t $transcol
