package DbPersistence.Service.ProductService;

import DbPersistence.Dao.ProductDao.ProductDao;
import DbPersistence.Dao.ProductDao.ProductDaoImp;
import DbPersistence.Mapper.ProductMapper.ProductEntityMapper;
import DbPersistence.Mapper.ProductMapper.ProductMapper;
import DbPersistence.Entities.ProductEntity.ProductEntity;
import Restaurant.Category.Product.Product;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ProductServiceImp implements ProductService{
    private final ProductDao productDao = new ProductDaoImp();
    private final ProductMapper productMapper = new ProductEntityMapper();

    @Override
    public Product create(Product product) {
        return null;
    }

    @Override
    public List<Product> read() {
        List<ProductEntity> productEntityList = this.productDao.read();
        return productEntityList.stream().map(productMapper::entityToModel).collect(Collectors.toList());

    }

    @Override
    public Product update(Product product, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Product findById(Integer id) {
        return null;
    }

    @Override
    public List<Product> findAllBy(Predicate<ProductEntity> predicate) {
        return null;
    }
}
