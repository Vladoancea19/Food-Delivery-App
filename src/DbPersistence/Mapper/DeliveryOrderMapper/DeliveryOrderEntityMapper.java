package DbPersistence.Mapper.DeliveryOrderMapper;

import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;
import Restaurant.Category.Product.Product;
import User.Order.DeliveryOrder.DeliveryOrder;

import java.util.Map;

public class DeliveryOrderEntityMapper implements DeliveryOrderMapper{
    @Override
    public DeliveryOrder entityToModel(DeliveryOrderEntity deliveryOrderEntity) {
        return null;
    }

    @Override
    public DeliveryOrderEntity modelToEntity(DeliveryOrder deliveryOrder) {
        DeliveryOrderEntity deliveryOrderEntity = new DeliveryOrderEntity();
        StringBuilder productsOrdered = new StringBuilder();
        for(Map.Entry<Product, Integer> productMap : deliveryOrder.getProductsOrdered().entrySet()) {
            productsOrdered.append(productMap.getKey().getName() + " x" + productMap.getValue() + " ");
        }
        deliveryOrderEntity.setProductsOrdered(productsOrdered.toString());
        deliveryOrderEntity.setProductsPrice(deliveryOrder.getProductsPrice());
        deliveryOrderEntity.setPaymentMethod(deliveryOrder.getPaymentMethod().toString());
        deliveryOrderEntity.setDeliveryPrice(deliveryOrder.getDeliveryPrice());
        deliveryOrderEntity.setDeliveryAddress(deliveryOrder.getDeliveryAddress());
        return deliveryOrderEntity;
    }
}
