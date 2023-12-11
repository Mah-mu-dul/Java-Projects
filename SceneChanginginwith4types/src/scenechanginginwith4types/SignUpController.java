package scenechanginginwith4types;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SignUpController implements Initializable {

    @FXML
    private TextField uname;
    @FXML
    private ComboBox<String> type;
    @FXML
    private PasswordField pass;
    @FXML
    private Label warning;
    @FXML
    private TextField name;

    ArrayList<Manager> managerList;
    ArrayList<Stuff> stuffList;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        type.getItems().addAll("Manager", "Stuff");
        type.getSelectionModel().select("Manager");
        managerList = new ArrayList();
        stuffList = new ArrayList();

    }

    @FXML
    private void clearwarning(MouseEvent event) {
        warning.setText("");
    }

    @FXML
    private void SignupBtnClick(ActionEvent event) {
        try {
            String userType = type.getValue();
            String username = uname.getText();
            String password = pass.getText();
            String fullName = name.getText();

            if (userType.equals("Manager")) {
                Manager m = new Manager(fullName, password, username);
                managerList.add(m);
            } else if (userType.equals("Stuff")) {
                Stuff s = new Stuff(fullName, password, username);
                stuffList.add(s);

            }
        } catch (Exception e) {
            warning.setText("please select type !!!");
        }

    }

    @FXML
    private void goToLoginPage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(getClass().getResource("Login.fxml"));
        Parent personViewParent = loader.load();

        Scene personViewScene = new Scene(personViewParent);

        LoginController controller = loader.getController();
        controller.initData(managerList, stuffList);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(personViewScene);
        window.show();

    }

}
