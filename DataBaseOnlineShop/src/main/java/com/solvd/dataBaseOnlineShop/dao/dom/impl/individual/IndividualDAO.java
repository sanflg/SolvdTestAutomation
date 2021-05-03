package com.solvd.dataBaseOnlineShop.dao.dom.impl.individual;

import com.solvd.dataBaseOnlineShop.dao.interfaces.individual.IIndividualDAO;
import com.solvd.dataBaseOnlineShop.models.individual.Individual;
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

public class IndividualDAO implements IIndividualDAO {
    private static final Logger logger = LogManager.getLogger(IndividualDAO.class);
    private final String FILE = "src/main/resources/xml/individuals.xml";

    public String getFILE() {
        return FILE;
    }

    @Override
    public void create(Individual individual) {
        //Open needed resources
        Document doc = createDocument(FILE);

        Node parentNode = doc.getDocumentElement();
        Element childNode = doc.createElement("individual");

        childNode.setAttribute("id",String.valueOf(individual.getId()));
        parentNode.appendChild(childNode);

        Element username = doc.createElement("username");
        username.setTextContent(individual.getUsername());
        childNode.appendChild(username);

        Element password = doc.createElement("password");
        password.setTextContent(individual.getPassword());
        childNode.appendChild(password);

        Element email = doc.createElement("email");
        email.setTextContent(individual.getEmail());
        childNode.appendChild(email);

        Element first_name = doc.createElement("first_name");
        first_name.setTextContent(String.valueOf(individual.getFirstName()));
        childNode.appendChild(first_name);

        Element last_name = doc.createElement("last_name");
        last_name.setTextContent(String.valueOf(individual.getLastName()));
        childNode.appendChild(last_name);

        Element language = doc.createElement("Languages_id");
        language.setTextContent(String.valueOf(individual.getLanguage()));
        childNode.appendChild(language);

        Element date = doc.createElement("birth_date");
        date.setTextContent(String.valueOf(individual.getDate()));
        childNode.appendChild(date);

        saveChanges(doc, FILE);
    }

    @Override
    public Individual getByID(int id) {
        Document doc = createDocument(FILE);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individual");

        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())==id)
                .collect(Collectors.toList());
        Individual individual = new Individual();

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            individual.setId(Integer.parseInt(node.getAttributes().item(0).getTextContent()));

            individual.setUsername(node.getElementsByTagName("username").item(0).getTextContent());
            individual.setPassword(node.getElementsByTagName("password").item(0).getTextContent());
            individual.setEmail(node.getElementsByTagName("email").item(0).getTextContent());
            individual.setFirstName(node.getElementsByTagName("first_name").item(0).getTextContent());
            individual.setLastName(node.getElementsByTagName("last_name").item(0).getTextContent());
            individual.setDate(
                    Date.valueOf(node.getElementsByTagName("birth_date").item(0).getTextContent()));
            individual.setLanguageId(
                    Integer.parseInt(node.getElementsByTagName("Languages_id").item(0).getTextContent()));
        }
        return individual;
    }

    @Override
    public void update(Individual individual) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individual");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== individual.getId())
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            node.getElementsByTagName("username").item(0)
                    .setTextContent(individual.getUsername());
            node.getElementsByTagName("password").item(0)
                    .setTextContent(individual.getPassword());
            node.getElementsByTagName("email").item(0)
                    .setTextContent(individual.getEmail());
            node.getElementsByTagName("first_name").item(0)
                    .setTextContent(individual.getFirstName());
            node.getElementsByTagName("last_name").item(0)
                    .setTextContent(individual.getLastName());
            node.getElementsByTagName("birth_date").item(0)
                    .setTextContent(String.valueOf(individual.getDate()));
            node.getElementsByTagName("Languages_id").item(0)
                    .setTextContent(String.valueOf(individual.getLanguage()));
        }
        saveChanges(doc, FILE);
    }

    @Override
    public void delete(int id) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("individual");
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
