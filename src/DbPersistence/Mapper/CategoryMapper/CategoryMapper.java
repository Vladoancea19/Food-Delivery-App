package DbPersistence.Mapper.CategoryMapper;

import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import Restaurant.Category.Category;

public interface CategoryMapper {
    Category entityToModel(CategoryEntity categoryEntity);

    CategoryEntity modelToEntity(Category category);
}
