package com.solvd.dataBaseOnlineShop.dao.dom.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IPhoneNumberDAO;
import com.solvd.dataBaseOnlineShop.models.individual.PhoneNumber;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PhoneNumberDAO implements IPhoneNumberDAO {
    private static final Logger logger = LogManager.getLogger(PhoneNumberDAO.class);
    private final String FILE = "src/main/resources/xml/phonenumbers.xml";

    public String getFILE() {
        return FILE;
    }

    @Override
    public void create(PhoneNumber phonenumber) {
        //Open needed resources
        Document doc = createDocument(FILE);

        Node parentNode = doc.getDocumentElement();
        Element childNode = doc.createElement("phonenumber");

        childNode.setAttribute("id",String.valueOf(phonenumber.getId()));
        parentNode.appendChild(childNode);

        Element phone = doc.createElement("number");
        phone.setTextContent(String.valueOf(phonenumber.getNumber()));
        childNode.appendChild(phone);

        Element individual_id = doc.createElement("Individuals_id");
        individual_id.setTextContent(String.valueOf(phonenumber.getIndividualId()));
        childNode.appendChild(individual_id);

        saveChanges(doc, FILE);
    }

    @Override
    public PhoneNumber getByID(int id) {
        Document doc = createDocument(FILE);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("phonenumber");

        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())==id)
                .collect(Collectors.toList());
        PhoneNumber phonenumber = new PhoneNumber();

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            phonenumber.setId(Integer.parseInt(node.getAttributes().item(0).getTextContent()));

            phonenumber.setNumber(
                    Integer.parseInt(node.getElementsByTagName("number").item(0).getTextContent()));
            phonenumber.setIndividualId(
                    Integer.parseInt(node.getElementsByTagName("Individuals_id").item(0).getTextContent()));
        }
        return phonenumber;
    }

    @Override
    public void update(PhoneNumber phonenumber) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("phonenumber");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== phonenumber.getId())
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            node.getElementsByTagName("number").item(0)
                    .setTextContent(String.valueOf(phonenumber.getNumber()));
            node.getElementsByTagName("Individuals_id").item(0)
                    .setTextContent(String.valueOf(phonenumber.getIndividualId()));
        }
        saveChanges(doc, FILE);
    }

    @Override
    public void delete(int id) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("phonenumber");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== id)
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);
            root.removeChild(node);
        }
        saveChanges(doc, FILE);
    }

    public static synchronized Document createDocument(String file) {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        dbFactory.setIgnoringElementContentWhitespace(true);
        DocumentBuilder dBuilder;
        Document doc = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e);
        }
        return doc;
    }

    public static synchronized void saveChanges(Document doc, String file){
        Transformer transformer;
        StreamResult result;
        doc.normalize();
        DOMSource source = new DOMSource(doc);

        TransformerFactory tf = TransformerFactory.newInstance();
        tf.setAttribute("indent-number", 2);
        try {
            transformer = tf.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            result = new StreamResult(new FileOutputStream(file));
            transformer.transform(source, result);
        } catch (TransformerException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
