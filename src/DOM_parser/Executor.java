package DOM_parser;

import Main.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.List;

public class Executor {
    private List<Beer> beers;
    public Executor(List<Beer> beers){
        this.beers = beers;
    }
    public void save_to_file(){
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
                number_turns.setTextContent(Double.toString(beers.get(i).getNumber_turns()));

                Element transparency = document.createElement("transparency");
                transparency.setTextContent(Double.toString(beers.get(i).getTransparency()));

                Element nutritional_value = document.createElement("nutritional_value");
                nutritional_value.setTextContent(Double.toString(beers.get(i).getNutritional_value()));

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
            StreamResult streamResult = new StreamResult(new File("src\\Beers.xml"));
            transformer.transform(domSource, streamResult);
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public void load_from_file(){
        try {
            //SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            //Schema s = sf.newSchema(new File("src\\Part_1\\Airport.xsd"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            //factory.setValidating(false);
            //factory.setSchema(s);
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            //builder.setErrorHandler(new SimpleErrorHandler());
            Document document = builder.parse("src\\Beers.xml");

            NodeList nodelist_beers = document.getElementsByTagName("Beer");

            for (int i = 0; i < nodelist_beers.getLength(); i++) {
                Element beer = (Element) nodelist_beers.item(i);
                beers.add(create_object_beer(beer));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Beer create_object_beer(Element el){
        int id = Integer.parseInt(el.getAttribute("id"));
        String name = el.getAttribute("name");
        String type = getTagValue("type", el);
        String al = getTagValue("alcohol", el);
        boolean al_val;
        if(al.equals("Ні")){
            al_val = false;
        }
        else{
            al_val = true;
        }
        String manudacturer = getTagValue("manufacturer", el);
        String ingredients = getTagValue("ingredients", el);
        String number_turns = getTagValue("number_turns",el);
        String transparency = getTagValue("transparency", el);
        String nutritional_value = getTagValue("nutritional_value", el);

        return new Beer(name,id, type, al_val,manudacturer, ingredients,Double.parseDouble(number_turns),
                Double.parseDouble(transparency), Double.parseDouble(nutritional_value));
    }
    private String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodeList.item(0);
        return node.getNodeValue();
    }
}
