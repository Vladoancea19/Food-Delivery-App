package User.Order;

import Restaurant.Category.Product.Product;

import java.util.Map;
import java.util.Objects;

public class Order {

    protected Map<Product, Integer> productsOrdered;
    protected float productsPrice;
    protected PaymentMethod paymentMethod;

    public Order(Map<Product, Integer> productsOrdered, PaymentMethod paymentMethod) {
        this.productsOrdered = productsOrdered;
        float price = 0;
        for(Map.Entry<Product, Integer> productQuantity : productsOrdered.entrySet()) {
            price += productQuantity.getKey().getPrice() * productQuantity.getValue();
        }
        this.productsPrice = price;
        this.paymentMethod = paymentMethod;
    }

    public Order() {

    }

    public Map<Product, Integer> getProductsOrdered() {
        return productsOrdered;
    }

    public void setProductsOrdered(Map<Product, Integer> productsOrdered) {
        this.productsOrdered = productsOrdered;
    }

    public float getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(float productsPrice) {
        this.productsPrice = productsPrice;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                ", productsOrdered=" + productsOrdered +
                ", productsPrice=" + productsPrice +
                ", paymentMethod=" + paymentMethod +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;
        return Float.compare(order.productsPrice, productsPrice) == 0 && productsOrdered.equals(order.productsOrdered) && paymentMethod == order.paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productsOrdered, productsPrice, paymentMethod);
    }
}
