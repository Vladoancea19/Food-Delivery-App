package DbPersistence.Mapper.RestaurantMapper;

import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;
import Restaurant.Restaurant;

public class RestaurantEntityMapper implements RestaurantMapper{

    @Override
    public Restaurant entityToModel(RestaurantEntity restaurantEntity) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantEntity.getRestaurantName());
        restaurant.setCategories(restaurantEntity.getRestaurantCategories());
        restaurant.setReviews(restaurantEntity.getRestaurantReviews());
        restaurant.setScore(restaurantEntity.getRestaurantScore());
        return restaurant;
    }

    @Override
    public RestaurantEntity modelToEntity(Restaurant restaurant) {
        return null;
    }
}
