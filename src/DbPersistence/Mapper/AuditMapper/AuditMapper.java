package DbPersistence.Mapper.AuditMapper;

import Audit.Audit;
import DbPersistence.Entities.AuditEntity.AuditEntity;

public interface AuditMapper {
    Audit entityToModel(AuditEntity takeawayOrderEntity);

    AuditEntity modelToEntity(Audit audit);
}
