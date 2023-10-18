package DbPersistence.Dao.ReviewDao;

import DbPersistence.Crud;
import DbPersistence.Entities.ReviewEntity.ReviewEntity;

import java.util.List;
import java.util.function.Predicate;

public interface ReviewDao extends Crud<ReviewEntity, Integer> {
    List<ReviewEntity> findAllBy(Predicate<ReviewEntity> predicate);
}
