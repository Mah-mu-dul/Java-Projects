package tableviewforsadman;

public class Order {

    String ItemName;
    int ItemId, quantity;
    float itemUnitPrice, TotalPrice;
    String Description;

    public Order(String ItemName, int ItemId, int quantity, float itemUnitPrice, String Description) {
        this.ItemName = ItemName;
        this.ItemId = ItemId;
        this.quantity = quantity;
        this.itemUnitPrice = itemUnitPrice;
        this.Description = Description;
        TotalPrice = this.quantity * this.itemUnitPrice;

    }

    public String getItemName() {
        return ItemName;
    }

    public int getItemId() {
        return ItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public float getItemUnitPrice() {
        return itemUnitPrice;
    }

    public float getTotalPrice() {
        return TotalPrice;
    }

    public String getDescription() {
        return Description;
    }

}
