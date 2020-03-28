package ca.mcgill.comp421.iil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.mcgill.comp421.iil.database.DatabaseConnector;
import ca.mcgill.comp421.ill.model.ResultTable;

public final class IILApplication {
	
	private static IILController controller;
	public static void main(String[] args) {
		initializeDatabase(args[0], args[1], args[2]);
		try {
			getController();
			ResultTable result = controller.getAllLoansForPatron("trenton.kautzer84@mail.com");
			System.out.println(result);
		}finally {
			DatabaseConnector.close();
		}


	}
	
	public static void initializeDatabase(String address, String username, String password) {
		try {
			DatabaseConnector.initilize(address, username, password);
		}catch(Exception e) {
			DatabaseConnector.close();
		}
	}
	
	public static IILController getController() {
		if(controller == null) {
			controller = new IILController();
		}
		
		return controller;
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
