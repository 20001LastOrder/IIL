package ca.mcgill.comp421.iilgenerator.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DatabaseInserter {
	public static void insertInstitutionsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Institutions VALUES('%s', '%s')", args[0], args[1]));
			return q;
		});
		System.out.println("--------------------------------------------------------------------------------------");
		queries.stream().forEach(a -> System.out.println(a));
	}
	
	public static void insertLibrariesFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Libraries VALUES('%s', '%s', '%s')", args[0], args[1], args[2]));
			return q;
		});
		System.out.println("--------------------------------------------------------------------------------------");
		queries.stream().forEach(a -> System.out.println(a));
	}
	
	public static void insertPatronsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Patrons VALUES('%s', %s, '%s')", args[0], args[4], args[5]));
			return q;
		});
		System.out.println("--------------------------------------------------------------------------------------");
		queries.stream().forEach(a -> System.out.println(a));
	}
	
	public static void insertLibrarianFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Librarians VALUES('%s', '%s', '%s', '%s')", args[0], args[4], args[5], args[6]));
			return q;
		});
		System.out.println("--------------------------------------------------------------------------------------");
		queries.stream().forEach(a -> System.out.println(a));
	}
	
	public static void insertAdministratorsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Administrators VALUES('%s', '%s')", args[0], args[4]));
			return q;
		});
		System.out.println("--------------------------------------------------------------------------------------");
		queries.stream().forEach(a -> System.out.println(a));
	}
	
	
	private void executeInserts(List<String> queries) {
		Statement s = DatabaseConnector.getStatement();
		try {
			for(String query : queries) {
					s.executeUpdate(query);
	
			}	
		}catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DatabaseConnector.closeStatement(s);
		}
	}
	
	private static List<String> parseFileToQuery(String filename, Function<String[], List<String>> converter){
		List<String> queries = new ArrayList<String>();
		File f = new File(filename);
		try {
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String line;
			// the first line is header, discard
			reader.readLine();
			while((line = reader.readLine()) != null) {
				queries.addAll(converter.apply(line.split(";")));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queries;
	}
}
