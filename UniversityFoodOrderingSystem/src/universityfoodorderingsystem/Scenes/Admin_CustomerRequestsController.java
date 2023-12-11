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
import universityfoodorderingsystem.ModelClasses.SignUpReq_Customer;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Admin_CustomerRequestsController implements Initializable {

    @FXML
    private TableView<SignUpReq_Customer> table;
    @FXML
    private TableColumn<SignUpReq_Customer, String> usernameCol;
    @FXML
    private TableColumn<SignUpReq_Customer, String> nameCol;
    @FXML
    private TableColumn<SignUpReq_Customer, String> addressCol;
    @FXML
    private TableColumn<SignUpReq_Customer, String> actionCol;

    String adminUname = "";
    ObservableList<SignUpReq_Customer> SignUpReq_CustomerList = FXCollections.observableArrayList();

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void initdata(String uname) {

        this.adminUname = uname;
        actionCol.setCellFactory(param -> new TableCell<SignUpReq_Customer, String>() {
            private final Button acceptButton = new Button("Accept");
            private final Button declineButton = new Button("Decline");

            {
                acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                declineButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    SignUpReq_Customer rowData = getTableView().getItems().get(getIndex());
                    // Handle accept action for rowData
                    Customer Customer = rowData.toCustomer(uname);
                    File f = null;
                    FileWriter fw = null;
                    FileChooser fc = null;
                    try {

                        f = new File("Customers.txt");

                        if (f.exists()) {
                            fw = new FileWriter(f, true);
                        } else {
                            fw = new FileWriter(f);
                        }

                        fw.write(Customer.toString());

                    } catch (IOException ex) {
                    } finally {
                        try {
                            if (fw != null) {
                                fw.close();
                            }

                        } catch (IOException ex) {
                        }
                    }

                    System.out.println("Accepted: " + rowData.toString());
                    getTableView().getItems().remove(rowData);
                    updateCustomerRequestFile();

                });

                declineButton.setOnAction(event -> {
                    SignUpReq_Customer rowData = getTableView().getItems().get(getIndex());
                    // Handle decline action for rowData
                    System.out.println("Declined: " + rowData.toString());
                    getTableView().getItems().remove(rowData);
                    updateCustomerRequestFile();

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

        usernameCol.setCellValueFactory(new PropertyValueFactory<SignUpReq_Customer, String>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<SignUpReq_Customer, String>("name"));
        addressCol.setCellValueFactory(new PropertyValueFactory<SignUpReq_Customer, String>("CustomerAddress"));

        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("CustomerRequest.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(",");

                    SignUpReq_Customer v = new SignUpReq_Customer(tokens[0].split("=")[1], // username
                            tokens[1].split("=")[1], // password
                            tokens[2].split("=")[1], // name
                            tokens[3].split("=")[1] // shopName

                    );

                    SignUpReq_CustomerList.add(v);
//                    System.out.println(v.toString());

                }
            } else {
            }
        } catch (IOException ex) {
        } finally {
        }
        table.setItems(SignUpReq_CustomerList);
    }

    private void updateCustomerRequestFile() {
        File f = new File("CustomerRequest.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            for (SignUpReq_Customer request : SignUpReq_CustomerList) {
                fw.write(request.toString());
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
