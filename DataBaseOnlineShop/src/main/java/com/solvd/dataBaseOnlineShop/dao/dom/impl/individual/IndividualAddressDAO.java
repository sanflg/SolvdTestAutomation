package com.solvd.dataBaseOnlineShop.dao.dom.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IIndividualAddressDAO;
import com.solvd.dataBaseOnlineShop.models.individual.IndividualAddress;
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
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IndividualAddressDAO implements IIndividualAddressDAO {
    private static final Logger logger = LogManager.getLogger(IndividualAddressDAO.class);
    private final String FILE = "src/main/resources/xml/individualaddresses.xml";

    public String getFILE() {
        return FILE;
    }

    @Override
    public void create(IndividualAddress individualaddress) {
        //Open needed resources
        Document doc = createDocument(FILE);

        Node parentNode = doc.getDocumentElement();
        Element childNode = doc.createElement("individualaddress");

        childNode.setAttribute("id",String.valueOf(individualaddress.getId()));
        parentNode.appendChild(childNode);

        Element number = doc.createElement("number");
        number.setTextContent(String.valueOf(individualaddress.getNumber()));
        childNode.appendChild(number);

        Element individual = doc.createElement("Individuals_id");
        individual.setTextContent(String.valueOf(individualaddress.getNumber()));
        childNode.appendChild(individual);

        Element address = doc.createElement("Addresses_id");
        address.setTextContent(String.valueOf(individualaddress.getNumber()));
        childNode.appendChild(address);

        saveChanges(doc, FILE);
    }

    @Override
    public IndividualAddress getByID(int id) {
        Document doc = createDocument(FILE);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individualaddress");

        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())==id)
                .collect(Collectors.toList());
        IndividualAddress individualaddress = new IndividualAddress();

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            individualaddress.setId(Integer.parseInt(node.getAttributes().item(0).getTextContent()));

            individualaddress.setNumber(
                    Integer.parseInt(node.getElementsByTagName("number").item(0).getTextContent()));
            individualaddress.setAddressId(
                    Integer.parseInt(node.getElementsByTagName("Addresses_id").item(0).getTextContent()));
            individualaddress.setIndividualId(
                    Integer.parseInt(node.getElementsByTagName("Individuals_id").item(0).getTextContent()));
        }
        return individualaddress;
    }

    @Override
    public void update(IndividualAddress individualaddress) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individualaddress");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== individualaddress.getId())
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            node.getElementsByTagName("number").item(0)
                    .setTextContent(String.valueOf(individualaddress.getNumber()));
            node.getElementsByTagName("Addresses_id").item(0)
                    .setTextContent(String.valueOf(individualaddress.getAddress()));
            node.getElementsByTagName("Individuals_id").item(0)
                    .setTextContent(String.valueOf(individualaddress.getIndividual()));
        }
        saveChanges(doc, FILE);
    }

    @Override
    public void delete(int id) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individualaddress");
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
