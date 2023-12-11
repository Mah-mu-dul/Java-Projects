package universityfoodorderingsystem.ModelClasses;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TopUpReceipt implements Serializable {

    private int requestId;
    private String customerUsername;
    private String adminUsername;
    private String name;
    private float amount;
    private Date date;

    public TopUpReceipt(int requestId, String customerUsername, String adminUsername, String name, float amount, Date date) {
        this.requestId = requestId;
        this.customerUsername = customerUsername;
        this.adminUsername = adminUsername;
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    // Getters and setters
    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getAdminUsername() {
        return adminUsername;
    }

    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(date);

        return "Request ID: " + requestId + "\n"
                + "Customer: " + customerUsername + "\n"
                + "Admin: " + adminUsername + "\n"
                + "Name: " + name + "\n"
                + "Amount: $" + amount + "\n"
                + "Date: " + formattedDate + "<>"
                + "\n";
    }
}
