package sax;

import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.SAXException;
import resources.TestResource;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class ReadXMLWithSaxParser {

    private static TestResource testResource;
    private static String name;
    private static int age;

    public static TestResource readNewResourcesFromXML(String path) {

        DefaultHandler handler = new DefaultHandler() {
            boolean bName = false;
            boolean bAge = false;

            @Override
            public void startElement(String uri, String localName, String qName, org.xml.sax.Attributes attributes) throws SAXException {
                if (qName.equals("name")) {
                    bName = true;
                }

                if (qName.equals("age")) {
                    bAge = true;
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) {
                if (bName) {
                    ReadXMLWithSaxParser.name = new String(ch, start, length);
                    bName = false;
                }

                if (bAge) {
                    ReadXMLWithSaxParser.age = Integer.parseInt(new String(ch, start, length));
                    bAge = false;
                }
            }
        };

        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = factory.newSAXParser();
            saxParser.parse(new File(path), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        return ReadXMLWithSaxParser.testResource = new TestResource(name, age);
    }
}
