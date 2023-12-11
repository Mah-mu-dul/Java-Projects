package universityfoodorderingsystem.ModelClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Product {

    int productId, stockQuantity;
    String name, Description, shopname;
    float price;

    public Product(int productId, int stockQuantity, String name, String Description, String shopname, float price) {
        this.productId = productId;
        this.stockQuantity = stockQuantity;
        this.name = name;
        this.Description = Description;
        this.shopname = shopname;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" + "productId=" + productId + ", stockQuantity=" + stockQuantity + ", name=" + name + ", Description=" + Description + ", supplyerUserName=" + shopname + ", price=" + price + '}';
    }

    public String writable() {
        return "productId=" + productId + "<> stockQuantity=" + stockQuantity + "<> name=" + name + "<> Description=" + Description + "<> supplyerUserName=" + shopname + "<> price=" + price + "\n";
    }

    public int getProductId() {
        return productId;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return Description;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public float getPrice() {
        return price;
    }

    public static int getLastProductId() {
        File f = new File("Products.txt");
        Scanner sc = null;

        try {
            if (f.exists()) {
                sc = new Scanner(f);
                int ProductId = 1001;

                while (sc.hasNextLine()) {
                    String str = sc.nextLine();
                    String[] tokens = str.split("<>");

                    ProductId = Integer.parseInt(tokens[0].split("=")[1]);
                }
                System.out.println(ProductId);

                if (ProductId != 1001) {
                    return ProductId;
                } else {
                    return 0;
                }
            } else {
                return 1001;
            }
        } catch (FileNotFoundException | NumberFormatException ex) {
            ex.printStackTrace();
            return 0;
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
    }

}
