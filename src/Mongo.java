/*  * Project: Lab3
    * Purpose Details: Mongo and MySQL implementation
    * Course: IST 242
    * Author: Anthony Chirichella
    * Date Developed: 2/27/26
    * Last Date Changed: 3/1/26
    * Rev: 1
 */

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class Mongo {
    public static void main(String[] args) {

        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017")) {

            // Use cleaner database name
            MongoDatabase database = mongoClient.getDatabase("ClothingStore");
            MongoCollection<Document> collection = database.getCollection("Customers");
            collection.deleteMany(new Document());

            // Insert
            Document customer1 = new Document("first_name", "John")
                    .append("last_name", "Doe")
                    .append("loyalty_points", 200)
                    .append("email", "john@example.com");

            Document customer2 = new Document("first_name", "Anthony")
                    .append("last_name", "Chirichella")
                    .append("loyalty_points", 200)
                    .append("email", "anthony@example.com");

            Document customer3 = new Document("first_name", "Joe")
                    .append("last_name", "Oakes")
                    .append("loyalty_points", 600)
                    .append("email", "JoeOakes@example.com");


            collection.insertOne(customer1);
            collection.insertOne(customer2);
            collection.insertOne(customer3);


            // Read
            System.out.println("=== Initial Data ===");
            for (Document customer : collection.find()) {
                System.out.println(customer.toJson());
            }

            // Update John
            collection.updateOne(eq("first_name", "Joe"),
                    set("first_name", "Updated First Name"));

            // Read again
            System.out.println("After Update");
            for (Document customer : collection.find()) {
                System.out.println(customer.toJson());
            }

            // Delete updated record
            collection.deleteOne(eq("first_name", "Updated First Name"));

            System.out.println("After Delete");
            for (Document customer : collection.find()) {
                System.out.println(customer.toJson());
            }
        }
    }
}