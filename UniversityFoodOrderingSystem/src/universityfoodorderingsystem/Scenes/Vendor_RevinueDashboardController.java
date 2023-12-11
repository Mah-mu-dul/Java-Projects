package universityfoodorderingsystem.Scenes;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import universityfoodorderingsystem.ModelClasses.Order;

public class Vendor_RevinueDashboardController implements Initializable {

    @FXML
    private BarChart<String, Number> barchart;
    @FXML
    private PieChart pichart;
    private ObservableList<PieChart.Data> list = FXCollections.observableArrayList();
    private ObservableList<Order> filteredData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void initdata(String shopName) {
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();

        File f = null;
        Scanner sc;
        String str;
        String[] tokens;

        try {
            f = new File("Orders.txt");
            sc = new Scanner(f);
            if (f.exists()) {
                while (sc.hasNextLine()) {
                    str = sc.nextLine();
                    tokens = str.split(",");

                    if (tokens[2].split("=")[1].equals(shopName) && tokens[4].split("=")[1].equals("Complete")) {

                        Order o = new Order(
                                Integer.parseInt(tokens[6].split("=")[1]),
                                Integer.parseInt(tokens[0].split("=")[1]),
                                Integer.parseInt(tokens[1].split("=")[1]),
                                tokens[3].split("=")[1],
                                tokens[4].split("=")[1],
                                tokens[5].split("=")[1]
                        );

                        // Add the order to the filteredData list
                        filteredData.add(o);
                    }

                }
            } else {
            }
        } catch (IOException ex) {
            Logger.getLogger(Customer_ShowAllProductsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }

        // Process filteredData to get total price and unique product names
        Map<String, Float> productPriceMap = new HashMap<>();
        for (Order order : filteredData) {
            String productName = order.getProductName();
            float totalPrice = order.getTotalPrice();

            productPriceMap.merge(productName, totalPrice, Float::sum);
        }

        // Create PieChart data from the map
        list.clear();
        for (Map.Entry<String, Float> entry : productPriceMap.entrySet()) {
            String productName = entry.getKey();
            float totalCalculatedPrice = entry.getValue();
            list.add(new PieChart.Data(productName, totalCalculatedPrice));
        }

        // Add PieChart data to PieChart
        pichart.setData(list);

        // Create BarChart data from the map
        series.getData().clear();
        for (Map.Entry<String, Float> entry : productPriceMap.entrySet()) {
            String productName = entry.getKey();
            float totalCalculatedPrice = entry.getValue();
            series.getData().add(new XYChart.Data<>(productName, totalCalculatedPrice));
        }

        // Add BarChart data to BarChart
        barchart.getData().add(series);
    }
}
