package Main;

public class Beer {
    private String name, type, manufacturer, ingredients;
    private int id;
    private boolean al;
    // chars
    private double numberTurns;
    private double transparency;
    private double nutritionalValue;

    public Beer(String name, int id, String type, boolean al, String manufacturer, String ingredients,
                double numberTurns, double transparency, double nutritionalValue){

        this.name = name;
        this.id = id;
        this.type = type;
        this.al = al;
        this.manufacturer = manufacturer;
        this.ingredients = ingredients;
        this.numberTurns = numberTurns;
        this.transparency = transparency;
        this.nutritionalValue = nutritionalValue;
    }
    @Override
    public String toString(){
        return  "Name:            " + name + "\n" +
                "id:              " + id + "\n" +
                "Type:            " + type + "\n" +
                "Alcohol:         " + al + "\n" +
                "Manufacturer:    " + manufacturer + "\n" +
                "Ingredients:     " + ingredients + "\n" +
                "Number turns:    " + numberTurns + "\n" +
                "Transparency:    " + transparency + "\n" +
                "Nutrition value: " + nutritionalValue + "\n";
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

    public double getNumberTurns() {
        return numberTurns;
    }

    public double getTransparency() {
        return transparency;
    }

    public double getNutritionalValue() {
        return nutritionalValue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setAl(boolean al) {
        this.al = al;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumberTurns(double numberTurns) {
        this.numberTurns = numberTurns;
    }

    public void setNutritionalValue(double nutritionalValue) {
        this.nutritionalValue = nutritionalValue;
    }

    public void setTransparency(double transparency) {
        this.transparency = transparency;
    }
}
