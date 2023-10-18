package User.Order.DeliveryOrder;

import Restaurant.Category.Product.Product;
import User.Order.Order;
import User.Order.PaymentMethod;

import java.util.Map;
import java.util.Objects;

public class DeliveryOrder extends Order {

    private int deliveryPrice;
    private String deliveryAddress;

    public DeliveryOrder(Map<Product, Integer> productsOrdered, PaymentMethod paymentMethod, int deliveryPrice, String deliveryAddress) {
        super(productsOrdered, paymentMethod);
        this.deliveryPrice = deliveryPrice;
        this.deliveryAddress = deliveryAddress;
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

    @Override
    public String toString() {
        return "DeliveryOrder{" +
                "deliveryPrice=" + deliveryPrice +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeliveryOrder that)) return false;
        if (!super.equals(o)) return false;
        return deliveryPrice == that.deliveryPrice && deliveryAddress.equals(that.deliveryAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deliveryPrice, deliveryAddress);
    }
}
