package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import universityfoodorderingsystem.ModelClasses.Customer;
import universityfoodorderingsystem.ModelClasses.User;
import universityfoodorderingsystem.ModelClasses.Vendor;
import universityfoodorderingsystem.ModelClasses.DeliveryRunner;

public class Admin_UpdateDeleteController implements Initializable {

    String uname;

    @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> unameCol;
    @FXML
    private TableColumn<User, String> nameCol;
    @FXML
    private TableColumn<User, String> extraFieldCol;
    @FXML
    private TableColumn<User, String> addedByCol;
    @FXML
    private TableColumn<User, String> actionCOl;
    @FXML
    private ComboBox<String> userTypeCombo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeCombo.getItems().addAll("Customer", "Vendor", "Delivery Runner");
        userTypeCombo.getSelectionModel().select("Customer");

        configureTableColumns("Customer");

        // Set the actionCol button
        actionCOl.setCellValueFactory(new PropertyValueFactory<>("action"));
        actionCOl.setCellFactory(column -> {
            return new TableCell<User, String>() {
                final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        // Call a method to handle the delete action
                        handleDeleteAction(getTableView().getItems().get(getIndex()));
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                    }
                }
            };
        });
    }

    private void configureTableColumns(String userType) {
        unameCol.setCellValueFactory(new PropertyValueFactory<>("username"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        addedByCol.setCellValueFactory(new PropertyValueFactory<>("addedBy"));

        // Set the cell value factory for the extraFieldCol based on the user type
        extraFieldCol.setCellValueFactory(cellData -> {
            User user = cellData.getValue();
            if (user instanceof Customer) {
                return new SimpleStringProperty(((Customer) user).getCustomerAddress());
            } else if (user instanceof Vendor) {
                return new SimpleStringProperty(((Vendor) user).getShoopName());
            } else {
                // For DeliveryRunner or any other type, return an empty property
                return new SimpleStringProperty("");
            }
        });

        // Load data from the respective text file
        loadDataFromTextFile(userType);
    }

    private void loadDataFromTextFile(String userType) {
        // Clear existing data
        ObservableList<User> userList = FXCollections.observableArrayList();
        table.getItems().clear();
        switch (userType) {
            case "Customer": {
                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("Customers.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split("<>");
                            Customer c = new Customer(
                                    tokens[4].split("=")[1],
                                    Float.parseFloat(tokens[5].split("=")[1]),
                                    tokens[0].split("=")[1],
                                    tokens[1].split("=")[1],
                                    tokens[2].split("=")[1],
                                    tokens[3].split("=")[1]
                            );
                            userList.add(c);

                        }
                    } else {
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
            case "Vendor": {
                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("Vendors.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split("<>");
                            Vendor v = new Vendor(
                                    tokens[4].split("=")[1],
                                    tokens[0].split("=")[1],
                                    tokens[1].split("=")[1],
                                    tokens[2].split("=")[1],
                                    tokens[3].split("=")[1]
                            );
                            userList.add(v);

                        }
                    } else {
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
            case "Delivery Runner": {
                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("DeliveryRunners.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split("<>");
                            DeliveryRunner v = new DeliveryRunner(
                                    tokens[0].split("=")[1],
                                    tokens[1].split("=")[1],
                                    tokens[2].split("=")[1],
                                    tokens[3].split("=")[1]
                            );
                            userList.add(v);

                        }
                    } else {
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
                } finally {
                }
            }
            // Add cases for other user types if needed
            default:

        }
        table.setItems(userList);

    }

    public void initData(String uname) {
        this.uname = uname;
    }

    @FXML
    private void ComboChange(ActionEvent event) {
        String selectedUserType = userTypeCombo.getValue();
        configureTableColumns(selectedUserType);
    }

    private void handleDeleteAction(User user) {
        // Add your logic to handle the delete action
        System.out.println("Delete action clicked for user: " + user.getUsername());
    }
}
