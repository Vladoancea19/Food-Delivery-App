package DbPersistence.Dao.RestaurantDao;

import DbPersistence.Crud;
import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;

import java.util.List;
import java.util.function.Predicate;

public interface RestaurantDao extends Crud<RestaurantEntity, Integer> {
    List<RestaurantEntity> findAllBy(Predicate<RestaurantEntity> predicate);
}
