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
			System.out.println("Connect mit der Datenbank erfolgreich!");
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from personen");
			
			// Ausgabe der Anzahl in der Tabelle personen
			while (resultSet.next()) {
				System.out.println("In der Tabelle personen gibt es " + resultSet.getInt(1) + " Einträge.");
			}
			
			// Ausgabe der Tabelle Personen
			resultSet = statement.executeQuery("select * from personen");
			System.out.println("Liste der Tabelle personen:");
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			// Eingabe einer neuen Person 
			Scanner scanner = new Scanner(System.in);
			System.out.println("ID: ");
			long id = Long.parseLong(scanner.nextLine());
			System.out.println("SALUTATION: ");
			short salutation = Short.parseShort(scanner.nextLine()); 
			System.out.println("NAME: ");
			String name = scanner.nextLine(); 
			System.out.println("SURNAME: ");
			String surname = scanner.nextLine(); 
//			scanner.close();
			
			// Import einer Person in die Tabelle personen
			PreparedStatement preparendStatement = connection.prepareStatement("INSERT INTO personen ( ID, SALUTATION, NAME, SURNAME) VALUES (?, ?, ?, ?)");
			preparendStatement.setLong(1, id);
			preparendStatement.setShort(2, salutation);
			preparendStatement.setString(3, name);
			preparendStatement.setString(4, surname);
			preparendStatement.execute();
			
			// Ausgabe der Tabelle personen
			resultSet = statement.executeQuery("select * from personen");
			System.out.println("Liste der Tabelle personen:");
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			// Änderung eines Vornamens in der Tabelle personen
			preparendStatement = connection.prepareStatement("UPDATE personen set NAME='Max' where ID=4");
//			resultSet = statement.executeQuery("UPDATE personen set NAME='Maxi' where ID=4");
			
			// Ausgabe der Tabelle personen
			resultSet = statement.executeQuery("select * from personen");
			System.out.println("Liste der Tabelle personen:");
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			// Eingabe der ID für eine Änderung des Nachnamen in der Tabelle personen
			System.out.println("Änderungseingabe:");
//			Scanner scanner = new Scanner(System.in);
			System.out.println("ID: ");
			id = Long.parseLong(scanner.nextLine());
			System.out.println("New SURNAME: ");
			surname = scanner.nextLine(); 
			preparendStatement = connection.prepareStatement("UPDATE personen set SURNAME='" + surname + "' where ID=" + id);
//			resultSet = statement.executeQuery("UPDATE personen set SURNAME='" + surname + "' where ID=" + id);
			
			scanner.close();
			
			// Ausgabe der Tabelle personen
			resultSet = statement.executeQuery("select * from personen");
			System.out.println("Liste der Tabelle personen:");
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			// Löschen von 2 IDs aus der Tabelle Personen
			preparendStatement = connection.prepareStatement("DELETE from personen where ID=7");
//			preparendStatement = connection.prepareStatement("DELETE from personen where ID=7");
//			resultSet = statement.executeQuery("DELETE from personen where ID=7");
//			resultSet = statement.executeQuery("DELETE from personen where ID=7");
			
			// Ausgabe der Tabelle personen
			resultSet = statement.executeQuery("select * from personen");
			System.out.println("Liste der Tabelle personen:");
			while (resultSet.next()) {
				System.out.println("ID          : " + resultSet.getInt(1)); // ID
				System.out.println("SALUTATION  : " + resultSet.getInt(2)); // Anrede
				System.out.println("NAME        : " + resultSet.getString(3)); // Nachname
				System.out.println("SURNAME     : " + resultSet.getString(4)); // Vorname
				System.out.println("------------");
			}
			
			resultSet.close();
			statement.close(); 
			connection.close(); 
			} 
		catch (Exception e) { 
			System.out.println(e);
		}
	}

}
