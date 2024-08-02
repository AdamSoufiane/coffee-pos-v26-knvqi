package ai.shreds.infrastructure; 
  
 import com.mongodb.client.MongoClient; 
 import com.mongodb.client.MongoClients; 
 import com.mongodb.client.MongoDatabase; 
 import org.springframework.context.annotation.Bean; 
 import org.springframework.context.annotation.Configuration; 
 import javax.annotation.PreDestroy; 
  
 /** 
  * Configuration class for MongoDB connections. 
  * Provides methods to create and configure MongoClient and MongoDatabase instances. 
  */ 
 @Configuration 
 public class InfrastructureMongoConfig { 
  
     private MongoClient mongoClient; 
  
     /** 
      * Creates a MongoClient instance to connect to MongoDB server. 
      *  
      * @return MongoClient instance 
      */ 
     @Bean 
     public MongoClient mongoClient() { 
         this.mongoClient = MongoClients.create("mongodb://localhost:27017"); 
         return this.mongoClient; 
     } 
  
     /** 
      * Configures the MongoDatabase instance to use the specified database. 
      *  
      * @param mongoClient the MongoClient instance 
      * @return MongoDatabase instance 
      */ 
     @Bean 
     public MongoDatabase mongoDatabase(MongoClient mongoClient) { 
         return mongoClient.getDatabase("inventory_db"); 
     } 
  
     /** 
      * Closes the MongoClient instance to ensure proper resource management. 
      */ 
     @PreDestroy 
     public void closeMongoClient() { 
         if (mongoClient != null) { 
             mongoClient.close(); 
         } 
     } 
 } 
 