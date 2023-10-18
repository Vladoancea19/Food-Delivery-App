package DbPersistence.Mapper.ReviewMapper;

import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import Restaurant.Review.Review;

public interface ReviewMapper {
    Review entityToModel(ReviewEntity reviewEntity);

    ReviewEntity modelToEntity(Review review);
}
