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

    public ExecutorSAX(List<Beer> beers) {
        this.beers = beers;
    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if (qName.equals("Beer")) {
            String name = attributes.getValue("name");
            int id = Integer.parseInt(attributes.getValue("id"));
            currBeer = new Beer(name, id, "", true, "", "",
                    0, 0, 0);
            beers.add(currBeer);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("type")) {
            currBeer.setType(elementValue);
        }
        if (qName.equalsIgnoreCase("alcohol")) {
            if(elementValue.equals("Ні")){
                currBeer.setAl(false);
            }
            else{
                currBeer.setAl(true);
            }
        }
        if (qName.equalsIgnoreCase("manufacturer")) {
            currBeer.setManufacturer(elementValue);
        }
        if (qName.equalsIgnoreCase("ingredients")) {
            currBeer.setIngredients(elementValue);
        }
        if (qName.equalsIgnoreCase("number_turns")) {
            currBeer.setNumberTurns(Double.parseDouble(elementValue));
        }
        if (qName.equalsIgnoreCase("transparency")) {
            currBeer.setTransparency(Double.parseDouble(elementValue));
        }
        if (qName.equalsIgnoreCase("nutritional_value")) {
            currBeer.setNutritionalValue(Double.parseDouble(elementValue));
        }


    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        elementValue = new String(ch, start, length);
    }
}
