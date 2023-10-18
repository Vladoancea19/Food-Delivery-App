package DbPersistence.Mapper.ProductMapper;

import DbPersistence.Entities.ProductEntity.ProductEntity;
import Restaurant.Category.Product.Product;

public interface ProductMapper {
    Product entityToModel(ProductEntity productEntity);

    ProductEntity modelToEntity(Product product);
}
