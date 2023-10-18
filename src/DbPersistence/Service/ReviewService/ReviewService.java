package DbPersistence.Service.ReviewService;

import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import Restaurant.Review.Review;

import java.util.List;
import java.util.function.Predicate;

public interface ReviewService {

    Review create(Review review);

    List<Review> read();

    Review update(Review review, Integer id);

    String delete(Integer id);

    Review findById(Integer id);

    List<Review> findAllBy(Predicate<ReviewEntity> predicate);
}
