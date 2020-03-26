package ca.mcgill.comp421.iilgenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.mcgill.comp421.iilgenerator.database.DatabaseConnector;

public class Main {
	public static void main(String[] args) {
		//generation();
		postToDatabase(args);
	}
	
	public static void postToDatabase(String args[]) {
		try {
			DatabaseConnector.initilize(args[0], args[1], args[2]);
		}finally {
			DatabaseConnector.close();
		}
		System.out.println("Generation Finished");
	}
	
	public static void queryInstitutions() {
		Statement s = DatabaseConnector.getStatement();
		try {
			ResultSet results = s.executeQuery("SELECT * FROM Institutions");
			while(results.next()) {
				String iName = results.getString("iName");
				String iAddress = results.getString("iAddress");
				System.out.println("name: " + iName + ", Address: " + iAddress);
			}
			results.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConnector.closeStatement(s);
		}
	}
}
