package Main;

import Parsers.ExecutorDOM;
import Parsers.ExecutorSAX;
import Parsers.ExecutorStAX;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private List<Beer> beers;
    private final String xmlLink;
    private final String xsdLink;
    public Menu(){
        xmlLink = "src\\Beers.xml";
        xsdLink = "src\\Beers.xsd";

    }
    private void runDOM(){
        // DOM parser start
        /* Beer beer1 = new Beer("Львівське", 1, "Світле", true, "Львів фабрика",
                "Вода, солод, дріджі", 3.4 , 10, 500);
        Beer beer2 = new Beer("Corona", 2, "Міцне", true, "Київ фабрика",
                "Вода, солод, цукор", 5, 15, 700);
        List<Beer> beers = new ArrayList<>();
        beers.add(beer1);
        beers.add(beer2);
        */
        beers = new ArrayList<>();
        ExecutorDOM executor = new ExecutorDOM(beers, xmlLink);
        if(!XMLValidator.validate(xmlLink,xsdLink)){
            System.out.println("Error validation!");
        }
        else{
            executor.loadFromFile();
        }
    }
    private void runSAX(){
        beers = new ArrayList<>();
        //SAX parser start
        if(!XMLValidator.validate(xmlLink,xsdLink)){
            System.out.println("Error validation!");
        }
        else{
            try {
                SAXParserFactory factory = SAXParserFactory.newInstance();
                SAXParser parser = factory.newSAXParser();
                ExecutorSAX handler = new ExecutorSAX(beers);
                parser.parse(new File(xmlLink), handler);
            } catch (ParserConfigurationException | SAXException | IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    private void runStAX(){
        beers = new ArrayList<>();
        if(!XMLValidator.validate(xmlLink,xsdLink)){
            System.out.println("Error validation!");
        }
        else{
            ExecutorStAX executorStAX = new ExecutorStAX(beers, xmlLink);
            executorStAX.load_from_file();
        }
    }
    private void sort(){
        NumberTurnsComparator numberTurnsComparator = new NumberTurnsComparator();
        beers.sort(numberTurnsComparator);
        System.out.println("Відсортоване по кількості оборотів!");
    }
    public void showMenu(){
        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.println("------Меню-------");
            System.out.println("1. DOM parser");
            System.out.println("2. SAX parser");
            System.out.println("3. Stax parser");
            System.out.println("4. Sort");
            System.out.println("5. Очистити список");
            System.out.print("Уведіть номер меню: ");
            int number = in.nextInt();
            in.nextLine();
            switch (number) {
                case (1) -> runDOM();
                case (2) -> runSAX();
                case (3) -> runStAX();
                case (4) -> sort();
                case (5) -> {beers.clear();
                    System.out.println("Очищено!");}
                default -> System.out.println("Уведіть номер меню!");
            }
            show_beers();
        }

    }
    private void show_beers(){
        for (Beer beer: beers) {
            System.out.println(beer);
        }
    }
}
