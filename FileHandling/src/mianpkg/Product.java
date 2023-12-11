package mianpkg;

import java.io.Serializable;

public class Product implements Serializable {

    String name;
    float price;
    int vat, stockQuantity;

    public Product(String name, float price, int vat, int stockQuantity) {
        this.name = name;
        this.price = price;
        this.vat = vat;
        this.stockQuantity = stockQuantity;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getVat() {
        return vat;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    @Override
    public String toString() {
        return "Product{" + "name=" + name + ", price=" + price + ", vat=" + vat + ", stockQuantity=" + stockQuantity + '}';
    }

}
