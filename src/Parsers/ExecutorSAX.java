package Parsers;

import Main.Beer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.List;

public class ExecutorSAX extends DefaultHandler {
    private List<Beer> beers;
    private Beer currBeer;
    private String elementValue;
    private  Tags tags;

    public ExecutorSAX(List<Beer> beers, Tags tags) {
        this.beers = beers;
        this.tags = tags;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals(tags.beer())) {
            String name = attributes.getValue(tags.name());
            int id = Integer.parseInt(attributes.getValue(tags.id()));
            currBeer = new Beer(name, id, "", true, "", "",
                    0, 0, 0);
            beers.add(currBeer);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase(tags.type())) {
            currBeer.setType(elementValue);
        }
        if (qName.equalsIgnoreCase(tags.alcohol())) {
            if(elementValue.equals("No")){
                currBeer.setAl(false);
            }
            else{
                currBeer.setAl(true);
            }
        }
        if (qName.equalsIgnoreCase(tags.manufacturer())) {
            currBeer.setManufacturer(elementValue);
        }
        if (qName.equalsIgnoreCase(tags.ingredients())) {
            currBeer.setIngredients(elementValue);
        }
        if (qName.equalsIgnoreCase(tags.numberTurns())) {
            currBeer.setNumberTurns(Double.parseDouble(elementValue));
        }
        if (qName.equalsIgnoreCase(tags.transparency())) {
            currBeer.setTransparency(Double.parseDouble(elementValue));
        }
        if (qName.equalsIgnoreCase(tags.nutritionalValue())) {
            currBeer.setNutritionalValue(Double.parseDouble(elementValue));
        }
    }
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }
}
