package DbPersistence.Dao.ProductDao;

import DbPersistence.Crud;
import DbPersistence.Entities.ProductEntity.ProductEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ProductDao extends Crud<ProductEntity, Integer> {

    List<ProductEntity> findAllBy(Predicate<ProductEntity> predicate);
}
