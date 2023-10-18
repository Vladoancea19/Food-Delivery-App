package DbPersistence.Mapper.DeliveryOrderMapper;

import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;
import User.Order.DeliveryOrder.DeliveryOrder;

public interface DeliveryOrderMapper {
    DeliveryOrder entityToModel(DeliveryOrderEntity deliveryOrderEntity);
    DeliveryOrderEntity modelToEntity(DeliveryOrder deliveryOrder);
}
