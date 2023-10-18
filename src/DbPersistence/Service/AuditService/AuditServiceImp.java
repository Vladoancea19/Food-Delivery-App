package DbPersistence.Service.AuditService;

import Audit.Audit;
import DbPersistence.Dao.AuditDao.AuditDao;
import DbPersistence.Dao.AuditDao.AuditDaoImp;
import DbPersistence.Entities.AuditEntity.AuditEntity;
import DbPersistence.Mapper.AuditMapper.AuditEntityMapper;
import DbPersistence.Mapper.AuditMapper.AuditMapper;

import java.util.List;
import java.util.function.Predicate;

public class AuditServiceImp implements AuditService{

    private final AuditDao auditDao = new AuditDaoImp();
    private final AuditMapper auditMapper = new AuditEntityMapper();

    @Override
    public Audit create(Audit audit) {
        AuditEntity auditEntity = auditMapper.modelToEntity(audit);
        AuditEntity savedAuditEntity = this.auditDao.create(auditEntity);
        return audit;
    }

    @Override
    public List<Audit> read() {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }

    @Override
    public Audit findById(Integer id) {
        return null;
    }

    @Override
    public List<Audit> findAllBy(Predicate<AuditEntity> predicate) {
        return null;
    }

    @Override
    public Audit update(Audit audit, Integer id) {
        return null;
    }
}
