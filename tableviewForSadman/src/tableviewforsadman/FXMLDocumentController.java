package tableviewforsadman;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {

    private Label label;
    @FXML
    private TextField quantity;
    @FXML
    private ComboBox<String> nameCombo;
    @FXML
    private TableView<Order> table;
    @FXML
    private TableColumn<Order, Integer> idCol;
    @FXML
    private TableColumn<Order, String> nameCol;
    @FXML
    private TableColumn<Order, Integer> QuantityCol;
    @FXML
    private TableColumn<Order, Float> unitPriceCol;
    @FXML
    private TableColumn<Order, Float> totalPriceCol;
    @FXML
    private TableColumn<Order, String> descriptionCol;

    ArrayList<Item> items;
    ObservableList<Order> orders = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items = new ArrayList<>();
        items.add(new Item("Onion", "description for onion", 300, 100, 130));
        items.add(new Item("Milk", "description for mild", 500, 101, 50));
        items.add(new Item("Rice", "description for Rice", 400, 102, 70));
        items.add(new Item("Oil", "description for Oil", 800, 103, 400));

        for (Item i : items) {
            nameCombo.getItems().add(i.getName());
        }
        nameCombo.getSelectionModel().select("Onion");

        idCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("ItemId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Order, String>("ItemName"));
        QuantityCol.setCellValueFactory(new PropertyValueFactory<Order, Integer>("quantity"));
        unitPriceCol.setCellValueFactory(new PropertyValueFactory<Order, Float>("itemUnitPrice"));
        totalPriceCol.setCellValueFactory(new PropertyValueFactory<Order, Float>("TotalPrice"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<Order, String>("Description"));

        table.setItems(orders);

    }

    @FXML
    private void addBtnClick(ActionEvent event) {
        String name = nameCombo.getValue();
        Item i = getItem(name);
//        System.out.println(quantity.getText());
        if (i != null && quantity.getText().length() > 0) {
            Order o = new Order(name, i.getId(), Integer.parseInt(quantity.getText()), i.getUnitPrice(), i.getDescription());
            orders.add(o);

        }
    }

    private Item getItem(String name) {
        for (Item i : items) {
            if (i.getName().equals(name)) {
                return i;
            }
        }
        return null;
    }

}
