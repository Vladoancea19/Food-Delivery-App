package DbPersistence.Dao.DeliveryOrderDao;

import DbPersistence.Crud;
import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;

import java.util.List;
import java.util.function.Predicate;

public interface DeliveryOrderDao extends Crud<DeliveryOrderEntity, Integer> {
    List<DeliveryOrderEntity> findAllBy(Predicate<DeliveryOrderEntity> predicate);
}
