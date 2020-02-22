package ca.mcgill.comp421.iilgenerator.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Consumer;

public final class DatabaseConnector {
	private static Connection connection;

	public static void initilize(String address, String username, String password) {
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
			connection = DriverManager.getConnection(address, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
			close();
		
		}
	}
	
	public static Statement getStatement() {
		try {
			Statement s =  connection.createStatement();
			return s;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void closeStatement(Statement s) {
		try {
			if(s != null && !s.isClosed()) {
				s.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(){ 
		try {
			if(connection != null && !connection.isClosed()) {
					connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
