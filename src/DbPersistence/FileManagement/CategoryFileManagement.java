package DbPersistence.FileManagement;

import java.io.*;

public class CategoryFileManagement {

    private static BufferedReader bufferedReader;

    private CategoryFileManagement() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("FilePersistence/category.csv"));
    }

    public static BufferedReader getBufferedReader() throws IOException {
        new CategoryFileManagement();
        return bufferedReader;
    }
}
