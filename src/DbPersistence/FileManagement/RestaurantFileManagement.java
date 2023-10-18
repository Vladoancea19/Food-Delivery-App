package DbPersistence.FileManagement;

import java.io.*;

public class RestaurantFileManagement {

    private static BufferedReader bufferedReader;

    private RestaurantFileManagement() throws IOException {
        bufferedReader = new BufferedReader(new FileReader("FilePersistence/restaurant.csv"));
    }

    public static BufferedReader getBufferedReader() throws IOException {
        new RestaurantFileManagement();
        return bufferedReader;
    }
}
