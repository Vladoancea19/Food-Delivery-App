package DbPersistence.Service.DeliveryOrderService;

import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;
import User.Order.DeliveryOrder.DeliveryOrder;

import java.util.List;
import java.util.function.Predicate;

public interface DeliveryOrderService {
    DeliveryOrder create(DeliveryOrder deliveryOrder);

    List<DeliveryOrder> read();

    DeliveryOrder update(DeliveryOrder deliveryOrder, Integer id);

    String delete(Integer id);

    DeliveryOrder findById(Integer id);

    List<DeliveryOrder> findAllBy(Predicate<DeliveryOrderEntity> predicate);
}
