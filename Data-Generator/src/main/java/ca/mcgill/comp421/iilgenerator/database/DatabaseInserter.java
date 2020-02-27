package ca.mcgill.comp421.iilgenerator.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Date;
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
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertLibrariesFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Libraries VALUES('%s', '%s', '%s')", args[0], args[1], args[2]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertPatronsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Patrons VALUES('%s', %s, '%s')", args[0], args[4], args[5]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertLibrarianFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Librarians VALUES('%s', '%s', '%s', '%s')", args[0], args[4], args[5], args[6]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertAdministratorsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Users VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			q.add(String.format("INSERT INTO Administrators VALUES('%s', '%s')", args[0], args[4]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertBooksFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Books VALUES('%s', '%s', '%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3], args[4], args[5 ]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertAuthorsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Authors VALUES(%s, '%s')", args[0], args[1]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertBookCopiesFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO BookCopies VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertLoansFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			Date defaultDate = new Date(0);
			if(args[3].equals(defaultDate.toString())) {
				// no actual return date
				q.add(String.format("INSERT INTO Loans (loanId, startDate, requiredReturnDate, fine, hasRequest, email, barCode)"
						+ " VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s')",
						  args[0], args[1], args[2], args[4], args[5], args[6], args[7]));
			}else {
				q.add(String.format("INSERT INTO Loans VALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
						  args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7]));
			}
			
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertWritesFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Writes VALUES(%s, '%s')", args[0], args[1]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	public static void insertRequestsFromFile(String filename) {
		List<String> queries = parseFileToQuery(filename, (args)->{
			List<String> q = new ArrayList<String>();
			q.add(String.format("INSERT INTO Requests VALUES('%s', '%s', '%s', '%s')", args[0], args[1], args[2], args[3]));
			return q;
		});
//		System.out.println("--------------------------------------------------------------------------------------");
//		queries.stream().forEach(a -> System.out.println(a));
		executeInserts(queries);
	}
	
	private static void executeInserts(List<String> queries) {
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
				// replace quotes to double quotes
				line = line.replace("'", "''");
				queries.addAll(converter.apply(line.split(";")));
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return queries;
	}
}
