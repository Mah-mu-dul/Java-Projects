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
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Admin_ManageUsersSceneController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private AnchorPane middle;

    String adminUname = "";

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_VendorRequests.fxml"));
            Parent root = loader.load();
            Admin_VendorRequestsController controller = loader.getController();
            controller.initdata(adminUname); // Replace with actual values
            borderpane.setCenter(root);
            // TODO
        } catch (IOException ex) {
        }
    }

    public void initdata(String uname) {

        this.adminUname = uname;
    }

    @FXML
    private void goToCustomerReqScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_CustomerRequests.fxml"));
        Parent root = loader.load();
        Admin_CustomerRequestsController controller = loader.getController();
        controller.initdata(adminUname); // Replace with actual values
        borderpane.setCenter(root);
    }

    @FXML
    private void goToVendorReqScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_VendorRequests.fxml"));
        Parent root = loader.load();
        Admin_VendorRequestsController controller = loader.getController();
        controller.initdata(adminUname); // Replace with actual values
        System.out.println(adminUname);
        borderpane.setCenter(root);
    }

    @FXML
    private void goToRunnerReqScene(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Admin_DeliveryRunnerRequests.fxml"));
        Parent root = loader.load();
        Admin_DeliveryRunnerRequestsController controller = loader.getController();
        controller.initdata(adminUname); // Replace with actual values
        borderpane.setCenter(root);
    }

}
