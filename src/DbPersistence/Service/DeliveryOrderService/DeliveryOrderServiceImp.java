package DbPersistence.Service.DeliveryOrderService;

import DbPersistence.Dao.DeliveryOrderDao.DeliveryOrderDao;
import DbPersistence.Dao.DeliveryOrderDao.DeliveryOrderDaoImp;
import DbPersistence.Mapper.DeliveryOrderMapper.DeliveryOrderEntityMapper;
import DbPersistence.Mapper.DeliveryOrderMapper.DeliveryOrderMapper;
import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;
import User.Order.DeliveryOrder.DeliveryOrder;

import java.util.List;
import java.util.function.Predicate;

public class DeliveryOrderServiceImp implements DeliveryOrderService {

    private final DeliveryOrderDao deliveryOrderDao = new DeliveryOrderDaoImp();
    private final DeliveryOrderMapper deliveryOrderMapper = new DeliveryOrderEntityMapper();

    @Override
    public DeliveryOrder create(DeliveryOrder deliveryOrder) {
        DeliveryOrderEntity deliveryOrderEntity = deliveryOrderMapper.modelToEntity(deliveryOrder);
        DeliveryOrderEntity savedDeliveryOrderEntity = this.deliveryOrderDao.create(deliveryOrderEntity);
        return deliveryOrder;
    }

    @Override
    public List<DeliveryOrder> read() {
        return null;
    }

    @Override
    public DeliveryOrder update(DeliveryOrder deliveryOrder, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public DeliveryOrder findById(Integer id) {
        return null;
    }

    @Override
    public List<DeliveryOrder> findAllBy(Predicate<DeliveryOrderEntity> predicate) {
        return null;
    }
}
