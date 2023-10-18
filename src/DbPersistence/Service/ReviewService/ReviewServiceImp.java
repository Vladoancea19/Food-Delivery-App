package DbPersistence.Service.ReviewService;

import DbPersistence.Dao.ReviewDao.ReviewDao;
import DbPersistence.Dao.ReviewDao.ReviewDaoImp;
import DbPersistence.Mapper.ReviewMapper.ReviewEntityMapper;
import DbPersistence.Mapper.ReviewMapper.ReviewMapper;
import DbPersistence.Entities.ReviewEntity.ReviewEntity;
import Restaurant.Review.Review;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ReviewServiceImp implements ReviewService{

    private final ReviewDao reviewDao = new ReviewDaoImp();
    private final ReviewMapper reviewMapper = new ReviewEntityMapper();

    @Override
    public Review create(Review review) {
        return null;
    }

    @Override
    public List<Review> read() {
        List<ReviewEntity> reviewEntityList = this.reviewDao.read();
        return reviewEntityList.stream().map(reviewMapper::entityToModel).collect(Collectors.toList());
    }

    @Override
    public Review update(Review review, Integer id) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        this.reviewDao.delete(id);
        return "Review has been deleted successfully!";
    }

    @Override
    public Review findById(Integer id) {
        Optional<ReviewEntity> entityOptional = this.reviewDao.findById(id);
        if (entityOptional.isEmpty()) {
            throw new RuntimeException("Review with id: " + id + " was not found!");
        }

        ReviewEntity reviewEntity = entityOptional.get();
        return reviewMapper.entityToModel(reviewEntity);
    }

    @Override
    public List<Review> findAllBy(Predicate<ReviewEntity> predicate) {
        return this.reviewDao.findAllBy(predicate).stream().map(reviewMapper::entityToModel).collect(Collectors.toList());
    }
}
