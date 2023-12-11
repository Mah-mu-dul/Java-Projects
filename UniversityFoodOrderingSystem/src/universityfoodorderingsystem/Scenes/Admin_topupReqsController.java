/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import universityfoodorderingsystem.ModelClasses.Product;
import universityfoodorderingsystem.ModelClasses.TopUpReceipt;
import universityfoodorderingsystem.ModelClasses.TopUpReq;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Admin_topupReqsController implements Initializable {

    @FXML
    private TableView<TopUpReq> reqTable;
    @FXML
    private TableColumn<TopUpReq, String> nameCol;
    @FXML
    private TableColumn<TopUpReq, Float> ammountCol;
    @FXML
    private TableColumn<TopUpReq, String> actionCol;
    @FXML
    private TableColumn<TopUpReq, String> customerIdCol;

    String uname = "admin user name";

    ObservableList<TopUpReq> TopUpReqList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        actionCol.setCellFactory(param -> new TableCell<TopUpReq, String>() {
            private final Button acceptButton = new Button("Accept");
            private final Button declineButton = new Button("Decline");

            {
                acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                declineButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    TopUpReq rowData = getTableView().getItems().get(getIndex());
                    // Handle accept action for rowData
                    TopUpReceipt receipt = createTopUpReceipt(rowData);
                    updateCustomerCredit(rowData.getCustomerUsername(), rowData.getAmmount());
                    File f = null;
                    FileWriter fw = null;
                    FileChooser fc = null;
                    try {

                        f = new File("TopUpRecipts.txt");

                        if (f.exists()) {
                            fw = new FileWriter(f, true);
                        } else {
                            fw = new FileWriter(f);
                        }

                        fw.write(receipt.toString());

                    } catch (IOException ex) {
                    } finally {
                        try {
                            if (fw != null) {
                                fw.close();
                            }

                        } catch (IOException ex) {
                        }
                    }

                    System.out.println("Accepted: " + rowData.writable());
                    getTableView().getItems().remove(rowData);
                    updateTopUpRequestsFile();

                });

                declineButton.setOnAction(event -> {
                    TopUpReq rowData = getTableView().getItems().get(getIndex());
                    // Handle decline action for rowData
                    System.out.println("Declined: " + rowData.writable());
                    getTableView().getItems().remove(rowData);
                    updateTopUpRequestsFile();
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
                buttonPane.getChildren().addAll(acceptButton, declineButton);
                return buttonPane;
            }
        });

        customerIdCol.setCellValueFactory(new PropertyValueFactory<TopUpReq, String>("customerUsername"));
        nameCol.setCellValueFactory(new PropertyValueFactory<TopUpReq, String>("name"));
        ammountCol.setCellValueFactory(new PropertyValueFactory<TopUpReq, Float>("ammount"));

        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("TopUpRequests.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("<>");

                    TopUpReq t = new TopUpReq(Integer.parseInt(tokens[0].split("=")[1]),
                            tokens[1].split("=")[1], // username
                            tokens[2].split("=")[1], // name
                            Float.parseFloat(tokens[3].split("=")[1]) // ammount

                    );

                    TopUpReqList.add(t);
                    System.out.println(t.writable());

                }
            } else {
            }
        } catch (IOException ex) {
        } finally {
        }
        reqTable.setItems(TopUpReqList);

    }

    public void initdata(String uname) {

        this.uname = uname;

    }

    private TopUpReceipt createTopUpReceipt(TopUpReq request) {
        // Customize this method based on your requirements
        // For now, we'll use the current date and time as the receipt date
        Date currentDate = new Date();
        TopUpReceipt t = new TopUpReceipt(request.getId(), request.getCustomerUsername(), uname, request.getName(), request.getAmmount(), currentDate);
        return t;
    }

    private boolean updateCustomerCredit(String CustomerUserName, float ammount) {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

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
                    if (CustomerUserName.equals(tokens[0].split("=")[1])) {
                        Customer c = new Customer(
                                tokens[4].split("=")[1],
                                Float.parseFloat(tokens[5].split("=")[1]) + ammount,
                                tokens[0].split("=")[1],
                                tokens[1].split("=")[1],
                                tokens[2].split("=")[1],
                                tokens[3].split("=")[1]
                        );
                        System.out.println(c.toString()+"inside of topup");

                        customerList.add(c);
                    } else {

                        Customer c = new Customer(
                                tokens[4].split("=")[1],
                                Float.parseFloat(tokens[5].split("=")[1]),
                                tokens[0].split("=")[1],
                                tokens[1].split("=")[1],
                                tokens[2].split("=")[1],
                                tokens[3].split("=")[1]
                        );

                        customerList.add(c);
                    }

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        try (FileWriter fw = new FileWriter("Customers.txt")) {
            // Write the string representation of each customer to the file
            for (Customer customer : customerList) {
                fw.write(customer.toString());
            }

        } catch (IOException e) {
        }

        return true;
    }

    private void updateTopUpRequestsFile() {
        File f = new File("TopUpRequests.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            for (TopUpReq request : TopUpReqList) {
                fw.write(request.writable());
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
}
