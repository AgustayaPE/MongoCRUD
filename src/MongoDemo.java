import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.BulkWriteOptions;
import com.mongodb.client.model.InsertOneModel;

import org.bson.Document;

public class MongoDemo {
    public static void main(String[] args)throws Exception {/*
        try{
            FileWriter myWriter=new FileWriter("/Users/agustayachaturvedi/Documents/SimpleParser/MongoCRUD/MondoCRUD/lib/MongoDemotarget.json");
            MongoClient mongoClient=new MongoClient("localhost",27017);
            DB db=mongoClient.getDB("test");
            System.out.println("Connection is successfull");
            DBCollection mongoCollection=db.getCollection("inventory");
            System.out.println("Collection found");
            DBCursor cursor=mongoCollection.find();
            while(cursor.hasNext()){
                myWriter.write(cursor.next()+" \n");
                //System.out.println(cursor.next());
            }
            myWriter.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("Data successfully tranferred MongoDB ----------> JSON");
*/
        MongoClient client=new MongoClient("localhost",27017);
        MongoDatabase database=client.getDatabase("test");
        MongoCollection<Document> collection=database.getCollection("inventory2");

        int count=0;
        int batch=100;

        List<InsertOneModel<Document>> docs=new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader("/Users/agustayachaturvedi/Documents/SimpleParser/MongoCRUD/MondoCRUD/lib/MongoDemotarget.json"))){
            String line;
            while((line=br.readLine())!=null){
                docs.add(new InsertOneModel<>(Document.parse(line)));
                count++;
                if(count==batch){
                    collection.bulkWrite(docs,new BulkWriteOptions().ordered(false));
                    docs.clear();
                    count=0;
                }
            }
        }

        

    }


}
