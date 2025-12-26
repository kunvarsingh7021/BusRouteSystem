package db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {

        if (database == null) {
            MongoClient client =
                MongoClients.create("mongodb://localhost:27017");
            database = client.getDatabase("xyz_school_bus");
        }
        return database;
    }
}
