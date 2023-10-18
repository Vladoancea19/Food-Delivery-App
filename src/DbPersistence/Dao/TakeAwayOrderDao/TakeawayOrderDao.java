package DbPersistence.Dao.TakeAwayOrderDao;

import DbPersistence.Crud;
import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;

import java.util.List;
import java.util.function.Predicate;

public interface TakeawayOrderDao extends Crud<TakeawayOrderEntity, Integer> {

    List<TakeawayOrderEntity> findAllBy(Predicate<TakeawayOrderEntity> predicate);
}
