package DbPersistence.FileManagement;

import java.io.*;

public class ProductFileManagement {

    private static BufferedReader bufferedReader;

    private ProductFileManagement() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("FilePersistence/product.csv"));
    }

    public static BufferedReader getBufferedReader() throws IOException {
        new ProductFileManagement();
        return bufferedReader;
    }
}
