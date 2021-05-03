package com.solvd.dataBaseOnlineShop.dao.dom.impl.commerce;

import com.solvd.dataBaseOnlineShop.dao.interfaces.commerce.IProductDAO;
import com.solvd.dataBaseOnlineShop.models.commerce.Product;
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

public class ProductDAO implements IProductDAO {
    private static final Logger logger = LogManager.getLogger(ProductDAO.class);
    private final String FILE = "src/main/resources/xml/products.xml";

    public String getFILE() {
        return FILE;
    }

    @Override
    public void create(Product product) {
        //Open needed resources
        Document doc = createDocument(FILE);

        Node parentNode = doc.getDocumentElement();
        Element childNode = doc.createElement("product");

        childNode.setAttribute("id",String.valueOf(product.getId()));
        parentNode.appendChild(childNode);

        Element name = doc.createElement("name");
        name.setTextContent(product.getName());
        childNode.appendChild(name);

        Element price = doc.createElement("price");
        name.setTextContent(String.valueOf(product.getPrice()));
        childNode.appendChild(price);

        Element description = doc.createElement("description");
        name.setTextContent(product.getDescription());
        childNode.appendChild(description);

        Element category = doc.createElement("Categories_id");
        name.setTextContent(String.valueOf(product.getCategoryId()));
        childNode.appendChild(category);

        Element currency = doc.createElement("Currencies_id");
        name.setTextContent(String.valueOf(product.getCurrencyId()));
        childNode.appendChild(currency);

        Element supplier = doc.createElement("Suppliers_id");
        name.setTextContent(String.valueOf(product.getSupplierId()));
        childNode.appendChild(supplier);

        saveChanges(doc, FILE);
    }

    @Override
    public Product getByID(int id) {
        Document doc = createDocument(FILE);
        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("product");

        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())==id)
                .collect(Collectors.toList());
        Product product = new Product();

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            product.setId(Integer.parseInt(node.getAttributes().item(0).getTextContent()));

            product.setName(node.getElementsByTagName("name").item(0).getTextContent());
            product.setPrice(
                    Double.parseDouble(node.getElementsByTagName("price").item(0).getTextContent()));
            product.setDescription(node.getElementsByTagName("description").item(0).getTextContent());
            product.setCategoryId(
                    Integer.parseInt(node.getElementsByTagName("Categories_id").item(0).getTextContent()));
            product.setCurrencyId(
                    Integer.parseInt(node.getElementsByTagName("Currencies_id").item(0).getTextContent()));
            product.setSupplierId(
                    Integer.parseInt(node.getElementsByTagName("Suppliers_id").item(0).getTextContent()));
        }
        return product;
    }

    @Override
    public void update(Product product) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("product");
        List<Node> nodes = IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item)
                .filter(n -> Integer.parseInt(n.getAttributes().item(0).getTextContent())== product.getId())
                .collect(Collectors.toList());

        if(nodes.get(0)!=null){
            Element node = (Element) nodes.get(0);

            node.getElementsByTagName("name").item(0)
                    .setTextContent(product.getName());
            node.getElementsByTagName("price").item(0)
                    .setTextContent(String.valueOf(product.getPrice()));
            node.getElementsByTagName("description").item(0)
                    .setTextContent(product.getDescription());
            node.getElementsByTagName("Categories_id").item(0)
                    .setTextContent(String.valueOf(product.getCategoryId()));
            node.getElementsByTagName("Currencies_id").item(0)
                    .setTextContent(String.valueOf(product.getCurrencyId()));
            node.getElementsByTagName("Suppliers_id").item(0)
                    .setTextContent(String.valueOf(product.getSupplierId()));
        }
        saveChanges(doc, FILE);
    }

    @Override
    public void delete(int id) {
        Document doc = createDocument(FILE);

        Element root = doc.getDocumentElement();
        NodeList nodeList = root.getElementsByTagName("product");
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
