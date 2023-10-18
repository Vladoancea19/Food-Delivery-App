package DbPersistence.Dao.CategoryDao;

import DbPersistence.Dao.ProductDao.ProductDaoImp;
import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import DbPersistence.Entities.ProductEntity.ProductEntity;
import DbPersistence.FileManagement.CategoryFileManagement;
import Restaurant.Category.Product.Product;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class CategoryDaoImp implements CategoryDao{
    private final List<ProductEntity> productEntities = new ProductDaoImp().read();
    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();
    private static final BufferedReader BUFFERED_READER;

    static {
        try {
            BUFFERED_READER = CategoryFileManagement.getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        return null;
    }

    @Override
    public List<CategoryEntity> read() {
        List<CategoryEntity> categoryEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM categories;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int categoryId = resultSet.getInt(1);
                    String categoryName = resultSet.getString(2);
                    List<Product> categoryProducts = new ArrayList<>();
                    for(ProductEntity productEntity : productEntities) {
                        if(productEntity.getCategoryId() == categoryId) {
                            Product neededProduct = new Product(productEntity.getProductName(), productEntity.getProductIngredients(), productEntity.getProductsPrice(), productEntity.getProductsWeight());
                            categoryProducts.add(neededProduct);
                        }
                    }
                    int restaurantId = resultSet.getInt(3);

                    CategoryEntity categoryEntity = new CategoryEntity(categoryId, categoryName, categoryProducts, restaurantId);
                    categoryEntityList.add(categoryEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryEntityList;
    }

//    Citire din fisier CSV
//    @Override
//    public List<CategoryEntity> read() {
//        List<CategoryEntity> categoryEntityList = new ArrayList<>();
//
//        try {
//            BUFFERED_READER.readLine();
//
//            String line;
//            while((line = BUFFERED_READER.readLine()) != null) {
//                String[] column = line.split(",");
//                if(column.length > 0) {
//                    CategoryEntity categoryEntity = new CategoryEntity();
//                    categoryEntity.setCategoryId(Integer.parseInt(column[0]));
//                    categoryEntity.setCategoryName(column[1]);
//                    List<Product> categoryProducts = new ArrayList<>();
//                    for(ProductEntity productEntity : productEntities) {
//                        if(productEntity.getCategoryId() == categoryEntity.getCategoryId()) {
//                            Product neededProduct = new Product(productEntity.getProductName(), productEntity.getProductIngredients(), productEntity.getProductsPrice(), productEntity.getProductsWeight());
//                            categoryProducts.add(neededProduct);
//                        }
//                    }
//                    categoryEntity.setCategoryProducts(categoryProducts);
//                    categoryEntity.setRestaurantId(Integer.parseInt(column[2]));
//                    categoryEntityList.add(categoryEntity);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                assert BUFFERED_READER != null;
//                BUFFERED_READER.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return categoryEntityList;
//    }

    @Override
    public Optional<CategoryEntity> update(CategoryEntity categoryEntity, Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Optional<CategoryEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<CategoryEntity> findAllBy(Predicate<CategoryEntity> predicate) {
        return null;
    }
}
