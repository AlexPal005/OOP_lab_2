package Parsers;

import Main.Beer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ExecutorStAX {
    private List<Beer> beers;
    private final String link;
    private Tags tags;
    public ExecutorStAX(List<Beer> beers, String link, Tags tags){
        this.beers = beers;
        this.link = link;
        this.tags = tags;
    }
    public void loadFromFile(){
        Beer beer = null;
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader reader = xmlInputFactory.createXMLEventReader(new FileInputStream(link));
            while (reader.hasNext()) {
                XMLEvent xmlEvent = reader.nextEvent();

                if (xmlEvent.isStartElement()) {
                    StartElement startElement = xmlEvent.asStartElement();

                    if (startElement.getName().getLocalPart().equals(tags.beer())) {
                        Attribute idAttr = startElement.getAttributeByName(new QName(tags.id()));
                        Attribute nameAttr = startElement.getAttributeByName(new QName(tags.name()));

                        if (idAttr != null && nameAttr != null) {
                           int id = Integer.parseInt(idAttr.getValue());
                            String name = nameAttr.getValue();
                            beer = new Beer(name, id, "", true, "", "",
                                    0, 0, 0);
                        }
                    }
                    setValues(startElement, reader, beer);

                }
                if (xmlEvent.isEndElement()) {
                    EndElement endElement = xmlEvent.asEndElement();
                    if (endElement.getName().getLocalPart().equals(tags.beer())) {
                        beers.add(beer);
                    }
                }
            }

        } catch (FileNotFoundException | XMLStreamException exc) {
            exc.printStackTrace();
        }
    }
    private void setValues(StartElement startElement, XMLEventReader reader, Beer beer ){
        try{
            XMLEvent xmlEvent;
            if (startElement.getName().getLocalPart().equals(tags.type())) {
                xmlEvent = reader.nextEvent();
                beer.setType(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(tags.alcohol())) {
                xmlEvent = reader.nextEvent();
                if(xmlEvent.asCharacters().getData().equals("No")){
                    beer.setAl(false);
                }
                else{
                    beer.setAl(true);
                }
            }
            else if (startElement.getName().getLocalPart().equals(tags.manufacturer())) {
                xmlEvent = reader.nextEvent();
                beer.setManufacturer(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(tags.ingredients())) {
                xmlEvent = reader.nextEvent();
                beer.setIngredients(xmlEvent.asCharacters().getData());
            }
            else if (startElement.getName().getLocalPart().equals(tags.numberTurns())) {
                xmlEvent = reader.nextEvent();
                beer.setNumberTurns(Double.parseDouble(xmlEvent.asCharacters().getData()));
            }
            else if (startElement.getName().getLocalPart().equals(tags.transparency())) {
                xmlEvent = reader.nextEvent();
                beer.setTransparency(Double.parseDouble(xmlEvent.asCharacters().getData()));
            }
            else if (startElement.getName().getLocalPart().equals(tags.nutritionalValue())) {
                xmlEvent = reader.nextEvent();
                beer.setNutritionalValue(Double.parseDouble(xmlEvent.asCharacters().getData()));
            }
        } catch (XMLStreamException e) {
            throw new RuntimeException(e);
        }
    }
}
