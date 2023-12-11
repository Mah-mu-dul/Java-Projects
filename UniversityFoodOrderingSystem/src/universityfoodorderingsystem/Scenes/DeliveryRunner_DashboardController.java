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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import universityfoodorderingsystem.ModelClasses.DeliveryRunner;
import universityfoodorderingsystem.ModelClasses.Product;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class DeliveryRunner_DashboardController implements Initializable {

    @FXML
    private Label RunnerName;
    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    DeliveryRunner dr;

    public void initdata(String uname) {

        File f = null;
        //FileReader fw = null;
        Scanner sc;
        String str;
        String[] tokens;
        try {
            f = new File("DeliveryRunners.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split("<>");
                    if (tokens[0].split("=")[1].equals(uname)) {
                        dr = new DeliveryRunner(
                                tokens[0].split("=")[1],
                                tokens[1].split("=")[1],
                                tokens[2].split("=")[1],
                                tokens[3].split("=")[1]
                        );
                    }
                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        RunnerName.setText(dr.getName());

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
    private void ViewDeliveries(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeliveryRunner_ViewDeliverys.fxml"));
        Parent root = loader.load();
        DeliveryRunner_ViewDeliverysController controller = loader.getController();
        controller.initData(dr); // Replace with actual values
        borderpane.setCenter(root);
    }

    @FXML
    private void DeliveryHistory(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DeliveryRunner_ViewDeliveryHistory.fxml"));
        Parent root = loader.load();
        DeliveryRunner_ViewDeliveryHistoryController controller = loader.getController();
        controller.initData(dr); // Replace with actual values
        borderpane.setCenter(root);
    }

}
