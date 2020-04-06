package ca.mcgill.comp421.iil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

import ca.mcgill.comp421.iil.database.DatabaseConnector;
import ca.mcgill.comp421.ill.model.ResultTable;

public final class IILApplication {
	
	private static IILController controller;
	public static void main(String[] args) {
		initializeDatabase(args[0], args[1], args[2]);
		try {
			controller = getController();
			System.out.println(controller.findBookLocation("Tiger! Tiger!").toString());
			//System.out.println(controller.updateRequest("bradley.kihn01@mail.com", "6-405-464-995-8", false));
			//System.out.println(controller.deleteDeclinedRequests());
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
	
	
	
	public static void performQueriesForVisualization() {
		System.out.println("Library Count...");
		ResultTable table = controller.customerQuery(Arrays.asList("libraryCount", "iname"), "SELECT iname, COUNT(*)AS libraryCount FROM libraries \r\n" + 
				"GROUP BY iname\r\n" + 
				"HAVING COUNT(*)>1;");
		table.toCsv("library.csv");
		
		System.out.println("Borrow Count...");
		table = controller.customerQuery(Arrays.asList("borrowcount", "iname"), "WITH borrowcount AS (SELECT COUNT(*)as borrowrecord, email FROM loans\r\n" + 
				"GROUP BY email),\r\n" + 
				"schoolborrowhistory AS (SELECT iname, COALESCE(borrowrecord, 0) AS borrowhistory FROM borrowcount bc FULL OUTER JOIN patrons p\r\n" + 
				"ON bc.email = p.email) \r\n" + 
				"SELECT SUM(borrowhistory) AS borrowcount, iname FROM schoolborrowhistory sbh\r\n" + 
				"GROUP BY iname;\r\n" + 
				"");
		table.toCsv("borrow.csv");
	}
}
