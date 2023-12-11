/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import universityfoodorderingsystem.ModelClasses.Product;
import universityfoodorderingsystem.ModelClasses.TopUpReceipt;
import universityfoodorderingsystem.ModelClasses.TopUpReq;

public class Vendor_ViewMyProductsController implements Initializable {

    String uname, shopname;

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
    private TableColumn<Product, String> ActionCol;

    ObservableList<Product> ProductList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ActionCol.setCellFactory(param -> new TableCell<Product, String>() {
            private final Button declineButton = new Button("Delete");

            {
                declineButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;");

                declineButton.setOnAction(event -> {
                    Product rowData = getTableView().getItems().get(getIndex());
                    // Handle decline action for rowData
                    System.out.println("Declined: " + rowData.writable());
                    getTableView().getItems().remove(rowData);
                    updateProductsFile();
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
                buttonPane.getChildren().addAll(declineButton);
                return buttonPane;
            }
        }
        );

        productIdCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        stockCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stockQuantity"));
        DescriptionCol.setCellValueFactory(new PropertyValueFactory<Product, String>("Description"));

    }

    private void updateProductsFile() {
        File f = new File("Products.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            for (Product p : ProductList) {
                fw.write(p.writable());
                fw.write("");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public void initdata(String uname, String shopname) {
        this.uname = uname;
        this.shopname = shopname;

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
                    if (p.getShopname().equals(shopname)) {
                        ProductList.add(p);
                    }
                    System.out.println(p.toString());

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        ProductTable.setItems(ProductList);

    }

}
