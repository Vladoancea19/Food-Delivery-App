package DbPersistence.Dao.AuditDao;

import DbPersistence.Crud;
import DbPersistence.Entities.AuditEntity.AuditEntity;

import java.util.List;
import java.util.function.Predicate;

public interface AuditDao extends Crud<AuditEntity, Integer> {
    List<AuditEntity> findAllBy(Predicate<AuditEntity> predicate);
}
