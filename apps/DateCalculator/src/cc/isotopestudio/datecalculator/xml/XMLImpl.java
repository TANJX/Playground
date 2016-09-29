package cc.isotopestudio.datecalculator.xml;

import cc.isotopestudio.datecalculator.ISODate;
import cc.isotopestudio.datecalculator.record.Record;
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
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XMLImpl {
    private static Document document;
    private static String fileName;

    static DocumentBuilder builder;
    static DocumentBuilderFactory factory;

    public static void init() {
        try {
            factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
            try {
                document = builder.parse(new FileInputStream(new File("data.xml")));
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }
            root = document.getDocumentElement();
            if (root == null || !root.getNodeName().equals("records")) {
                createXML("data.yml");
            }
            save();
        } catch (ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }
    }

    private static Element root;

    public static void createXML(String fileName) {
        XMLImpl.fileName = fileName;
        root = document.createElement("records");
        document.appendChild(root);
        save();
    }

    public static void save() {
        writeXML(document, "data.xml");
    }

    private static void writeXML(Document document, String filename) {
        try {
            builder = factory.newDocumentBuilder();
            //Document document = builder.parse(new File("E:\\testFiles\\test.xml"));
            document.normalize();

            /** 将document中的内容写入文件中 */
            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            //编码
            DOMSource source = new DOMSource(document);
            PrintWriter pw = new PrintWriter(new FileOutputStream(filename));
            StreamResult result = new StreamResult(pw);
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void addRecord(String name, ISODate date) {
        Element record = document.createElement("record");
        Element nameE = document.createElement("name");
        nameE.appendChild(document.createTextNode(name));
        record.appendChild(nameE);
        Element year = document.createElement("year");
        year.appendChild(document.createTextNode("" + date.getYear()));
        record.appendChild(year);
        Element month = document.createElement("month");
        month.appendChild(document.createTextNode("" + date.getMonth()));
        record.appendChild(month);
        Element day = document.createElement("day");
        day.appendChild(document.createTextNode("" + date.getDay()));
        record.appendChild(day);
        root.appendChild(record);
        save();
    }

    public void parserXML(String fileName) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(fileName);
            NodeList records = document.getChildNodes();
            for (int i = 0; i < records.getLength(); i++) {
                Node record = records.item(i);
                NodeList recordInfo = record.getChildNodes();
                for (int j = 0; j < recordInfo.getLength(); j++) {
                    Node detail = recordInfo.item(j);
                    NodeList detailInfo = detail.getChildNodes();
                    for (int k = 0; k < detailInfo.getLength(); k++) {
                        System.out.println(
                                detailInfo.item(k).getNodeName() + ":" + detailInfo.item(k).getTextContent());
                    }
                }
            }
            System.out.println("Encoding finished");
        } catch (ParserConfigurationException | IOException | SAXException e) {
            System.out.println(e.getMessage());
        }
    }

    public static List<Record> getRecords() throws Exception {
        List<Record> list = new ArrayList<>();
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        Document document = builder.parse(new File("data.xml"));
//        Element element = document.getDocumentElement();

        NodeList records = document.getChildNodes();
        for (int i = 0; i < records.getLength(); i++) {

            Node record = records.item(i);
            NodeList recordInfo = record.getChildNodes();
            for (int j = 0; j < recordInfo.getLength(); j++) {
                int year = 0, month = 0, day = 0;
                String name = null;
                Node detail = recordInfo.item(j);
                NodeList detailInfo = detail.getChildNodes();
                for (int k = 0; k < detailInfo.getLength(); k++) {
                    if ("name".equals(detailInfo.item(k).getNodeName())) {
                        name = detailInfo.item(k).getFirstChild().getNodeValue();
                    } else if ("year".equals(detailInfo.item(k).getNodeName())) {
                        year = Integer.parseInt(detailInfo.item(k).getFirstChild().getNodeValue());
                    } else if ("month".equals(detailInfo.item(k).getNodeName())) {
                        month = Integer.parseInt(detailInfo.item(k).getFirstChild().getNodeValue());
                    } else if ("day".equals(detailInfo.item(k).getNodeName())) {
                        day = Integer.parseInt(detailInfo.item(k).getFirstChild().getNodeValue());
                    }
                }
                if (name != null)
                    list.add(new Record(name, year, month, day));
            }
        }
        return list;
    }
}