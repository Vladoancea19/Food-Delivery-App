package DbPersistence.Mapper.TakeAwayOrderMapper;

import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;
import Restaurant.Category.Product.Product;
import User.Order.TakeawayOrder.TakeawayOrder;

import java.util.Map;

public class TakeawayOrderEntityMapper implements TakeawayOrderMapper {
    @Override
    public TakeawayOrder entityToModel(TakeawayOrderEntity takeawayOrderEntity) {
        return null;
    }

    @Override
    public TakeawayOrderEntity modelToEntity(TakeawayOrder takeAwayOrder) {
        TakeawayOrderEntity takeawayOrderEntity = new TakeawayOrderEntity();
        StringBuilder productsOrdered = new StringBuilder();
        for(Map.Entry<Product, Integer> productMap : takeAwayOrder.getProductsOrdered().entrySet()) {
            productsOrdered.append(productMap.getKey().getName() + " x" + productMap.getValue() + " ");
        }
        takeawayOrderEntity.setProductsOrdered(productsOrdered.toString());
        takeawayOrderEntity.setProductsPrice(takeAwayOrder.getProductsPrice());
        takeawayOrderEntity.setPaymentMethod(takeAwayOrder.getPaymentMethod().toString());
        takeawayOrderEntity.setTakeawayDiscount(takeAwayOrder.getTakeAwayDiscount());
        return takeawayOrderEntity;
    }
}
