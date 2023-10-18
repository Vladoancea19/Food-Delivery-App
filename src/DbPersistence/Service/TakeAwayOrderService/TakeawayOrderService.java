package DbPersistence.Service.TakeAwayOrderService;

import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;
import User.Order.TakeawayOrder.TakeawayOrder;

import java.util.List;
import java.util.function.Predicate;

public interface TakeawayOrderService {
    TakeawayOrder create(TakeawayOrder takeAwayOrder);

    List<TakeawayOrder> read();

    TakeawayOrder update(TakeawayOrder takeAwayOrder, Integer id);

    String delete(Integer id);

    TakeawayOrder findById(Integer id);

    List<TakeawayOrder> findAllBy(Predicate<TakeawayOrderEntity> predicate);
}
