package scenechanginginwith4types;

public class Item {

    String name, description;
    int Stockquantity, id;
    float price;
    

    public Item(String name, String description, int Stockquantity, int id, float price) {
        this.name = name;
        this.description = description;
        this.Stockquantity = Stockquantity;
        this.id = id;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", description=" + description + ", Stockquantity=" + Stockquantity + ", id=" + id + ", price=" + price + '}';
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getStockquantity() {
        return Stockquantity;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }
    

}
