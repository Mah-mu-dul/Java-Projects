package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.TopUpReq;

public class Customer_creditTopUpController implements Initializable {

    String uname;
    String customerName = "name will come db";
    @FXML
    private TextField ammount;

    Customer customer;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initdata(Customer c) {
        customer = c;

    }

    @FXML
    private void reqBtnOnclick(ActionEvent event) {
        TopUpReq t = new TopUpReq(1, customer.getUsername(), customerName, Float.parseFloat(ammount.getText()));
        File f = null;
        FileWriter fw = null;
        FileChooser fc = null;
        try {

            f = new File("TopUpRequests.txt");

            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }

            fw.write(t.writable());

        } catch (IOException ex) {
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {
            }
        }
    }

}
