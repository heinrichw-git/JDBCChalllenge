package de.telekom.jdbcchalllenge;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	
	java.util.Scanner scanner = new java.util.Scanner(System.in);
		
	final static String URL = "jdbc:mysql://localhost:3306/seadb?user=seauser&password=seapass"; 
	final static String DRIVER = "com.mysql.cj.jdbc.Driver";
		
	public static void main(String[] args) throws ClassNotFoundException {
		
		Class.forName(DRIVER);
		try { 
			Connection connection = DriverManager.getConnection(URL); 
			System.out.println("erfolgreich!");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from personen");
			
			while (resultSet.next()) {
				System.out.println("In der Tabelle personen gibt es " + resultSet.getInt(1) + " Einträge.");
			}
			
			resultSet = statement.executeQuery("select * from personen");
			
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID: ");
			long id = Long.parseLong(scanner.nextLine());
			System.out.println("SALUTATION: ");
			short salutation = Short.parseShort(scanner.nextLine()); 
			System.out.println("NAME: ");
			String name = scanner.nextLine(); 
			System.out.println("SURNAME: ");
			String surname = scanner.nextLine(); 
			scanner.close();
			
			PreparedStatement preparendStatement = connection.prepareStatement("INSERT INTO personen ( ID, SALUTATION, NAME, SURNAME) VALUES (?, ?, ?, ?)");
			preparendStatement.setLong(1, id);
			preparendStatement.setShort(2, salutation);
			preparendStatement.setString(3, name);
			preparendStatement.setString(4, surname);
			preparendStatement.execute();
			
			
			resultSet.close();
			statement.close(); 
			connection.close(); 
			} 
		catch (Exception e) { 
			System.out.println(e);
		}
	}

}
