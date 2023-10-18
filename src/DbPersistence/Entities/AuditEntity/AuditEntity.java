package DbPersistence.Entities.AuditEntity;

import java.sql.Timestamp;

public class AuditEntity {
    private int auditId;
    private String actionSignature;
    private Timestamp actionTimestamp;

    public AuditEntity(int auditId, String actionSignature, Timestamp actionTimestamp) {
        this.auditId = auditId;
        this.actionSignature = actionSignature;
        this.actionTimestamp = actionTimestamp;
    }

    public AuditEntity() {

    }

    public int getAuditId() {
        return auditId;
    }

    public void setAuditId(int auditId) {
        this.auditId = auditId;
    }

    public String getActionSignature() {
        return actionSignature;
    }

    public void setActionSignature(String actionSignature) {
        this.actionSignature = actionSignature;
    }

    public Timestamp getActionTimestamp() {
        return actionTimestamp;
    }

    public void setActionTimestamp(Timestamp actionTimestamp) {
        this.actionTimestamp = actionTimestamp;
    }
}
