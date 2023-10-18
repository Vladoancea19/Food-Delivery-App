package DbPersistence.Dao.CategoryDao;

import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import DbPersistence.Crud;

import java.util.List;
import java.util.function.Predicate;

public interface CategoryDao extends Crud<CategoryEntity, Integer> {
    List<CategoryEntity> findAllBy(Predicate<CategoryEntity> predicate);
}
