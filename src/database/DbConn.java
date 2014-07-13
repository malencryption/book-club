package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConn {
	String DB_CONNECTION;
	
	// Database credentials
	String USER;
	String PASS;
	Connection conn = null;
				
	public DbConn() {
		super();
	}

	public Connection connect(){
		String host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
		  System.out.println("host: " + host);
		  if (host == null){
		  	//Not in the openshift environment
		  	// echo "Using local credentials: ";
			  DB_CONNECTION = "jdbc:mysql://localhost/book_club";
			  USER = "leesaruz_iClient";
			  PASS = "Jd59wMUo";
		  }
		  else {
		      	//In the openshift environment
		      	// echo "Using openshift credentials: ";

		   String port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
		   USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
		   PASS = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		   
		   DB_CONNECTION = String.format("jdbc:mysql://%s:%s/book_club", host, port);
		 }
		
		try {
			
			conn = DriverManager.getConnection(DB_CONNECTION, USER,
					PASS);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
}
