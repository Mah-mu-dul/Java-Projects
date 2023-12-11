/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.Order;
import universityfoodorderingsystem.ModelClasses.TopUpReceipt;
import universityfoodorderingsystem.ModelClasses.TopUpReq;
import universityfoodorderingsystem.ModelClasses.Vendor;
import universityfoodorderingsystem.ModelClasses.VendorOrderView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Vendor_ViewOrdersController implements Initializable {

    @FXML
    private TableView<VendorOrderView> table;
    @FXML
    private TableColumn<VendorOrderView, Integer> productIdCol;
    @FXML
    private TableColumn<VendorOrderView, String> productnameCol;
    @FXML
    private TableColumn<VendorOrderView, Integer> StockQuantityCol;
    @FXML
    private TableColumn<VendorOrderView, Integer> quantityCol;
    @FXML
    private TableColumn<VendorOrderView, Float> unitPriceCol;
    @FXML
    private TableColumn<VendorOrderView, Float> totalPriceCol;
    @FXML
    private TableColumn<VendorOrderView, String> actionCol;
    @FXML
    private TableColumn<VendorOrderView, Integer> OrderIdCol;
    ObservableList<Order> Orders = FXCollections.observableArrayList();
    ObservableList<VendorOrderView> OrderViewList = FXCollections.observableArrayList();
    String uname, shopname;
    @FXML
    private TableColumn<VendorOrderView, String> customerNameCol;
    @FXML
    private TableColumn<VendorOrderView, String> statusCol;

    public void initdata(String uname, String shopname) {
        this.uname = uname;
        this.shopname = shopname;
        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("Orders.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(",");

                    Order o = new Order(
                            Integer.parseInt(tokens[6].split("=")[1]),
                            Integer.parseInt(tokens[0].split("=")[1]),
                            Integer.parseInt(tokens[1].split("=")[1]),
                            tokens[3].split("=")[1],
                            tokens[4].split("=")[1],
                            tokens[5].split("=")[1]
                    );
                    if (o.getShopName().equals(this.shopname) && (!o.getStatus().equals("Complete") && !o.getStatus().equals("Shipping"))) {

                        VendorOrderView ov = new VendorOrderView(o.getOrderid(), o.getProductId(), o.getQuantity(), uname, o.getCustomerId(), o.getStatus());
                        OrderViewList.add(ov);
                    }
                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        table.setItems(OrderViewList);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        OrderIdCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Integer>("orderId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Integer>("productId"));
        productnameCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, String>("productName"));
        StockQuantityCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Integer>("stockQuantity"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Integer>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Float>("unitPrice"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, Float>("totalPrice"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, String>("customerName"));
        statusCol.setCellValueFactory(new PropertyValueFactory<VendorOrderView, String>("orderStatus"));

        actionCol.setCellFactory(param -> new TableCell<VendorOrderView, String>() {

            private Button acceptButton = new Button("Accept");
            private Button declineButton = new Button("Decline");

            {
                acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                declineButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    VendorOrderView rowData = getTableView().getItems().get(getIndex());
                    // Handle accept action for rowData
                    declineButton.setVisible(false);
                    changeStatus(rowData);  // have to implement it

                });

                declineButton.setOnAction(event -> {
                    VendorOrderView rowData = getTableView().getItems().get(getIndex());
                    // Handle decline action for rowData
                    System.out.println("Declined: " + rowData.toString());
                    SendNotification(rowData);
                    getTableView().getItems().remove(rowData);
                });
                table.refresh();

            }

            private void SendNotification(VendorOrderView rowData) {
                System.out.println("have to send notification to customer for decline");
            }

            private void changeStatus(VendorOrderView rowData) {

                ObservableList<Order> orderList = FXCollections.observableArrayList();

                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("Orders.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split(",");
                            Order o = new Order(
                                    Integer.parseInt(tokens[6].split("=")[1]),
                                    Integer.parseInt(tokens[0].split("=")[1]),
                                    Integer.parseInt(tokens[1].split("=")[1]),
                                    tokens[3].split("=")[1],
                                    tokens[4].split("=")[1],
                                    tokens[5].split("=")[1]
                            );
                            if (o.getStatus().equals("accepted")) {
                                acceptButton.setText("Shipping Requested#n");
                                acceptButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
                            } else if (o.getStatus().equals("shipped")) {
                                acceptButton.setText("Shipping Requested");
                                acceptButton.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
                            }
                            orderList.add(o);
                        }
                    } else {
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
                for (Order o : orderList) {
                    if (o.getOrderid() == rowData.getOrderId()) {
                        if (o.getStatus().equals("placed")) {
                            declineButton.setVisible(false);
                            acceptButton.setText("Req for Ship");
                            acceptButton.setStyle("-fx-background-color: orange; -fx-text-fill: white;");
                            o.setStatus("accepted");
                            rowData.setOrderStatus("accepted");

                            File file = new File("Orders.txt");
                            FileWriter fileWriter = null;
                            try {
                                // Empty the file by creating a new FileWriter with the append flag set to false
                                fileWriter = new FileWriter(file, false);

                                // Write new orders to the file
                                for (Order order : orderList) {
                                    fileWriter.write(order.toString());
                                }
                            } catch (IOException ex) {
                            } finally {
                                try {
                                    if (fileWriter != null) {
                                        fileWriter.close();
                                    }
                                } catch (IOException ex) {
                                }
                            }
                        } else if (o.getStatus().equals("accepted")) {
                            acceptButton.setText("Shipping Requested");
                            acceptButton.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
                            o.setStatus("Shipping Requested");
                            rowData.setOrderStatus("Shipping Requested");

                            File file = new File("Orders.txt");
                            FileWriter fileWriter = null;

                            try {
                                // Empty the file by creating a new FileWriter with the append flag set to false
                                fileWriter = new FileWriter(file, false);

                                // Write new orders to the file
                                for (Order order : orderList) {
                                    fileWriter.write(order.toString());
                                }
                            } catch (IOException ex) {
                            } finally {
                                try {
                                    if (fileWriter != null) {
                                        fileWriter.close();
                                    }
                                } catch (IOException ex) {
                                }
                            }
                        }
                        System.out.println(o.getStatus());

                    }

                }
//                    System.out.println(OrderViewList);
            }

            @Override
            protected void updateItem(String item, boolean empty
            ) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(createButtonPane());
                }
            }

            private HBox createButtonPane() {
                HBox buttonPane = new HBox(5);
                buttonPane.getChildren().addAll(acceptButton, declineButton);
                return buttonPane;
            }
        });
    }

}
