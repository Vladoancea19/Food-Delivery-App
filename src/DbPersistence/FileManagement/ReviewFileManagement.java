package DbPersistence.FileManagement;

import java.io.*;

public class ReviewFileManagement {

    private static BufferedReader bufferedReader;

    private ReviewFileManagement() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("FilePersistence/review.csv"));
    }

    public static BufferedReader getBufferedReader() throws IOException {
        new ReviewFileManagement();
        return bufferedReader;
    }
}
