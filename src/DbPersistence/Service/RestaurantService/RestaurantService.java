package DbPersistence.Service.RestaurantService;

import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;
import Restaurant.Restaurant;

import java.util.List;
import java.util.function.Predicate;

public interface RestaurantService {
    Restaurant create(Restaurant restaurant);

    List<Restaurant> read();

    Restaurant update(Restaurant restaurant, Integer id);

    String delete(Integer id);

    Restaurant findById(Integer id);

    List<Restaurant> findAllBy(Predicate<RestaurantEntity> predicate);
}
