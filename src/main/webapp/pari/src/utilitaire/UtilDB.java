package utilitaire;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;



public class UtilDB
{
	
	
	public static MongoClient getConnMongoDB() throws Exception
	{
		MongoClient mongoClient;
		try{
			
	         // To connect to mongodb server
			 MongoClientURI connectionString = new MongoClientURI("mongodb://localhost:27017");
			 mongoClient = new MongoClient(connectionString);
			
	         // Now connect to your databases
	         MongoDatabase database = mongoClient.getDatabase("openclassrooms");
//	         DB db = mongoClient.getDB( "test" );
	         System.out.println("Connect to database successfully");
//	         boolean auth = db.authenticate(myUserName, myPassword);
//	         System.out.println("Authentication: "+auth);
				
	      }catch(Exception e){
	    	  throw new Exception(e.getClass().getName() + ": " + e.getMessage());
//	         System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      }
	  
		return mongoClient;
	}
	
}
