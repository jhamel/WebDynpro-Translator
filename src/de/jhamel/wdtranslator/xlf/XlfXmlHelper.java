package de.jhamel.wdtranslator.xlf;

import de.jhamel.wdtranslator.TechnicalException;
import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.xpath.XPath;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.List;

public class XlfXmlHelper {
    // constants

    private static Logger log = Logger.getLogger(XlfXmlHelper.class);

    // fields
    private SAXBuilder builder = new SAXBuilder();
    private XMLOutputter outputter;
    private File file;
    private Document doc;

    public XlfXmlHelper(File file) {
        this.file = file;
        Format format = Format.getRawFormat();
        format.setEncoding("UTF-8");
        outputter = new XMLOutputter(format);
        initJdomDocument(file);
    }

    private void initJdomDocument(File file) {
        try {
            doc = builder.build(file);
        } catch (JDOMException e) {
            throw new TechnicalException("Could not parse file '" + file.getAbsolutePath() + "'", e);
        } catch (IOException e) {
            throw new TechnicalException("Could not parse file '" + file.getAbsolutePath() + "'", e);
        }
    }

    public void replaceValueOfSourceElement(String id, String newValue) {
<<<<<<< HEAD
        if (newValue.contains("Währung")) {
            System.out.println("--" + file.getAbsolutePath());
=======
        if (newValue.contains("W�hrung")) {
			System.out.println("--"+file.getAbsolutePath());
>>>>>>> a5113337c194b85d7eb1b5dbd4f288d9862c4af0
        }
        Element element = findElementById(id);
        String currentTextValueInFile = element.getText();

        log.debug("Try storing '" + currentTextValueInFile + "' to '" + newValue + "' in " + file.getAbsolutePath());

        if (currentTextValueInFile.equals(newValue)) {
            log.debug("No change, skip storing.");
            return;
        }

        log.debug("Storing '" + currentTextValueInFile + "' to '" + newValue + "' in " + file.getAbsolutePath());
        element.setText(newValue);
        store();
    }

    public List<Element> findTransUnitElements() {
        return elementsByXpath("//trans-unit");
    }

    public Element findElementById(String key) {
        return elementByXpath("//trans-unit[@id='" + key + "']/source");
    }

    private Element elementByXpath(String xpathExpression) {
        List<Element> nodes = elementsByXpath(xpathExpression);
        if (nodes.size() != 1)
            throw new TechnicalException("Expected one element  xpath '" + xpathExpression + "' in '" + file.getAbsolutePath() + "', but got " + nodes.size() + " elements.");
        return nodes.get(0);
    }

    private List<Element> elementsByXpath(String xpathExpression) {
        XPath xpath = null;
        try {
            xpath = XPath.newInstance(xpathExpression);
            return xpath.selectNodes(doc);
        } catch (JDOMException e) {
            throw new TechnicalException("Could not find node for xpath '" + xpathExpression + "' in '" + file.getAbsolutePath() + "'", e);
        }
    }

    public void store() {
		log.trace("entering store");
        log.debug("file: " + file);
        try {

            outputter.output(doc, new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));

            //outputter.output(doc, System.out);
        } catch (IOException e) {
            throw new TechnicalException("Could not store '" + file.getAbsolutePath() + "'.", e);
        }
		log.trace("exiting store");
    }

}
