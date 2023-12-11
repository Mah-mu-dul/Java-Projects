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
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.Order;
import universityfoodorderingsystem.ModelClasses.OrderItem;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Customer_OrderPageController implements Initializable {

    @FXML
    private TableView<OrderItem> table;
    @FXML
    private TableColumn<OrderItem, Integer> ProductIdCol;
    @FXML
    private TableColumn<OrderItem, String> productNameCol;
    @FXML
    private TableColumn<OrderItem, Float> UnitPriceCol;
    @FXML
    private TableColumn<OrderItem, Integer> quantityCol;
    @FXML
    private TableColumn<OrderItem, Float> TotalPricecol;
    @FXML
    private Label TotalPriceLable;
    private String uname;
    ObservableList<OrderItem> cart = FXCollections.observableArrayList();
    @FXML
    private ComboBox<String> deliveryTypeCombo;
    Customer c;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        deliveryTypeCombo.getItems().addAll("Dine-in", "Takeaway", "Request For Delivery");
        deliveryTypeCombo.getSelectionModel().select("Request For Delivery");
    }

    public void initdata(ObservableList cart, Customer c) {
        this.cart = cart;
        this.uname = c.getUsername();
        this.c = c;

        ProductIdCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("productid"));
        UnitPriceCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Float>("unitPrice"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<OrderItem, Integer>("quantity"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<OrderItem, String>("Productname"));
        TotalPricecol.setCellValueFactory(param -> {
            OrderItem orderItem = param.getValue();
            float totalPrice = orderItem.getTotalPrice(); // Replace with the actual method to calculate total price
            return new SimpleFloatProperty(totalPrice).asObject();
        });
        table.setItems(cart);
        float finalTotal = 0.0f;

        for (OrderItem orderItem : this.cart) {
            finalTotal += orderItem.getTotalPrice();
        }
        TotalPriceLable.setText(Float.toString(finalTotal));

    }

    @FXML
    private void PlaceOrder(ActionEvent event) {
        float finalTotal = 0.0f;

        for (OrderItem orderItem : this.cart) {
            finalTotal += orderItem.getTotalPrice();
        }
        if (finalTotal < c.getCredit()) {
            ObservableList<Customer> customerList = FXCollections.observableArrayList();
            {
                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("Customers.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split("<>");
                            if (tokens[0].split("=")[1].equals(c.getUsername())) {
                                Customer cc = new Customer(
                                        tokens[4].split("=")[1],
                                        c.getCredit() - finalTotal,
                                        tokens[0].split("=")[1],
                                        tokens[1].split("=")[1],
                                        tokens[2].split("=")[1],
                                        tokens[3].split("=")[1]
                                );
                                customerList.add(cc);
                            } else {

                                Customer cc = new Customer(
                                        tokens[4].split("=")[1],
                                        Float.parseFloat(tokens[5].split("=")[1]),
                                        tokens[0].split("=")[1],
                                        tokens[1].split("=")[1],
                                        tokens[2].split("=")[1],
                                        tokens[3].split("=")[1]
                                );
                                customerList.add(cc);
                            }

                        }
                    } else {
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
            for (Customer ccc : customerList) {
                System.out.println(ccc.toString()+"ccc");
                File f = null;
                FileWriter fw = null;
                FileChooser fc = null;
                try {
                    f = new File("Customers.txt");
                    fw = new FileWriter(f);
                    fw.write(ccc.toString());

                } catch (IOException ex) {
                } finally {
                    try {
                        if (fw != null) {
                            fw.close();
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Vendor_AddProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            for (OrderItem orderItem : this.cart) {
                Order order = new Order(Order.getLastOrderId() + 1, orderItem.getProductid(), orderItem.getQuantity(), uname, "placed", deliveryTypeCombo.getValue());
                File f = null;
                FileWriter fw = null;
                FileChooser fc = null;
                try {

                    f = new File("Orders.txt");

                    if (f.exists()) {
                        fw = new FileWriter(f, true);
                    } else {
                        fw = new FileWriter(f);
                    }

                    fw.write(order.toString());

                } catch (IOException ex) {
                    Logger.getLogger(Vendor_AddProductController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                    try {
                        if (fw != null) {
                            fw.close();
                        }

                    } catch (IOException ex) {
                        Logger.getLogger(Vendor_AddProductController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("less credit, please topup");
            alert.showAndWait();
        }
    }
}
