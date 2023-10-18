package DbPersistence.Service.ProductService;

import DbPersistence.Entities.ProductEntity.ProductEntity;
import Restaurant.Category.Product.Product;

import java.util.List;
import java.util.function.Predicate;

public interface ProductService {
    Product create(Product product);

    List<Product> read();

    Product update(Product product, Integer id);

    String delete(Integer id);

    Product findById(Integer id);

    List<Product> findAllBy(Predicate<ProductEntity> predicate);
}
