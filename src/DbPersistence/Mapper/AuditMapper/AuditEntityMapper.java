package DbPersistence.Mapper.AuditMapper;

import Audit.Audit;
import DbPersistence.Entities.AuditEntity.AuditEntity;

public class AuditEntityMapper implements AuditMapper {
    @Override
    public Audit entityToModel(AuditEntity takeawayOrderEntity) {
        return null;
    }

    @Override
    public AuditEntity modelToEntity(Audit audit) {
        AuditEntity auditEntity = new AuditEntity();
        auditEntity.setActionSignature(audit.getActionSignature());
        auditEntity.setActionTimestamp(audit.getTimestamp());
        return auditEntity;
    }
}
