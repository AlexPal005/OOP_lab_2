package Parsers;

import Main.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;

public class ExecutorDOM {
    private List<Beer> beers;
    private final String xmlLink;
    public ExecutorDOM(List<Beer> beers, String xmlLink){
        this.beers = beers;
        this.xmlLink = xmlLink;
    }
    public void saveToFile(){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Beers");
            document.appendChild(root);
            for (int i = 0; i < beers.size(); i++) {
                Element beer = document.createElement("Beer");
                beer.setAttribute("id", Integer.toString(beers.get(i).getId()));
                beer.setAttribute("name", beers.get(i).getName());
                root.appendChild(beer);

                Element type = document.createElement("type");
                type.setTextContent(beers.get(i).getType());

                Element al = document.createElement("alcohol");
                if(beers.get(i).getAl()){
                    al.setTextContent("Так");
                }else{
                    al.setTextContent("Ні");
                }

                Element manufacturer = document.createElement("manufacturer");
                manufacturer.setTextContent(beers.get(i).getManufacturer());

                Element ingredients = document.createElement("ingredients");
                ingredients.setTextContent(beers.get(i).getIngredients());

                Element number_turns = document.createElement("number_turns");
                number_turns.setTextContent(Double.toString(beers.get(i).getNumberTurns()));

                Element transparency = document.createElement("transparency");
                transparency.setTextContent(Double.toString(beers.get(i).getTransparency()));

                Element nutritional_value = document.createElement("nutritional_value");
                nutritional_value.setTextContent(Double.toString(beers.get(i).getNutritionalValue()));

                beer.appendChild(type);
                beer.appendChild(al);
                beer.appendChild(manufacturer);
                beer.appendChild(ingredients);
                beer.appendChild(number_turns);
                beer.appendChild(transparency);
                beer.appendChild(nutritional_value);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(xmlLink));
            transformer.transform(domSource, streamResult);
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadFromFile(){
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlLink);

            NodeList nodelistBeers = document.getElementsByTagName("Beer");

            for (int i = 0; i < nodelistBeers.getLength(); i++) {
                Element beer = (Element) nodelistBeers.item(i);
                beers.add(createObjectBeer(beer));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Beer createObjectBeer(Element el){
        int id = Integer.parseInt(el.getAttribute("id"));
        String name = el.getAttribute("name");
        String type = getTagValue("type", el);
        String al = getTagValue("alcohol", el);
        boolean alVal;
        if(al.equals("Ні")){
            alVal = false;
        }
        else{
            alVal = true;
        }
        String manudacturer = getTagValue("manufacturer", el);
        String ingredients = getTagValue("ingredients", el);
        String numberTurns = getTagValue("number_turns",el);
        String transparency = getTagValue("transparency", el);
        String nutritionalValue = getTagValue("nutritional_value", el);

        return new Beer(name,id, type, alVal,manudacturer, ingredients,Double.parseDouble(numberTurns),
                Double.parseDouble(transparency), Double.parseDouble(nutritionalValue));
    }
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
