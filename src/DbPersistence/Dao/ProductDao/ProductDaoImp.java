package DbPersistence.Dao.ProductDao;

import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.ProductEntity.ProductEntity;
import DbPersistence.FileManagement.ProductFileManagement;

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

public class ProductDaoImp implements ProductDao {

    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();
    private static final BufferedReader BUFFERED_READER;

    static {
        try {
            BUFFERED_READER = ProductFileManagement.getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProductEntity create(ProductEntity entity) {
        return null;
    }

    @Override
    public List<ProductEntity> read() {
        List<ProductEntity> productEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM products;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int productId = resultSet.getInt(1);
                    String productName = resultSet.getString(2);
                    String productIngredients = resultSet.getString(3);
                    float productPrice = resultSet.getFloat(4);
                    int productWeight = resultSet.getInt(5);
                    int categoryId = resultSet.getInt(6);
                    ProductEntity productEntity = new ProductEntity(productId, productName, productIngredients, productPrice, productWeight, categoryId);
                    productEntityList.add(productEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productEntityList;
    }

//    Citire din fisier CSV
//    @Override
//    public List<ProductEntity> read() {
//        List<ProductEntity> productEntityList = new ArrayList<>();
//
//        try {
//            BUFFERED_READER.readLine();
//
//            String line;
//            while((line = BUFFERED_READER.readLine()) != null) {
//                String[] column = line.split(",");
//                if(column.length > 0) {
//                    ProductEntity productEntity = new ProductEntity();
//                    productEntity.setProductId(Integer.parseInt(column[0]));
//                    productEntity.setProductName(column[1]);
//                    productEntity.setProductIngredients(column[2]);
//                    productEntity.setProductsPrice(Float.parseFloat(column[3]));
//                    productEntity.setProductsWeight(Integer.parseInt(column[4]));
//                    productEntity.setCategoryId(Integer.parseInt(column[5]));
//                    productEntityList.add(productEntity);
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
//        return productEntityList;
//    }

    @Override
    public Optional<ProductEntity> update(ProductEntity entity, Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Optional<ProductEntity> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<ProductEntity> findAllBy(Predicate<ProductEntity> predicate) {
        return null;
    }
}
