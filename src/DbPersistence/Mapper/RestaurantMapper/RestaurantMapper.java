package DbPersistence.Mapper.RestaurantMapper;

import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;
import Restaurant.Restaurant;

public interface RestaurantMapper {
    Restaurant entityToModel(RestaurantEntity restaurantEntity);

    RestaurantEntity modelToEntity(Restaurant restaurant);
}
