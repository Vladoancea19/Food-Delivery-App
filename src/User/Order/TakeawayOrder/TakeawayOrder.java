package User.Order.TakeawayOrder;

import Restaurant.Category.Product.Product;
import User.Order.Order;
import User.Order.PaymentMethod;

import java.util.Map;
import java.util.Objects;

public class TakeawayOrder extends Order {

    private int takeAwayDiscount;


    public TakeawayOrder(Map<Product, Integer> productsOrdered, PaymentMethod paymentMethod, int takeAwayDiscount) {
        super(productsOrdered, paymentMethod);
        this.takeAwayDiscount = takeAwayDiscount;
    }

    public TakeawayOrder() {
        super();
    }

    public int getTakeAwayDiscount() {
        return takeAwayDiscount;
    }

    public void setTakeAwayDiscount(int takeAwayDiscount) {
        this.takeAwayDiscount = takeAwayDiscount;
    }

    @Override
    public String toString() {
        return "TakeAwayOrder{" +
                "takeAwayDiscount=" + takeAwayDiscount +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TakeawayOrder that)) return false;
        if (!super.equals(o)) return false;
        return takeAwayDiscount == that.takeAwayDiscount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), takeAwayDiscount);
    }
}
