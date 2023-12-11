package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import universityfoodorderingsystem.ModelClasses.Administrator;
import universityfoodorderingsystem.ModelClasses.SignUpReq_Customer;
import universityfoodorderingsystem.ModelClasses.SignUpReq_DeliveryRunner;
import universityfoodorderingsystem.ModelClasses.VendorReq;

public class SignUpController implements Initializable {

    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField paswardInput;
    @FXML
    private TextField fullname;
    @FXML
    private TextField extraInput;

    @FXML
    private Label extraField;
    @FXML
    private Button signUpBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCombo.getItems().addAll("Vendor", "Customer", "Delivery Runner", "Administrator");
        typeCombo.getSelectionModel().select("Administrator");
        extraInput.setVisible(false);
        extraField.setVisible(false);

    }

    @FXML
    private void signupBtnOncCick(ActionEvent event) {

        String selectedType = typeCombo.getValue();

        if (selectedType != null) {
            String username = usernameInput.getText();
            String password = paswardInput.getText();
            String name = fullname.getText();  // You should get the name from the user input

            switch (selectedType) {
                case "Customer":

                    String address = extraInput.getText();
                    if (address.length()>0) {
                        SignUpReq_Customer customerReq = new SignUpReq_Customer(username, password, name, address);
                        saveSignUpRequestToFile(customerReq, "CustomerRequest.txt");
                        break;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("Address is empty");
                        alert.showAndWait();
                    }
                case "Vendor":
                    String shopName = extraInput.getText();
                    if (shopName.length()>0) {

                        VendorReq vendorReq = new VendorReq(username, password, name, shopName);
                        saveSignUpRequestToFile(vendorReq, "VendorRequest.txt");
                        break;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("shop name is empty");
                        alert.showAndWait();
                    }

                case "Delivery Runner":
                    SignUpReq_DeliveryRunner deliveryRunnerReq = new SignUpReq_DeliveryRunner(username, password, name);
                    saveSignUpRequestToFile(deliveryRunnerReq, "DeliveryRunnerRequest.txt");
                    break;

                case "Administrator":
                    Administrator administrator = new Administrator(username, password, name, "");  // Assuming 0 as a placeholder for addedBy
                    FileWriter fw = null;
                    try {
                        File file = new File("Administrators.txt");

                        if (file.exists()) {
                            fw = new FileWriter(file, true);
                        } else {
                            fw = new FileWriter(file);
                        }

                        fw.write(administrator.toString());
                    } catch (IOException ex) {
                        // Handle IOException (e.g., log the exception)
                    } finally {
                        try {
                            if (fw != null) {
                                fw.close();
                            }
                        } catch (IOException ex) {
                            // Handle IOException on closing the FileWriter
                        }
                    }
                    break;
                default:
                    // Handle unsupported user type
                    break;
            }
        }
        usernameInput.setText("");
        paswardInput.setText("");
        fullname.setText("");
        extraInput.setText("");

    }
    // Helper method to save user to file

    private void saveSignUpRequestToFile(Object signUpRequest, String fileName) {
        FileWriter fw = null;
        try {
            File file = new File(fileName);

            if (file.exists()) {
                fw = new FileWriter(file, true);
            } else {
                fw = new FileWriter(file);
            }

            fw.write(signUpRequest.toString());
        } catch (IOException ex) {
            // Handle IOException (e.g., log the exception)
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException ex) {
                // Handle IOException on closing the FileWriter
            }
        }
    }

    @FXML
    private void loginBtnOncCick(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

    @FXML
    private void CobmoChange(ActionEvent event) {
        String selectedType = typeCombo.getValue();

        // Handle visibility and label text based on the selected user type
        if ("Administrator".equals(selectedType)) {
            signUpBtn.setText("Sign up");
        } else {
            signUpBtn.setText("Sign up request");
        }
        if ("Customer".equals(selectedType)) {
            extraInput.setVisible(true);
            extraField.setVisible(true);
            extraField.setText("Address");

        } else if ("Vendor".equals(selectedType)) {
            extraInput.setVisible(true);
            extraField.setVisible(true);
            extraField.setText("Shop Name");
        } else {
            extraInput.setVisible(false);
            extraField.setVisible(false);
        }
        System.out.println("changed combo" + typeCombo.getValue());
    }

}
