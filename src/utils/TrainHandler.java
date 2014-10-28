package utils;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import domain.Train;

public class TrainHandler extends DefaultHandler {

    private List<Train> trains;
    private Train train;
    private StringBuffer buffer;
    
    public TrainHandler() {
        super();
    }

    // Détection de balise ouvrante
    @Override
    public void startElement(String uri, String localName, String qName,
            Attributes attributes) throws SAXException {
       
       if(qName.equals("passages")) {
           trains = new ArrayList<Train>();
       }
       else if(qName.equals("train")) {
           train = new Train();
       }
        
       buffer = new StringBuffer();
    }

    // Détection de balise fermante
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        if(qName.equals("passages")) { }
        else if(qName.equals("train")) {
            trains.add(train);
        }
        else if(qName.equals("date")) {
            train.setDate(buffer.toString());
            buffer = null;
        }
        else if(qName.equals("num")) {
            train.setNum(buffer.toString());
            buffer = null;
        }
        else if(qName.equals("miss")) {
            train.setMiss(buffer.toString());
            buffer = null;
        }
        else if(qName.equals("term")) {
            train.setTerm(buffer.toString());
            buffer = null;
        }
        else if(qName.equals("etat")) {
            train.setEtat(buffer.toString());
            buffer = null;
        }
        else
            throw new SAXException("Balise " + qName + " inconnue.");
    }

    // Détection de caractères
    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        String lecture = new String(ch, start, length);
        if(buffer != null)
            buffer.append(lecture);
    }

    // Retourne une liste de trains
    public List<Train> getTrains() {
        return trains;
    }
    
}