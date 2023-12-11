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

public class LoginController implements Initializable {

    ArrayList<Manager> managerList;
    ArrayList<Stuff> stuffList;

    @FXML
    private TextField uname;
    @FXML
    private PasswordField pass;
    @FXML
    private ComboBox<String> type;
    @FXML
    private Label warning;

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        type.getItems().addAll("Manager", "Stuff");
        type.getSelectionModel().select("Manager");

    }

    public void initData(ArrayList managerList, ArrayList stuffList) {
        this.managerList = managerList;
        this.stuffList = stuffList;

    }

    @FXML
    private void LoginBtnClick(ActionEvent event) throws IOException {
        String username = uname.getText();
        String password = pass.getText();

        try {

            if (type.getValue().equals("Manager")) {

                for (Manager m : managerList) {
                    if (username.equals(m.uname) && m.checkPass(password)) {
                        System.out.println(m.uname);
                        //                 scene change with parameter

                        FXMLLoader loader = new FXMLLoader();

                        loader.setLocation(getClass().getResource("ManagerDashboard.fxml"));
                        Parent personViewParent = loader.load();

                        Scene personViewScene = new Scene(personViewParent);

                        ManagerDashboardController controller = loader.getController();
                        controller.initdata(username, m);

                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

                        window.setScene(personViewScene);
                        window.show();
                    }
                }

//                 code for  scene change without parameter
/*           Parent mainSceneParent = FXMLLoader.load(getClass().getResource("ManagerDashboard.fxml"));
                Scene scene1 = new Scene(mainSceneParent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene1);
                window.show();
                 */
//
//
//
//
//
            } else {
                warning.setText("kichu ekta vejal hoiche !!!!");
            }
        } catch (Exception e) {
            warning.setText("please select type.");

        }

    }

    @FXML
    private void clearwarning(MouseEvent event) {
        warning.setText("");

    }

    @FXML
    private void signupbtnclick(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }

}
