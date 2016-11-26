package basic.xml;/*
 * Created by Mars Tan on 11/26/2016.
 * Copyright ISOTOPE Studio
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;

import java.io.File;

public class DOMXML {
    private Element rootElement;
    private Document doc;
    private File file;
    private static final String path = "C:\\Users\\Mars Tan\\Desktop\\xml\\Mars.xml";

    public DOMXML() {
        init();
    }

    public void addRecord(String name, int age) {
        //  student element
        Element student = doc.createElement("student");
        rootElement.appendChild(student);

        // setting attribute to element
        Attr attr = doc.createAttribute("campus");
        attr.setValue("SZMS");
        student.setAttributeNode(attr);

        // name element
        Element nameElement = doc.createElement("name");
        nameElement.appendChild(
                doc.createTextNode(name));
        student.appendChild(nameElement);

        Element ageElement = doc.createElement("age");
        ageElement.appendChild(doc.createTextNode(age + ""));
        student.appendChild(ageElement);
        saveFile();
    }

    public int getAgeByName(String name) {
        NodeList nList = rootElement.getChildNodes();
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getElementsByTagName("name").item(0).getTextContent().equals(name))
                    return Integer.parseInt(eElement.getElementsByTagName("age").item(0).getTextContent());
            }
        }
        return -1;
    }

    private void init() {
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
            rootElement = doc.createElement("students");
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
            System.out.println("Root element :"
                    + doc.getDocumentElement().getNodeName());
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
            DOMSource source = new DOMSource(doc);
            StreamResult result =
                    new StreamResult(file);
            transformer.transform(source, result);
            // Output to console for testing
            StreamResult consoleResult =
                    new StreamResult(System.out);
            transformer.transform(source, consoleResult);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
