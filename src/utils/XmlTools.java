package utils;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import domain.Train;

public class XmlTools {

    /**
     * Crée un parser XML SAX.
     * @return un parser XML SAX.
     */
    private static SAXParser createParser() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = null;
        try {
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return saxParser;
    }

    /**
     * Parse le fichier XML.
     * @param saxParser le parseur XML SAX permettant de parser le fichier XML.
     */
    private static void parse(SAXParser saxParser, DefaultHandler handler, String xml) {
        try {
            // Parsing du XML
            saxParser.parse(new InputSource(new StringReader(xml)), handler);
        }
        catch (SAXParseException e) {
            e.printStackTrace();
        } 
        catch (IOException | SAXException e) {
            e.printStackTrace();
        } 
    }

    /**
     * Permet dobtenir une liste de trains a partir dun xml.
     * @param xml la chaine de caracteres xml.
     * @return une liste de trains.
     */
    public static List<Train> XmlToTrains(String xml) {
        
        SAXParser saxParser = createParser();
        DefaultHandler handler = new TrainHandler();
        parse(saxParser, handler, xml);
        return ((TrainHandler)handler).getTrains();
                
    }
    
    
}
