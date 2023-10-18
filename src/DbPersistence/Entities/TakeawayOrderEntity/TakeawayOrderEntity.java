package DbPersistence.Entities.TakeawayOrderEntity;

public class TakeawayOrderEntity {
    private int takeawayOrderId;
    private String productsOrdered;
    private float productsPrice;
    private String paymentMethod;
    private int takeawayDiscount;

    public TakeawayOrderEntity(int takeawayOrderId, String productsOrdered, float productsPrice, String paymentMethod, int takeAwayDiscount) {
        this.takeawayOrderId = takeawayOrderId;
        this.productsOrdered = productsOrdered;
        this.productsPrice = productsPrice;
        this.paymentMethod = paymentMethod;
        this.takeawayDiscount = takeAwayDiscount;
    }

    public TakeawayOrderEntity() {
    }

    public int getTakeawayOrderId() {
        return takeawayOrderId;
    }

    public void setTakeawayOrderId(int takeawayOrderId) {
        this.takeawayOrderId = takeawayOrderId;
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

    public int getTakeawayDiscount() {
        return takeawayDiscount;
    }

    public void setTakeawayDiscount(int takeawayDiscount) {
        this.takeawayDiscount = takeawayDiscount;
    }
}
