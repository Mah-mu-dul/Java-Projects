package universityfoodorderingsystem.Scenes;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Administrator_DashboardController implements Initializable {

    @FXML
    private Label AdministratorName;

    @FXML
    private BorderPane borderpane;
    String uname;

    /**
     * Initializes the controller class.
     */
    public void initdata(String uname) {

        AdministratorName.setText(uname);
        this.uname = uname;

        // TODO
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_ManageUsersScene.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(Administrator_DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Admin_ManageUsersSceneController controller = loader.getController();
        controller.initdata(uname); // Replace with actual values
        borderpane.setCenter(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    private void UserRequestsonclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_ManageUsersScene.fxml"));
        Parent root = loader.load();
        Admin_ManageUsersSceneController controller = loader.getController();
        controller.initdata(uname); // Replace with actual values
        borderpane.setCenter(root);

    }

    @FXML
    private void topupReqOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_topupReqs.fxml"));
        Parent root = loader.load();
        Admin_topupReqsController controller = loader.getController();
        controller.initdata(uname); // Replace with actual values
        borderpane.setCenter(root);
    }

    @FXML
    private void UpdateOrDeleteUsrsOnclick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_UpdateDelete.fxml"));
        Parent root = loader.load();
        Admin_UpdateDeleteController controller = loader.getController();
        controller.initData(uname); // Replace with actual values
        borderpane.setCenter(root);
    }

}
