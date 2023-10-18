package DbPersistence.Dao.RestaurantDao;

import DbPersistence.Dao.CategoryDao.CategoryDaoImp;
import DbPersistence.Dao.ReviewDao.ReviewDaoImp;
import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.CategoryEntity.CategoryEntity;
import DbPersistence.Entities.RestaurantEntity.RestaurantEntity;
import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import DbPersistence.FileManagement.RestaurantFileManagement;
import Restaurant.Category.Category;
import Restaurant.Review.Review;

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
import java.util.stream.Collectors;

public class RestaurantDaoImp implements RestaurantDao {

    private final List<CategoryEntity> categoryEntities = new CategoryDaoImp().read();
    private final List<ReviewEntity> reviewEntities = new ReviewDaoImp().read();
    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();
    private static final BufferedReader BUFFERED_READER;

    static {
        try {
            BUFFERED_READER = RestaurantFileManagement.getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public RestaurantEntity create(RestaurantEntity entity) {
        return null;
    }

    @Override
    public List<RestaurantEntity> read() {
        List<RestaurantEntity> restaurantEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM restaurants;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int restaurantId = resultSet.getInt(1);
                    String restaurantName = resultSet.getString(2);
                    List<Review> restaurantReviews = new ArrayList<>();
                    for(ReviewEntity review : reviewEntities) {
                        if(review.getRestaurantId() == restaurantId) {
                            Review neededReview = new Review(review.getReview());
                            restaurantReviews.add(neededReview);
                        }
                    }
                    int restaurantScore = resultSet.getInt(3);
                    List<Category> restaurantCategories = new ArrayList<>();
                    for(CategoryEntity category : categoryEntities) {
                        if(category.getRestaurantId() == restaurantId) {
                            Category neededCategory = new Category(category.getCategoryName(), category.getCategoryProducts());
                            restaurantCategories.add(neededCategory);
                        }
                    }

                    RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, restaurantReviews, restaurantScore, restaurantCategories);
                    restaurantEntityList.add(restaurantEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return restaurantEntityList;
    }

//    Citire din fisier CSV
//    @Override
//    public List<RestaurantEntity> read() {
//        List<RestaurantEntity> restaurantEntityList = new ArrayList<>();
//
//        try {
//            BUFFERED_READER.readLine();
//
//            String line;
//            while((line = BUFFERED_READER.readLine()) != null) {
//                String[] column = line.split(",");
//                if(column.length > 0) {
//                    RestaurantEntity restaurantEntity = new RestaurantEntity();
//                    restaurantEntity.setRestaurantId(Integer.parseInt(column[0]));
//                    restaurantEntity.setRestaurantName(column[1]);
//                    restaurantEntity.setRestaurantScore(Integer.parseInt(column[2]));
//                    List<Review> restaurantReviews = new ArrayList<>();
//                    for(ReviewEntity reviewEntity : reviewEntities) {
//                        if(reviewEntity.getRestaurantId() == restaurantEntity.getRestaurantId()) {
//                            Review neededReview = new Review(reviewEntity.getReview());
//                            restaurantReviews.add(neededReview);
//                        }
//                    }
//                    restaurantEntity.setRestaurantReviews(restaurantReviews);
//                    List<Category> restaurantCategories = new ArrayList<>();
//                    for(CategoryEntity categoryEntity : categoryEntities) {
//                        if(categoryEntity.getCategoryId() == categoryEntity.getCategoryId()) {
//                            Category neededCategories = new Category(categoryEntity.getCategoryName(), categoryEntity.getCategoryProducts());
//                            restaurantCategories.add(neededCategories);
//                        }
//                    }
//                    restaurantEntity.setRestaurantCategories(restaurantCategories);
//                    restaurantEntityList.add(restaurantEntity);
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
//        return restaurantEntityList;
//    }

    @Override
    public Optional<RestaurantEntity> update(RestaurantEntity entity, Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Optional<RestaurantEntity> findById(Integer id) {
        final String SQL = "SELECT * FROM restaurants WHERE restaurant_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int restaurantId = resultSet.getInt(1);
                    String restaurantName = resultSet.getString(2);
                    List<Review> restaurantReviews = new ArrayList<>();
                    for(ReviewEntity review : reviewEntities) {
                        if(review.getRestaurantId() == restaurantId) {
                            Review neededReview = new Review(review.getReview());
                            restaurantReviews.add(neededReview);
                        }
                    }
                    int restaurantScore = resultSet.getInt(3);
                    List<Category> categories = new ArrayList<>();
                    for(CategoryEntity category : categoryEntities) {
                        if(category.getRestaurantId() == restaurantId) {
                            Category neededCategory = new Category(category.getCategoryName(), category.getCategoryProducts());
                            categories.add(neededCategory);
                        }
                    }

                    RestaurantEntity restaurantEntity = new RestaurantEntity(restaurantId, restaurantName, restaurantReviews, restaurantScore, categories);
                    return Optional.of(restaurantEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<RestaurantEntity> findAllBy(Predicate<RestaurantEntity> predicate) {
        List<RestaurantEntity> restaurantEntityList = read();
        restaurantEntityList = restaurantEntityList.stream().filter(predicate).collect(Collectors.toList());
        return restaurantEntityList;
    }
}
