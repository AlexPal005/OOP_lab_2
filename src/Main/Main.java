package Main;

import DOM_parser.Executor;

import java.util.ArrayList;
import java.util.List;

//варіант 10
public class Main {
    public static void main(String[] args) {
        Beer beer1 = new Beer("Львівське", 1, "Світле", true, "Львів фабрика",
                "Вода, солод, дріджі", 3.4 , 10, 500);
        Beer beer2 = new Beer("Corona", 2, "Міцне", true, "Київ фабрика",
                "Вода, солод, цукор", 5, 15, 700);
        List<Beer> beers = new ArrayList<>();
        beers.add(beer1);
        beers.add(beer2);
        List<Beer> beers1 = new ArrayList<>();
        Executor executor = new Executor(beers1);
        executor.load_from_file();

    }
}