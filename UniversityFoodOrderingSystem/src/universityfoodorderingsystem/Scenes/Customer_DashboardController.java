/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.Product;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Customer_DashboardController implements Initializable {

    String uname;
    @FXML
    private Label CustomerName;
    @FXML
    private BorderPane borderpane;
    @FXML
    private Label credit;

    Customer customer;

    /**
     * Initializes the controller class.
     */
    public void initdata(Customer customer) {
        this.customer = customer;
        uname = customer.getUsername();
        CustomerName.setText(customer.getName());
        credit.setText(Float.toString(customer.getCredit()));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void LogOutBtn(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void ShowAllProductsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_ShowAllProducts.fxml"));
        Parent root = loader.load();
        Customer_ShowAllProductsController controller = loader.getController();
        controller.initData(borderpane, customer); // Replace with actual values
        borderpane.setCenter(root);

    }

    @FXML
    private void TopUpOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_creditTopUp.fxml"));
        Parent root = loader.load();
        Customer_creditTopUpController controller = loader.getController();
        controller.initdata(customer); // Replace with actual values
        borderpane.setCenter(root);
    }

    @FXML
    private void OrderHistoryBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_OrderHistory.fxml"));
        Parent root = loader.load();
        Customer_OrderHistoryController controller = loader.getController();
        controller.initData(customer.getUsername()); // Replace with actual values
        borderpane.setCenter(root);
    }

    @FXML
    private void CurrentOrdersBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Customer_CurrentOrders.fxml"));
        Parent root = loader.load();
        Customer_CurrentOrdersController controller = loader.getController();
        controller.initData(customer.getUsername()); // Replace with actual values
        borderpane.setCenter(root);
    }

}
