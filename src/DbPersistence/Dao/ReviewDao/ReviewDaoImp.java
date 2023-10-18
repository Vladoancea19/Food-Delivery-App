package DbPersistence.Dao.ReviewDao;

import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import DbPersistence.FileManagement.ReviewFileManagement;

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

public class ReviewDaoImp implements ReviewDao {

    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();
    private static final BufferedReader BUFFERED_READER;

    static {
        try {
            BUFFERED_READER = ReviewFileManagement.getBufferedReader();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ReviewEntity create(ReviewEntity reviewEntity) {
        final String SQL = "insert into reviews (review_id, review, restaurant_id) value (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, reviewEntity.getReview());
            preparedStatement.setInt(2, reviewEntity.getRestaurantId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewEntity;
    }

    @Override
    public List<ReviewEntity> read() {
        List<ReviewEntity> reviewEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM reviews;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int reviewId = resultSet.getInt(1);
                    String review = resultSet.getString(2);
                    int restaurantId = resultSet.getInt(3);
                    ReviewEntity reviewEntity = new ReviewEntity(reviewId, review, restaurantId);
                    reviewEntityList.add(reviewEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return reviewEntityList;
    }

//    Citire din fisier CSV
//    @Override
//    public List<ReviewEntity> read() {
//        List<ReviewEntity> reviewEntityList = new ArrayList<>();
//
//        try {
//            BUFFERED_READER.readLine();
//
//            String line;
//            while((line = BUFFERED_READER.readLine()) != null) {
//                String[] column = line.split(",");
//                if(column.length > 0) {
//                    ReviewEntity reviewEntity = new ReviewEntity();
//                    reviewEntity.setReviewId(Integer.parseInt(column[0]));
//                    reviewEntity.setReview(column[1]);
//                    reviewEntity.setRestaurantId(Integer.parseInt(column[2]));
//                    reviewEntityList.add(reviewEntity);
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
//        return reviewEntityList;
//    }

    @Override
    public Optional<ReviewEntity> update(ReviewEntity reviewEntity, Integer id) {
        final String SQL = "UPDATE reviews SET review = ?, restaurant_id = ? WHERE review_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, reviewEntity.getReview());
            preparedStatement.setInt(2, reviewEntity.getRestaurantId());
            preparedStatement.setInt(3, id);
            int n = preparedStatement.executeUpdate();
            if(n > 0) {
                return Optional.of(reviewEntity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        final String SQL = "DELETE FROM reviews WHERE review_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<ReviewEntity> findById(Integer id) {
        final String SQL = "SELECT * FROM reviews WHERE review_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int reviewId = resultSet.getInt(1);
                    String review = resultSet.getString(2);
                    int restaurantId = resultSet.getInt(6);
                    ReviewEntity reviewEntity = new ReviewEntity(reviewId, review, restaurantId);
                    return Optional.of(reviewEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<ReviewEntity> findAllBy(Predicate<ReviewEntity> predicate) {
        List<ReviewEntity> reviewEntityList = read();
        reviewEntityList = reviewEntityList.stream().filter(predicate).collect(Collectors.toList());
        return reviewEntityList;
    }
}
