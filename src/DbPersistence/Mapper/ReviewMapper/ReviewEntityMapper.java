package DbPersistence.Mapper.ReviewMapper;

import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import Restaurant.Review.Review;

public class ReviewEntityMapper implements ReviewMapper{

    @Override
    public Review entityToModel(ReviewEntity reviewEntity) {
        Review review = new Review();
        review.setReview(reviewEntity.getReview());
        return review;
    }

    @Override
    public ReviewEntity modelToEntity(Review review) {
        return null;
    }
}
