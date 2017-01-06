package em;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import utilitaire.UtilDB;
public class Em{
	protected String []colonne;
	protected String []valeur;
	
	public Em(String []tab ,String []val){
		setColonne(tab);
		setValue(val);
	}
	public Em(){
		
	}
	void setColonne(String []tab){
		colonne=tab;
	}
	public String []getColonne(){
		return colonne;
	}
	
	void setValue(String []val){
		valeur=val;
	}
	
	public void setValue(String val,int ind){
		valeur[ind]=val;
	}
	public String[] getValue(){
		return valeur;
	}
	
	public String getValue(int i){
		return valeur[i];
	}
	
	
	
	public void update(Em em,String tab,String valwhere,Connection con) throws Exception{
		
		con.setAutoCommit(false);
		
		java.sql.Statement stmt = con.createStatement();
		
		String req=new String("UPDATE "+tab+" SET ");

		String toconcat="";
		for(int i=1,j=0;i<em.colonne.length;i++,j++){
			toconcat=new String(em.colonne[i]+"='"+em.valeur[j]+"'");
			
			if(i<em.colonne.length-1)
				toconcat+= ",";
			
			req=req.concat(toconcat);
		}
	
		req=req.concat(" WHERE "+em.colonne[0]+"='"+valwhere+"'");
		System.out.println("requete="+req);
		ResultSet res = stmt.executeQuery(req);
//		con.commit();
//		con.close();
	}
	public static void insert(String tab,String []values,Connection con) throws Exception{
		
		con.setAutoCommit(false);
		
		java.sql.Statement stmt = con.createStatement();
		
		String req=new String("INSERT INTO "+tab+" VALUES (");
		
		String val=new String(""+values[0]+"");
		
		for(int i=1;i<values.length;i++){
			val=val.concat(","+values[i]+"");
		}
		
		req=req.concat(val);
		req=req.concat(")");
		System.out.println(req);
		ResultSet res = stmt.executeQuery(req);
		stmt.close();
//		con.commit();
//		con.close();
	}
	
	public static void insertToAccess(String tab,String []colonne,String []values,Connection con) throws Exception{

		con.setAutoCommit(false);
		
		java.sql.Statement stmt = con.createStatement();
		
        String req=new String("INSERT INTO "+tab+" (");
		
		int t=colonne.length;
		for(int i=0;i<t;i++){
			req=req.concat(colonne[i]);
			if(i==t-1){
				break;
			}
			req=req+",";
		}
		req=req.concat(") VALUES('");
		for(int i=0;i<t;i++){
			req=req.concat(values[i])+"'";
			if(i==t-1){
				break;
			}
			req=req+",'";
		}
		req=req+")";
		System.out.println(req);
        
        stmt.executeUpdate(req);
//        System.out.println("Row is added");
		
//		con.commit();
		con.close();
	}
		
	public static String selectMongo(String collection) throws Exception{
		
		ArrayList<String> ret=new ArrayList<String>();
		MongoClient connMongo=UtilDB.getConnMongoDB();
		MongoDatabase database = connMongo.getDatabase("openclassrooms");
//		
		MongoCollection<Document> col = database.getCollection(collection);
//		
		Document myDoc = col.find().first();
		
//		MongoCursor<Document> cursor = database.getCollection(collections).find().iterator();
		
		//Document myDoc = database.getCollection(collections).find().first();
		
		
		
//		try {
//			
//		    while (cursor.hasNext()) {
//		        System.out.println(cursor.next().toJson());
////		        ret.add(cursor.next().toJson());
//		    }
//		} finally {
//		    cursor.close();
//		}
		
		return myDoc.toJson();
	}
	
}
