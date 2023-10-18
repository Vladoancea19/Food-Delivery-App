package DbPersistence.Service.RestaurantService;

import DbPersistence.Dao.RestaurantDao.RestaurantDao;
import DbPersistence.Dao.RestaurantDao.RestaurantDaoImp;
import DbPersistence.Mapper.RestaurantMapper.RestaurantEntityMapper;
import DbPersistence.Mapper.RestaurantMapper.RestaurantMapper;
import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;
import Restaurant.Restaurant;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantServiceImp implements RestaurantService {
    private final RestaurantDao restaurantDao = new RestaurantDaoImp();
    private final RestaurantMapper restaurantMapper = new RestaurantEntityMapper();

    @Override
    public Restaurant create(Restaurant restaurant) {
        return null;
    }

    @Override
    public List<Restaurant> read() {
        List<RestaurantEntity> restaurantEntityList = this.restaurantDao.read();
        return restaurantEntityList.stream().map(restaurantMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Restaurant update(Restaurant restaurant, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Restaurant findById(Integer id) {
        Optional<RestaurantEntity> entityOptional = this.restaurantDao.findById(id);
        if (entityOptional.isEmpty()) {
            throw new RuntimeException("Restaurant with id: " + id + " was not found!");
        }

        RestaurantEntity restaurantEntity = entityOptional.get();
        return restaurantMapper.entityToModel(restaurantEntity);
    }

    @Override
    public List<Restaurant> findAllBy(Predicate<RestaurantEntity> predicate) {
        return this.restaurantDao.findAllBy(predicate).stream().map(restaurantMapper::entityToModel).collect(Collectors.toList());
    }
}
