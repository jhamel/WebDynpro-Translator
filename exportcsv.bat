@echo off

REM * set the input parameters here
REM wdBaseDir=~/Downloads/wdtrans/
set wdBaseDir="/private/tmp/14731/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd/"
set csvOutputFile=./resources/ExSP_Frontend_DO_NOT_CHANGE_FORMAT_out.csv
set language=nl

REM * set classpath
set cpath=
set cpath=%cpath%;lib/develop/hamcrest-all-1.1.jar
set cpath=%cpath%;lib/develop/junit-4.8.2.jar
set cpath=%cpath%;lib/develop/mockito-all-1.8.5.jar
set cpath=%cpath%;lib/deploy/commons-io-2.0.1.jar
set cpath=%cpath%;lib/deploy/jaxen.jar
set cpath=%cpath%;lib/deploy/jdom.jar
set cpath=%cpath%;lib/deploy/log4j-1.2.16.jar
set cpath=%cpath%;lib/deploy/ostermillerutils_1_07_00.jar
set cpath=%cpath%;lib/deploy/saxpath.jar
set cpath=%cpath%;lib/deploy/xalan.jar
set cpath=%cpath%;lib/deploy/xerces.jar
set cpath=%cpath%;lib/deploy/xml-apis.jar
set cpath=%cpath%;lib/deploy/commons-cli-1.2.jar
set cpath=%cpath%;out/production/WebDynpro-Translator


java -cp "%cpath%" Main -a out -w "%wdBaseDir%" -o "%csvOutputFile%" -l %language%
