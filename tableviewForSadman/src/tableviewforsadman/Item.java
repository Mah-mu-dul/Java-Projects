package tableviewforsadman;

public class Item {

    String name, description;
    int Stockquantity, id;
    float UnitPrice;

    public Item(String name, String description, int Stockquantity, int id, float UnitPrice) {
        this.name = name;
        this.description = description;
        this.Stockquantity = Stockquantity;
        this.id = id;
        this.UnitPrice = UnitPrice;
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

    public float getUnitPrice() {
        return UnitPrice;
    }

}
