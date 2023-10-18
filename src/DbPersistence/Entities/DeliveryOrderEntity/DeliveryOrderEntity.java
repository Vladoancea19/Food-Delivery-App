package DbPersistence.Entities.DeliveryOrderEntity;

public class DeliveryOrderEntity {
    private int deliveryOrderId;
    private String productsOrdered;
    private float productsPrice;
    private String paymentMethod;
    private int deliveryPrice;
    private String deliveryAddress;

    public DeliveryOrderEntity() {
    }

    public int getDeliveryOrderId() {
        return deliveryOrderId;
    }

    public void setDeliveryOrderId(int deliveryOrderId) {
        this.deliveryOrderId = deliveryOrderId;
    }

    public String getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(String productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public float getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(float productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(int deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
