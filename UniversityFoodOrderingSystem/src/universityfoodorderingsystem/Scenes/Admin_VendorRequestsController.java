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
import sun.security.util.Password;
import universityfoodorderingsystem.ModelClasses.TopUpReq;
import universityfoodorderingsystem.ModelClasses.Vendor;
import universityfoodorderingsystem.ModelClasses.VendorReq;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Admin_VendorRequestsController implements Initializable {

    @FXML
    private TableView<VendorReq> table;
    @FXML
    private TableColumn<VendorReq, String> usernameCol;
    @FXML
    private TableColumn<VendorReq, String> nameCol;
    @FXML
    private TableColumn<VendorReq, String> shopNameCol;
    @FXML
    private TableColumn<VendorReq, String> actionCol;

    String uname;
    ObservableList<VendorReq> VendorreqList = FXCollections.observableArrayList();

    public void initdata(String uname) {
        this.uname = uname;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        actionCol.setCellFactory(param -> new TableCell<VendorReq, String>() {
            private final Button acceptButton = new Button("Accept");
            private final Button declineButton = new Button("Decline");

            {
                acceptButton.setStyle("-fx-background-color: green; -fx-text-fill: white;");
                declineButton.setStyle("-fx-background-color: orangered; -fx-text-fill: white;");

                acceptButton.setOnAction(event -> {
                    VendorReq rowData = getTableView().getItems().get(getIndex());
                    // Handle accept action for rowData
                    Vendor vendor = rowData.toVendor(uname);
                    System.out.println(uname);

                    File f = null;
                    FileWriter fw = null;
                    FileChooser fc = null;
                    try {

                        f = new File("Vendors.txt");

                        if (f.exists()) {
                            fw = new FileWriter(f, true);
                        } else {
                            fw = new FileWriter(f);
                        }

                        fw.write(vendor.toString());

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
                    updateVendorReqFile();

                });

                declineButton.setOnAction(event -> {
                    VendorReq rowData = getTableView().getItems().get(getIndex());
                    // Handle decline action for rowData
                    System.out.println("Declined: " + rowData.toString());
                    getTableView().getItems().remove(rowData);
                    updateVendorReqFile();

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

        usernameCol.setCellValueFactory(new PropertyValueFactory<VendorReq, String>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<VendorReq, String>("name"));
        shopNameCol.setCellValueFactory(new PropertyValueFactory<VendorReq, String>("shopName"));

        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("VendorRequest.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(",");

                    VendorReq v = new VendorReq(tokens[0].split("=")[1], // username
                            tokens[1].split("=")[1], // password
                            tokens[2].split("=")[1], // name
                            tokens[3].split("=")[1] // shopName

                    );

                    VendorreqList.add(v);
//                    System.out.println(v.toString());

                }
            } else {
            }
        } catch (IOException ex) {
        } finally {
        }
        table.setItems(VendorreqList);

    }

    private void updateVendorReqFile() {
        File f = new File("VendorRequest.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(f);
            for (VendorReq vendorReq : VendorreqList) {
                fw.write(vendorReq.toString());
                fw.write(""); // Add a newline after each entry
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
