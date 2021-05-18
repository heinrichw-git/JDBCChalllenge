package de.telekom.jdbcchalllenge;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) throws ClassNotFoundException {
		final String URL = "jdbc:mysql://localhost:3306/seadb?user=seauser&password=seapass"; 
		final String DRIVER = "com.mysql.cj.jdbc.Driver";
		Class.forName(DRIVER);
		try { 
			Connection connection = DriverManager.getConnection(URL); 
			System.out.println("erfolgreich!");
			connection.close(); 
			} 
		catch (Exception e) { 
			System.out.println(e);
		}
	}

}
