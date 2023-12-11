package universityfoodorderingsystem.Scenes;

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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Vendor_DashboardController implements Initializable {

    @FXML
    private Label VendorName;
    @FXML
    private BorderPane BorderPane;

    String uname;
    String shopname;  // read vendors.txt file and replace the shop name.

    public void initdata(String uname, String shopname) {
        this.uname = uname;
        VendorName.setText(uname);
        this.shopname = shopname;

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
    private void ViewOrdersBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendor_ViewOrders.fxml"));
        Parent root = loader.load();
        Vendor_ViewOrdersController controller = loader.getController();
        controller.initdata(uname, shopname); // Replace with actual values
        BorderPane.setCenter(root);
    }

    @FXML
    private void ViewProductsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendor_ViewMyProducts.fxml"));
        Parent root = loader.load();
        Vendor_ViewMyProductsController controller = loader.getController();
        controller.initdata(uname, shopname); // Replace with actual values
        BorderPane.setCenter(root);
    }

    @FXML
    private void AddNewProductBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendor_AddProduct.fxml"));
        Parent root = loader.load();
        Vendor_AddProductController controller = loader.getController();
        controller.initdata(uname, shopname); // Replace with actual values
        BorderPane.setCenter(root);
    }

    @FXML
    private void ViewOrderHistoryBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendor_OrderHistory.fxml"));
        Parent root = loader.load();
        Vendor_OrderHistoryController controller = loader.getController();
        controller.initData(shopname); // Replace with actual values
        BorderPane.setCenter(root);
    }

    @FXML
    private void revinueDashboardBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Vendor_RevinueDashboard.fxml"));
        Parent root = loader.load();
        Vendor_RevinueDashboardController controller = loader.getController();
        controller.initdata(shopname); // Replace with actual values
        BorderPane.setCenter(root);
    }

}
