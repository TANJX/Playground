package cc.isotopestudio.datecalculator.xml;

import cc.isotopestudio.datecalculator.ISODate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLImpl implements XMLInterface {
    private Document document;
    private String fileName;

    public void init() {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            this.document = builder.newDocument();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    private Element root;

    public void createXML(String fileName) {
        this.fileName = fileName;
        root = this.document.createElement("days");
        this.document.appendChild(root);
        save();
    }

    public void save() {
        TransformerFactory tf = TransformerFactory.newInstance();
        try {
            Transformer transformer = tf.newTransformer();
            DOMSource source = new DOMSource(document);
            transformer.setOutputProperty(OutputKeys.ENCODING, "gb2312");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
            System.out.println("XML file updated!");
        } catch (IllegalArgumentException | TransformerException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    public void addRecord(String name, ISODate date) {
        Element record = this.document.createElement("record");
        Element nameE = this.document.createElement("name");
        nameE.appendChild(this.document.createTextNode(name));
        record.appendChild(nameE);
        Element year = this.document.createElement("year");
        year.appendChild(this.document.createTextNode("" + date.getYear()));
        record.appendChild(year);
        Element month = this.document.createElement("month");
        month.appendChild(this.document.createTextNode("" + date.getMonth()));
        record.appendChild(month);
        Element day = this.document.createElement("day");
        day.appendChild(this.document.createTextNode("" + date.getDay()));
        record.appendChild(day);
        root.appendChild(record);
        save();
    }

    public void parserXML(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            NodeList employees = document.getChildNodes();
            for (int i = 0; i < employees.getLength(); i++) {
                Node employee = employees.item(i);
                NodeList employeeInfo = employee.getChildNodes();
                for (int j = 0; j < employeeInfo.getLength(); j++) {
                    Node node = employeeInfo.item(j);
                    NodeList employeeMeta = node.getChildNodes();
                    for (int k = 0; k < employeeMeta.getLength(); k++) {
                        System.out.println(
                                employeeMeta.item(k).getNodeName() + ":" + employeeMeta.item(k).getTextContent());
                    }
                }
            }
            System.out.println("Encoding finished");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }
}