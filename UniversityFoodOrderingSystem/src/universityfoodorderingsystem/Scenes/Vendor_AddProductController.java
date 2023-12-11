/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import universityfoodorderingsystem.ModelClasses.Product;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class Vendor_AddProductController implements Initializable {

    @FXML
    private TextField ProductNameInput;
    @FXML
    private TextField priceInput;
    @FXML
    private TextField stockQuantityInput;
    @FXML
    private TextArea descriptionInput;
    String shopname, uname;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initdata(String uname, String shopname) {
        this.shopname = shopname;
        this.uname = uname;

    }

    @FXML
    private void AddProductBtnOnClick(ActionEvent event) {
        Product p = new Product(Product.getLastProductId() + 1, Integer.parseInt(stockQuantityInput.getText()), ProductNameInput.getText(), descriptionInput.getText(), shopname, Float.parseFloat(priceInput.getText()));
        File f = null;
        FileWriter fw = null;
        FileChooser fc = null;
        try {

            f = new File("Products.txt");

            if (f.exists()) {
                fw = new FileWriter(f, true);
            } else {
                fw = new FileWriter(f);
            }

            fw.write(p.writable());

        } catch (IOException ex) {
            Logger.getLogger(Vendor_AddProductController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }

            } catch (IOException ex) {
                Logger.getLogger(Vendor_AddProductController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
