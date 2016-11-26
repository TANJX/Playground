package cc.isotopestudio.datecalculator.xml;
/*
 * Created by Mars Tan on 11/26/2016.
 * Copyright ISOTOPE Studio
 */

import cc.isotopestudio.datecalculator.ISODate;
import org.w3c.dom.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DOMXML {
    private Element rootElement;
    private Document doc;
    private File file;
    private static final String path = "C:\\Users\\Mars Tan\\Desktop\\xml\\Date.xml";

    public DOMXML() {
        init();
    }

    public void addRecord(String name, ISODate date) {
        Element record = doc.createElement("record");
        rootElement.appendChild(record);

        Element nameE = doc.createElement("name");
        nameE.appendChild(doc.createTextNode(name));
        record.appendChild(nameE);

        Element year = doc.createElement("year");
        year.appendChild(doc.createTextNode("" + date.getYear()));
        record.appendChild(year);

        Element month = doc.createElement("month");
        month.appendChild(doc.createTextNode("" + date.getMonth()));
        record.appendChild(month);

        Element day = doc.createElement("day");
        day.appendChild(doc.createTextNode("" + date.getDay()));
        record.appendChild(day);

        saveFile();
    }

    public ISODate getDateByName(String name) {
        NodeList nList = rootElement.getChildNodes();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(name)) {
                    int year = getInt(eElement, "year");
                    int month = getInt(eElement, "month");
                    int day = getInt(eElement, "day");
                    return new ISODate(year, month, day);
                }
            }
        }
        return null;
    }

    public List<String> getDates() {
        List<String> dates = new ArrayList<>();
        NodeList nList = rootElement.getChildNodes();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                dates.add(eElement.getElementsByTagName("name").item(0).getTextContent());
            }
        }
        return dates;
    }

    private int getInt(Element element, String field) {
        return Integer.parseInt(element.getElementsByTagName(field).item(0).getTextContent());
    }

    public void init() {
        file = new File(path);
        if (!file.exists()) {
            createFile();
            saveFile();
        } else {
            parseFile();
        }
    }

    private void createFile() {
        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder =
                    dbFactory.newDocumentBuilder();
            doc = dBuilder.newDocument();
            // root element
            rootElement = doc.createElement("dates");
            doc.appendChild(rootElement);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseFile() {
        try {
            DocumentBuilderFactory dbFactory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            rootElement = doc.getDocumentElement();
            System.out.println("parse existing file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveFile() {
        try {
            // write the content into xml file
            TransformerFactory transformerFactory =
                    TransformerFactory.newInstance();
            Transformer transformer =
                    transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);
            StreamResult result =
                    new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("File saved");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
