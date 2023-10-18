package DbPersistence.Service.CategoryService;

import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import Restaurant.Category.Category;

import java.util.List;
import java.util.function.Predicate;

public interface CategoryService {
    Category create(Category category);

    List<Category> read();

    Category update(Category category, Integer id);

    String delete(Integer id);

    Category findById(Integer id);

    List<Category> findAllBy(Predicate<CategoryEntity> predicate);
}
