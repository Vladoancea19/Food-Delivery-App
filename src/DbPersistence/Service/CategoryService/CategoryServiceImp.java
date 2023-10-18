package DbPersistence.Service.CategoryService;

import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import DbPersistence.Dao.CategoryDao.CategoryDao;
import DbPersistence.Dao.CategoryDao.CategoryDaoImp;
import DbPersistence.Mapper.CategoryMapper.CategoryEntityMapper;
import DbPersistence.Mapper.CategoryMapper.CategoryMapper;
import Restaurant.Category.Category;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CategoryServiceImp implements CategoryService {
    private final CategoryDao categoryDao = new CategoryDaoImp();
    private final CategoryMapper categoryMapper = new CategoryEntityMapper();


    @Override
    public Category create(Category category) {
        return null;
    }

    @Override
    public List<Category> read() {
        List<CategoryEntity> categoryEntityList = this.categoryDao.read();
        return categoryEntityList.stream().map(categoryMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Category update(Category category, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Category findById(Integer id) {
        return null;
    }

    @Override
    public List<Category> findAllBy(Predicate<CategoryEntity> predicate) {
        return null;
    }
}

