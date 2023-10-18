package DbPersistence.Mapper.TakeAwayOrderMapper;

import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;
import User.Order.TakeawayOrder.TakeawayOrder;

public interface TakeawayOrderMapper {
    TakeawayOrder entityToModel(TakeawayOrderEntity takeawayOrderEntity);

    TakeawayOrderEntity modelToEntity(TakeawayOrder takeAwayOrder);
}
