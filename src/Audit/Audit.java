package Audit;

import java.sql.Timestamp;
import java.util.Objects;

public class Audit {
    private String actionSignature;
    private Timestamp timestamp;

    public Audit(String actionSignature, Timestamp timestamp) {
        this.actionSignature = actionSignature;
        this.timestamp = timestamp;
    }

    public String getActionSignature() {
        return actionSignature;
    }

    public void setActionSignature(String actionSignature) {
        this.actionSignature = actionSignature;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Audit{" +
                "actionSignature='" + actionSignature + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audit)) return false;
        Audit audit = (Audit) o;
        return actionSignature.equals(audit.actionSignature) && timestamp.equals(audit.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(actionSignature, timestamp);
    }
}
