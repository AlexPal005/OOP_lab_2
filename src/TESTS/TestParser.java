package TESTS;
import Main.Beer;
import Parsers.ExecutorDOM;
import Parsers.ExecutorSAX;
import Parsers.ExecutorStAX;
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
    public TestParser(){
        this.xmlLink ="src\\TESTS\\test.xml";
        createBeersResult();
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
    public void testDOM(){
        List<Beer> beers = new ArrayList<>();
        ExecutorDOM executorDOM = new ExecutorDOM(beers,xmlLink);
        executorDOM.loadFromFile();
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
    @Test
    public void testSAX(){
        List<Beer> beers = new ArrayList<>();
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            ExecutorSAX handler = new ExecutorSAX(beers);
            parser.parse(new File(xmlLink), handler);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
    @Test
    public void testStAX(){
        List<Beer> beers = new ArrayList<>();
        ExecutorStAX executorStAX = new ExecutorStAX(beers,xmlLink);
        executorStAX.load_from_file();
        for (int i = 0; i < beersResult.size(); i++) {
            assertEquals(beersResult.get(i).getName(), beers.get(i).getName());
        }
    }
}
