package org.trrusst.reviews.db.software;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * DB Utility class for MongoDB based operaitons
 * Created by lahiru.gallege
 */
public class MongoDBUtil {

    public static void main(String[] args) {
        try {
            // Get the client from the MongoDB
            MongoClient mongo = new MongoClient( "localhost" , 27017 );

            DB db = mongo.getDB("AzSoftware");

            List<String> dbs = mongo.getDatabaseNames();
            for(String dbss : dbs){
                System.out.println(dbss);
            }

            DBCollection table = db.getCollection("user");

            Set<String> tables = db.getCollectionNames();

            for(String coll : tables){
                System.out.println(coll);
            }

            // Insert to a table
            BasicDBObject document = new BasicDBObject();
            document.put("name", "mkyong");
            document.put("age", 30);
            document.put("createdDate", new Date());
            table.insert(document);

            // Find Exmaple
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("name", "mkyong");

            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }

            // Delete example
            table.remove(searchQuery);


        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
