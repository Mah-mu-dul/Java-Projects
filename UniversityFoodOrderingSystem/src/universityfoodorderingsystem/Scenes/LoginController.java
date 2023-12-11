package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import universityfoodorderingsystem.ModelClasses.Customer;

public class LoginController implements Initializable {
    
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private TextField usernameInput;
    @FXML
    private TextField paswardInput;
    boolean matchFound = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        typeCombo.getItems().addAll("Vendor", "Customer", "Delivery Runner", "Administrator");
        typeCombo.getSelectionModel().select("Customer");
    }
    
    @FXML
    private void LoginBtnOncCick(ActionEvent event) throws IOException {
        if (usernameInput.getText().length() > 3 && paswardInput.getText().length() > 3) {
            if (typeCombo.getValue().equals("Vendor")) {
                
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
                            
                            if (tokens[0].split("=")[1].equals(usernameInput.getText())
                                    && tokens[1].split("=")[1].equals(paswardInput.getText())) {
                                
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("Vendor_Dashboard.fxml"));
                                Parent personViewParent = loader.load();
                                Scene personViewScene = new Scene(personViewParent);
                                Vendor_DashboardController controller = loader.getController();
                                controller.initdata(usernameInput.getText(), tokens[4].split("=")[1]);   //  getting shop name from file
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(personViewScene);
                                window.show();
                                
                                matchFound = true;
                                
                                break;
                                
                            }
                            
                        }
                        
                    } else {
                    }
                } catch (IOException ex) {
                } finally {
                }
                
            } else if (typeCombo.getValue().equals("Customer")) {
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
                            
                            if (tokens[0].split("=")[1].equals(usernameInput.getText())
                                    && tokens[1].split("=")[1].equals(paswardInput.getText())) {
                                Customer customer = new Customer(
                                        tokens[4].split("=")[1],
                                        Float.parseFloat(tokens[5].split("=")[1]),
                                        tokens[0].split("=")[1],
                                        tokens[1].split("=")[1],
                                        tokens[2].split("=")[1],
                                        tokens[3].split("=")[1]
                                );
                                
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("Customer_Dashboard.fxml"));
                                Parent personViewParent = loader.load();
                                Scene personViewScene = new Scene(personViewParent);
                                Customer_DashboardController controller = loader.getController();
                                controller.initdata(customer);
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(personViewScene);
                                window.show();
                                matchFound = true;
                                
                                break;
                                
                            }
                            
                        }
                        
                    } else {
                    }
                } catch (IOException ex) {
                } finally {
                }
                
            } else if (typeCombo.getValue().equals("Delivery Runner")) {
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
                            
                            if (tokens[0].split("=")[1].equals(usernameInput.getText())
                                    && tokens[1].split("=")[1].equals(paswardInput.getText())) {
                                
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("DeliveryRunner_Dashboard.fxml"));
                                Parent personViewParent = loader.load();
                                Scene personViewScene = new Scene(personViewParent);
                                DeliveryRunner_DashboardController controller = loader.getController();
                                controller.initdata(usernameInput.getText());
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(personViewScene);
                                window.show();
                                matchFound = true;
                                
                                break;
                                
                            }
                            
                        }
                    } else {
                    }
                } catch (IOException ex) {
                } finally {
                }
                
            } else if (typeCombo.getValue().equals("Administrator")) {
                File f = null;
                //FileReader fw = null;
                Scanner sc;
                String str;
                String[] tokens;
                try {
                    f = new File("Administrators.txt");
                    sc = new Scanner(f);
                    if (f.exists()) {
                        while (sc.hasNextLine()) {
                            str = sc.nextLine();
                            tokens = str.split("<>");
                            
                            if (tokens[0].split("=")[1].equals(usernameInput.getText())
                                    && tokens[1].split("=")[1].equals(paswardInput.getText())) {
                                
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("Administrator_Dashboard.fxml"));
                                Parent personViewParent = loader.load();
                                Scene personViewScene = new Scene(personViewParent);
                                Administrator_DashboardController controller = loader.getController();
                                controller.initdata(usernameInput.getText());
                                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                                window.setScene(personViewScene);
                                window.show();
                                matchFound = true;
                                
                                break;
                                
                            } else {
                                Alert alert = new Alert(AlertType.WARNING);
                                alert.setHeaderText("User Name and pass not matched");
                                alert.showAndWait();
                            }
                            
                        }
                    } else {
                    }
                } catch (IOException ex) {
                } finally {
                }
                
            }
            if (!matchFound) {
                // No match found, show the alert
                Alert alert = new Alert(AlertType.WARNING);
                alert.setHeaderText("User Name and password not matched for ");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setHeaderText("Plese Provide Username and Password");
            
            alert.showAndWait();
        }
        
    }
    
    @FXML
    private void SignUpBtnOncCick(ActionEvent event) throws IOException {
        Parent mainSceneParent = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        Scene scene1 = new Scene(mainSceneParent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(scene1);
        window.show();
    }
    
}
