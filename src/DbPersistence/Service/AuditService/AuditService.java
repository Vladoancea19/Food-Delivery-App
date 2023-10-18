package DbPersistence.Service.AuditService;

import Audit.Audit;
import DbPersistence.Entities.AuditEntity.AuditEntity;

import java.util.List;
import java.util.function.Predicate;

public interface AuditService {
    Audit create(Audit audit);

    List<Audit> read();

    Audit update(Audit audit, Integer id);

    String delete(Integer id);

    Audit findById(Integer id);

    List<Audit> findAllBy(Predicate<AuditEntity> predicate);
}
