package DbPersistence.Mapper.CategoryMapper;

import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import Restaurant.Category.Category;

public class CategoryEntityMapper implements CategoryMapper{
    @Override
    public Category entityToModel(CategoryEntity categoryEntity) {
        Category category = new Category();
        category.setName(categoryEntity.getCategoryName());
        category.setProducts(categoryEntity.getCategoryProducts());
        return category;
    }

    @Override
    public CategoryEntity modelToEntity(Category category) {
        return null;
    }
}
