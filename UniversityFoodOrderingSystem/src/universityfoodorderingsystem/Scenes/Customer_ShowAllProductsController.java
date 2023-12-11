/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.OrderItem;
import universityfoodorderingsystem.ModelClasses.Product;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Customer_ShowAllProductsController implements Initializable {

    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, Integer> productIdCol;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Float> priceCol;
    @FXML
    private TableColumn<Product, Integer> stockCol;
    @FXML
    private TableColumn<Product, String> DescriptionCol;
    @FXML
    private ComboBox<String> productCombo;
    @FXML
    private ComboBox<String> VendorCombo;

    ObservableList<Product> ProductList = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Product, String> ShopCol;
    @FXML
    private TableColumn<Product, String> orderCol;
    ObservableList<OrderItem> cart = FXCollections.observableArrayList();

    private BorderPane borderpane;
    private String uname;
    Customer c;

    public void initData(BorderPane borderpane, Customer c) {
        this.borderpane = borderpane;
        this.uname = c.getUsername();
        this.c = c;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderCol.setCellFactory(param -> new TableCell<Product, String>() {
            private final Button acceptButton = new Button("Add to cart");

            {
                acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    Product rowData = getTableView().getItems().get(getIndex());

                    OrderItem oi = new OrderItem(rowData.getName(), rowData.getProductId(), 1, rowData.getPrice());
                    Optional<OrderItem> existingItem = cart.stream()
                            .filter(item -> item.getProductid() == oi.getProductid())
                            .findFirst();
                    if (existingItem.isPresent()) {
                        existingItem.get().setQuantity(existingItem.get().getQuantity() + oi.getQuantity());
                    } else {
                        cart.add(oi);
                    }
                    System.out.println(cart);
                });

            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(createButtonPane());
                }
            }

            private HBox createButtonPane() {
                HBox buttonPane = new HBox(5);
                buttonPane.getChildren().addAll(acceptButton);
                return buttonPane;
            }
        });

        productIdCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stockQuantity"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("Description"));
        ShopCol.setCellValueFactory(new PropertyValueFactory<Product, String>("shopname"));
        productCombo.getItems().add("All");
        VendorCombo.getItems().add("All");
        productCombo.getSelectionModel().select("All");
        VendorCombo.getSelectionModel().select("All");
        // Create a button column for orders
        TableColumn<Product, Void> orderBtnCol = new TableColumn<>("Order");
//        orderBtnCol.setCellFactory(param -> new OrderButtonCell());

// TODO
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("Products.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("<>");
                    Product p = new Product(Integer.parseInt(tokens[0].split("=")[1]),
                            Integer.parseInt(tokens[1].split("=")[1]),
                            tokens[2].split("=")[1],
                            tokens[3].split("=")[1],
                            tokens[4].split("=")[1],
                            Float.parseFloat(tokens[5].split("=")[1])
                    );

                    ProductList.add(p);

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        productCombo.getItems().addAll(ProductList.stream().map(Product::getName).collect(Collectors.toSet()));
        VendorCombo.getItems().addAll(ProductList.stream().map(Product::getShopname).distinct().collect(Collectors.toList()));
        ObservableList<Product> filteredList = filterProducts(ProductList, productCombo.getValue(), VendorCombo.getValue());

        ProductTable.setItems(filteredList);

    }
    // Method to filter products based on name and Shopname

    @FXML
    private void OnProductSelect(ActionEvent event) {
        ObservableList<Product> filteredList = filterProducts(ProductList, productCombo.getValue(), VendorCombo.getValue());
        ProductTable.setItems(filteredList);

    }

    private static ObservableList<Product> filterProducts(ObservableList<Product> productList, String name, String supplierName) {
        return productList.stream()
                .filter(product -> (name.equals("All") || product.getName().equals(name))
                && (supplierName.equals("All") || product.getShopname().equals(supplierName)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    private static ObservableList<Product> filterVendors(ObservableList<Product> productList, String name, String supplierName) {
        return productList.stream()
                .filter(product -> (name.equals("All") || product.getName().equals(name))
                && (supplierName.equals("All") || product.getShopname().equals(supplierName)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
    }

    @FXML
    private void OnVendorSelect(ActionEvent event) {
        // Get the selected values from both ComboBoxes
        String selectedProduct = productCombo.getValue();
        String selectedVendor = VendorCombo.getValue();

        // Filter the products based on the selected values
        ObservableList<Product> filteredList = filterProducts(ProductList, selectedProduct, selectedVendor);

        // Update the TableView with the filtered list
        ProductTable.setItems(filteredList);
    }

    @FXML
    private void checkoutBtnClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_OrderPage.fxml"));
        Parent root = loader.load();
        Customer_OrderPageController controller = loader.getController();
        controller.initdata(cart, c); // Replace with actual values
        borderpane.setCenter(root);
    }

}
