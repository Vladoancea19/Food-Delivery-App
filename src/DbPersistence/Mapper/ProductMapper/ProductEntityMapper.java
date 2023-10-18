package DbPersistence.Mapper.ProductMapper;

import DbPersistence.Entities.ProductEntity.ProductEntity;
import Restaurant.Category.Product.Product;

public class ProductEntityMapper implements ProductMapper{
    @Override
    public Product entityToModel(ProductEntity productEntity) {
        Product product = new Product();
        product.setName(productEntity.getProductName());
        product.setIngredients(productEntity.getProductIngredients());
        product.setPrice(productEntity.getProductsPrice());
        product.setWeight(productEntity.getProductsWeight());
        return product;
    }

    @Override
    public ProductEntity modelToEntity(Product product) {
        return null;
    }
}
