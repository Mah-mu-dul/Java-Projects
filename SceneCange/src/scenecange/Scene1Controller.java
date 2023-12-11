/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package scenecange;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Scene1Controller implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotoscene2(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("scene2.fxml"));
        Parent personViewParent = loader.load();

        Scene personViewScene = new Scene(personViewParent);

        Scene2Controller controller = loader.getController();
        controller.initData("kichu ekta");

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(personViewScene);
        window.show();
    }

    @FXML
    private void scene2Innewwindow(ActionEvent event) throws IOException {
        FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("scene2.fxml"));
        Parent root = (Parent) (fxmlloader.load());
        Stage stage = new Stage();
        stage.show();
    }

}
