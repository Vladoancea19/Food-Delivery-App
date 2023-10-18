package DbPersistence.Dao.AuditDao;

import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.AuditEntity.AuditEntity;
import DbPersistence.FileManagement.AuditFileManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class AuditDaoImp implements AuditDao {

    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();
    private static final File AUDIT_FILE;

    static {
        try {
            AUDIT_FILE = AuditFileManagement.getFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static final FileWriter AUDIT_FILE_WRITER = AuditFileManagement.getFileWriter();

    @Override
    public AuditEntity create(AuditEntity auditEntity) {
        final String SQL = "insert into audit (audit_id, action_signature, action_timestamp) value (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, auditEntity.getAuditId());
            preparedStatement.setString(2, auditEntity.getActionSignature());
            preparedStatement.setTimestamp(3, auditEntity.getActionTimestamp());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return auditEntity;
    }

//    Scriere fisier CSV
//    @Override
//    public AuditEntity create(AuditEntity auditEntity) {
//        try {
//            if(AUDIT_FILE.length() == 0) {
//                AUDIT_FILE_WRITER.append("actionSignature, actionTimestamp\n");
//                AUDIT_FILE_WRITER.append(auditEntity.getActionSignature());
//                AUDIT_FILE_WRITER.append(",");
//                AUDIT_FILE_WRITER.append(auditEntity.getActionTimestamp().toString());
//                AUDIT_FILE_WRITER.append("\n");
//            }
//            else {
//                AUDIT_FILE_WRITER.append(auditEntity.getActionSignature());
//                AUDIT_FILE_WRITER.append(",");
//                AUDIT_FILE_WRITER.append(auditEntity.getActionTimestamp().toString());
//                AUDIT_FILE_WRITER.append("\n");
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                assert AUDIT_FILE_WRITER != null;
//                AUDIT_FILE_WRITER.flush();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return auditEntity;
//    }

    @Override
    public List<AuditEntity> read() {
        return null;
    }

    @Override
    public Optional<AuditEntity> update(AuditEntity entity, Integer integer) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer integer) {
    }

    @Override
    public Optional<AuditEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<AuditEntity> findAllBy(Predicate<AuditEntity> predicate) {
        return null;
    }
}
