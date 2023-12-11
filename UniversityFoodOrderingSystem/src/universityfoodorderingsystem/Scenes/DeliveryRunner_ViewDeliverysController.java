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
import javafx.stage.FileChooser;
import universityfoodorderingsystem.ModelClasses.DeliveryOrderView;
import universityfoodorderingsystem.ModelClasses.DeliveryRunner;
import universityfoodorderingsystem.ModelClasses.Order;

public class DeliveryRunner_ViewDeliverysController implements Initializable {

    @FXML
    private TableColumn<DeliveryOrderView, Integer> orderIdCol;
    @FXML
    private TableColumn<DeliveryOrderView, Integer> productIdCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> productNameCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> shopNameCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> customerIdCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> customerNameCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> addressCol;
    @FXML
    private TableColumn<DeliveryOrderView, Float> priceCol;
    @FXML
    private TableColumn<DeliveryOrderView, String> actionCol;
    @FXML
    private TableView<DeliveryOrderView> table;
    @FXML
    private TableColumn<DeliveryOrderView, Integer> quantityCol;

    private DeliveryRunner dr;
    ObservableList<DeliveryOrderView> DeliveryOrderViewList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        orderIdCol.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<>("productId"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        productNameCol.setCellValueFactory(new PropertyValueFactory<>("productName"));
        shopNameCol.setCellValueFactory(new PropertyValueFactory<>("shopName"));
        customerIdCol.setCellValueFactory(new PropertyValueFactory<>("customerID"));
        customerNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        actionCol.setCellValueFactory(new PropertyValueFactory<>("action"));

        actionCol.setCellFactory(col -> new TableCell<DeliveryOrderView, String>() {
            final Button acceptButton = new Button("Accept");
            final Button completeButton = new Button("Complete");

            {
                acceptButton.setOnAction(event -> {
                    DeliveryOrderView deliveryOrder = getTableView().getItems().get(getIndex());
                    ObservableList<Order> Orders = FXCollections.observableArrayList();

                    File f = null;
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

                                if (Integer.parseInt(tokens[6].split("=")[1]) == deliveryOrder.getOrderId()) {

                                    Order o = new Order(
                                            Integer.parseInt(tokens[6].split("=")[1]),
                                            Integer.parseInt(tokens[0].split("=")[1]),
                                            Integer.parseInt(tokens[1].split("=")[1]),
                                            tokens[3].split("=")[1],
                                            "Shipping",
                                            tokens[5].split("=")[1]
                                    );
                                    Orders.add(o);
                                } else {

                                    Order o = new Order(
                                            Integer.parseInt(tokens[6].split("=")[1]),
                                            Integer.parseInt(tokens[0].split("=")[1]),
                                            Integer.parseInt(tokens[1].split("=")[1]),
                                            tokens[3].split("=")[1],
                                            tokens[4].split("=")[1],
                                            tokens[5].split("=")[1]
                                    );
                                    Orders.add(o);
                                }

                            }
                        } else {
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                    }
                    FileWriter fw = null;
                    FileChooser fc = null;
                    try {

                        f = new File("Orders.txt");
                        fw = new FileWriter(f);
                        for (Order o : Orders) {
                            fw.write(o.toString());
                        }
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
                    refreashTable();
                });

                // Event handler for the "Complete" button
                completeButton.setOnAction(event -> {
                    DeliveryOrderView deliveryOrder = getTableView().getItems().get(getIndex());
                    ObservableList<Order> Orders = FXCollections.observableArrayList();

                    File f = null;
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

                                if (Integer.parseInt(tokens[6].split("=")[1]) == deliveryOrder.getOrderId()) {

                                    Order o = new Order(
                                            Integer.parseInt(tokens[6].split("=")[1]),
                                            Integer.parseInt(tokens[0].split("=")[1]),
                                            Integer.parseInt(tokens[1].split("=")[1]),
                                            tokens[3].split("=")[1],
                                            "Complete",
                                            tokens[5].split("=")[1].split("#")[0] + "#" + dr.getUsername()
                                    );
                                    Orders.add(o);
                                } else {

                                    Order o = new Order(
                                            Integer.parseInt(tokens[6].split("=")[1]),
                                            Integer.parseInt(tokens[0].split("=")[1]),
                                            Integer.parseInt(tokens[1].split("=")[1]),
                                            tokens[3].split("=")[1],
                                            tokens[4].split("=")[1],
                                            tokens[5].split("=")[1]
                                    );
                                    Orders.add(o);
                                }

                            }
                        } else {
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                    } finally {
                    }
                    FileWriter fw = null;
                    FileChooser fc = null;
                    try {

                        f = new File("Orders.txt");
                        fw = new FileWriter(f);
                        for (Order o : Orders) {
                            fw.write(o.toString());
                        }
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
                    refreashTable();
                });
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    DeliveryOrderView deliveryOrder = getTableView().getItems().get(getIndex());
                    if ("Shipping Requested".equals(deliveryOrder.getStatus())) {
                        acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;"); // Green background

                        setGraphic(acceptButton);
                    } else if ("Shipping".equals(deliveryOrder.getStatus())) {
                        completeButton.setStyle("-fx-background-color: #FFAF00; -fx-text-fill: black;"); // Green background

                        setGraphic(completeButton);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
    }

    public void initData(DeliveryRunner dr) {
        this.dr = dr;
        refreashTable();
    }

    private void refreashTable() {
        File f = null;
        DeliveryOrderViewList.clear();
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
                    if (tokens[4].split("=")[1].equals("Shipping Requested") || tokens[4].split("=")[1].equals("Shipping")) {

                        DeliveryOrderView dov = new DeliveryOrderView(Integer.parseInt(tokens[6].split("=")[1]));
                        DeliveryOrderViewList.add(dov);
                        System.out.println(dov.toString());
                    }
                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
        table.refresh();
        table.setItems(DeliveryOrderViewList);
    }

}
