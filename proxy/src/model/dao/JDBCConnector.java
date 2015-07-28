package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCConnector {

	private Connection connection = null;
	
	public Connection getConnection(){
		
		 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager
			.getConnection("jdbc:mysql://localhost:3306/proxyserver","root", "");
			
			if (connection != null) {
				System.out.println("You made it, take control your database now!");
				return connection;
			} else {
				System.out.println("Failed to make connection!");
				return null;
			}
			
	 
		} catch (Exception e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return null;
		}
	 
		
	}
	
	public void close(){
		if(connection!=null){
			try {
				connection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
