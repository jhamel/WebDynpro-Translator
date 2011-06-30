@echo off

REM * set the input parameters here
set wdBaseDir="/private/tmp/14731/C/Dokumente und Einstellungen/helmut/.dtc/52/DCs/eonis.com/eea/bpexd/wd/"
REM wdBaseDir=~/Downloads/wdtrans/
set csvInputFile=./resources/ExSP_Frontend_DO_NOT_CHANGE_FORMAT.csv
set language=en #nl fr en
set defaultcol=0
set transcol=1 #nl=3, fr=2, en=1

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


java -cp "%cpath%" Main -a in -w "%wdBaseDir%" -i "%csvInputFile%" -l %language% -d %defaultcol% -t %transcol%
