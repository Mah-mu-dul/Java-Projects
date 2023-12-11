package mianpkg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AddProductController implements Initializable {

    @FXML
    private TextField nameTextField;
    @FXML
    private TextField quantityTextField;
    @FXML
    private TextField priceTextField;
    @FXML
    private TextField vatTextField;
    @FXML
    private TableView<Product> productTable;
    @FXML
    private TableColumn<Product, String> nameCol;
    @FXML
    private TableColumn<Product, Integer> QuantityCol;
    @FXML
    private TableColumn<Product, Float> priceCol;
    @FXML
    private TableColumn<Product, Integer> vatCol;
    @FXML
    private ComboBox<String> ProductNameCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Products.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Product p;
            try {
                while (true) {
                    p = (Product) ois.readObject();
                    if (!ProductNameCombo.getItems().contains(p.name)) {
                        ProductNameCombo.getItems().add(p.name);
                    }
                    System.out.println(p.toString());
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }

        }
    }

    @FXML
    private void AddBtnOnAction(ActionEvent event) {
        Product i = new Product(nameTextField.getText(), Float.parseFloat(priceTextField.getText()), Integer.parseInt(vatTextField.getText()), Integer.parseInt(quantityTextField.getText()));
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        File f = null;
        try {
            f = new File("Products.bin");
            if (f.exists()) {
                fos = new FileOutputStream(f, true);
                oos = new AppendableObjectOutputStream(fos);
            } else {
                fos = new FileOutputStream(f);
                oos = new ObjectOutputStream(fos);
            }

            oos.writeObject(i);
            System.out.println("write object sucessfull ");

        } catch (IOException ex) {
            Logger.getLogger(AddProductController.class
                    .getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (oos != null) {
                    oos.close();

                }
            } catch (IOException ex) {
                Logger.getLogger(AddProductController.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        ObservableList<Product> ProductList = FXCollections.observableArrayList();
        //    formate:  columnFxid.setCellValueFactory(new PropertyValueFactory<ModelClass, Type>("ModelcCassFieldName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stockQuantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        vatCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Products.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Product p;
            try {
                while (true) {
                    p = (Product) ois.readObject();
                    ProductList.add(p);
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }

        }
        productTable.setItems(ProductList);
    }

    @FXML
    private void reLoadTableBtnOnAction(ActionEvent event) {
        ObservableList<Product> ProductList = FXCollections.observableArrayList();
        //    formate:  columnFxid.setCellValueFactory(new PropertyValueFactory<ModelClass, Type>("ModelcCassFieldName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stockQuantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        vatCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Products.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Product p;
            try {
                while (true) {
                    p = (Product) ois.readObject();
                    ProductList.add(p);
                    System.out.println(p.toString());
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }

        }
        productTable.setItems(ProductList);

    }

    @FXML
    private void selectedAnItemAction(ActionEvent event) {
        ObservableList<Product> ProductList = FXCollections.observableArrayList();
        //    formate:  columnFxid.setCellValueFactory(new PropertyValueFactory<ModelClass, Type>("ModelcCassFieldName"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Product, String>("name"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("stockQuantity"));
        priceCol.setCellValueFactory(new PropertyValueFactory<Product, Float>("price"));
        vatCol.setCellValueFactory(new PropertyValueFactory<Product, Integer>("price"));

        File f = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            f = new File("Products.bin");
            fis = new FileInputStream(f);
            ois = new ObjectInputStream(fis);
            Product p;
            try {
                while (true) {
                    p = (Product) ois.readObject();
                    if (p.name.equals(ProductNameCombo.getValue())) {
                        ProductList.add(p);
                    }
                    System.out.println(p.toString());
                }
            } catch (Exception e) {
            }
        } catch (IOException ex) {
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException ex) {
            }

        }
        productTable.setItems(ProductList);
    }

}
