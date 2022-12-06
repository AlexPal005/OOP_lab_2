package TESTS;
import Main.Beer;
import Parsers.ExecutorDOM;
import Parsers.ExecutorSAX;
import Parsers.ExecutorStAX;
import Parsers.Tags;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
public class TestParser {
    private final String xmlLink;
    private List<Beer> beersResult;
    private Tags tags;
    public TestParser(){
        this.xmlLink ="src\\TESTS\\test.xml";
        createBeersResult();
        tags = new Tags("Beers", "Beer","id", "name", "type", "alcohol",
                "manufacturer", "ingredients","number_turns",
                "transparency", "nutritional_value");
    }
    private void createBeersResult(){
        beersResult= new ArrayList<>();
        Beer beer1 = new Beer("Львівське", 1, "Світле", true, "Львів фабрика",
                "Вода, солод, дріджі", 3.4 , 10, 500);
        Beer beer2 = new Beer("Чернігівське", 2, "Міцне", true, "Київ фабрика",
                "Вода, солод, цукор", 5, 15, 700);
        beersResult.add(beer1);
        beersResult.add(beer2);
    }
    @Test
    public void testLoadFromFileDOM(){
        List<Beer> beers = new ArrayList<>();
        ExecutorDOM executorDOM = new ExecutorDOM(beers,xmlLink,tags);
        executorDOM.loadFromFile();
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
    @Test
    public void testLoadFromFileSAX(){
        List<Beer> beers = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ExecutorSAX handler = new ExecutorSAX(beers,tags);
            parser.parse(new File(xmlLink), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
    @Test
    public void testLoadFromFileStAX(){
        List<Beer> beers = new ArrayList<>();
        ExecutorStAX executorStAX = new ExecutorStAX(beers,xmlLink,tags);
        executorStAX.loadFromFile();
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
}
