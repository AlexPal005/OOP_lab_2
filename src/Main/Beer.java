package Main;

import java.util.List;

public class Beer {
    private String name;
    private int id;
    private String type;
    private boolean al;
    private String manufacturer;
    private String ingredients;
    // chars
    private double number_turns;
    private double transparency;
    private double nutritional_value;

    public Beer(String name, int id, String type, boolean al, String manufacturer, String ingredients,
                double number_turns, double transparency, double nutritional_value){

        this.name = name;
        this.id = id;
        this.type = type;
        this.al = al;
        this.manufacturer = manufacturer;
        this.ingredients = ingredients;
        this.number_turns = number_turns;
        this.transparency = transparency;
        this.nutritional_value = nutritional_value;
    }
    @Override
    public String toString(){
        return  "Name:            " + name + "\n" +
                "id:              " + id + "\n" +
                "Tupe:            " + type + "\n" +
                "Alcohol:         " + al + "\n" +
                "Manufacturer:    " + manufacturer + "\n" +
                "Ingredients:     " + ingredients + "\n" +
                "Number turns:    " + number_turns + "\n" +
                "Transparency:    " + transparency + "\n" +
                "Nutrition value: " + nutritional_value + "\n";
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
    public boolean getAl(){
        return al;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getIngredients() {
        return ingredients;
    }

    public double getNumber_turns() {
        return number_turns;
    }

    public double getTransparency() {
        return transparency;
    }

    public double getNutritional_value() {
        return nutritional_value;
    }
}
