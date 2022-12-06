package Main;


import Parsers.Tags;

//варіант 10
public class Main {
    public static void main(String[] args) {
        Tags tags = new Tags("Beers", "Beer","id", "name", "type", "alcohol",
                "manufacturer", "ingredients","number_turns",
                "transparency", "nutritional_value");
        Menu menu = new Menu(tags);
        menu.showMenu();

    }
}