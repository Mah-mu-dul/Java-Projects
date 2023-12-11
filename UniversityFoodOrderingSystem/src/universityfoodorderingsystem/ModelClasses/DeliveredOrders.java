package universityfoodorderingsystem.ModelClasses;

public class DeliveredOrders {

    int OrderId;
    String DeliveryRunnerId;

    public DeliveredOrders(int OrderId, String DeliveryRunnerId) {
        this.OrderId = OrderId;
        this.DeliveryRunnerId = DeliveryRunnerId;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int OrderId) {
        this.OrderId = OrderId;
    }

    public String getDeliveryRunnerId() {
        return DeliveryRunnerId;
    }

    public void setDeliveryRunnerId(String DeliveryRunnerId) {
        this.DeliveryRunnerId = DeliveryRunnerId;
    }

    @Override
    public String toString() {
        return "OrderId=" + OrderId + ", DeliveryRunnerId=" + DeliveryRunnerId + '\n';
    }

}
