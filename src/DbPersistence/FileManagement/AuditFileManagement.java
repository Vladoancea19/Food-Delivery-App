package DbPersistence.FileManagement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AuditFileManagement {

    private static File file;
    private static FileWriter fileWriter;

    private AuditFileManagement() throws IOException {
        file = new File("FilePersistence/audit.csv");
        fileWriter = new FileWriter(file, true);
    }

    public static File getFile() throws IOException {
        new AuditFileManagement();
        return file;
    }

    public static FileWriter getFileWriter() {
        return fileWriter;
    }
}
