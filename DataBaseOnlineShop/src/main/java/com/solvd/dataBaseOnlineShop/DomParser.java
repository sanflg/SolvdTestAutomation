package com.solvd.dataBaseOnlineShop;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class DomParser {
    private static final Logger logger = LogManager.getLogger(DomParser.class);

    public static void main(String[] args) {

        try {
            File inputFile = new File("src/main/resources/xml/addresses.xml");

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("address");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                logger.info("\nCurrent Element  :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    logger.info("id: " + eElement.getAttribute("id"));
                    logger.info("Name : "
                            + eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    logger.info("Cities_id : "
                            + eElement
                            .getElementsByTagName("Cities_id")
                            .item(0)
                            .getTextContent());
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            logger.error(e);
        }
    }
}