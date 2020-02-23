package ca.mcgill.comp421.iilgenerator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.mcgill.comp421.iilgenerator.database.DatabaseConnector;
import ca.mcgill.comp421.iilgenerator.database.DatabaseInserter;
import ca.mcgill.comp421.iilgenerator.generators.AdministratorGenerator;
import ca.mcgill.comp421.iilgenerator.generators.AuthorGenerator;
import ca.mcgill.comp421.iilgenerator.generators.BookCopyGenerator;
import ca.mcgill.comp421.iilgenerator.generators.BookGenerator;
import ca.mcgill.comp421.iilgenerator.generators.InstitutionGenerator;
import ca.mcgill.comp421.iilgenerator.generators.LibrarianGenerator;
import ca.mcgill.comp421.iilgenerator.generators.LibraryGenerator;
import ca.mcgill.comp421.iilgenerator.generators.LoanGenerator;
import ca.mcgill.comp421.iilgenerator.generators.PatronGenerator;
import ca.mcgill.comp421.iilgenerator.generators.RequestGenerator;
import ca.mcgill.comp421.iilgenerator.generators.Utils;
import ca.mcgill.comp421.iilgenerator.generators.WriteGenerator;

public class Main {
	public static void main(String[] args) {
		try {
			DatabaseConnector.initilize(args[0], args[1], args[2]);
			System.out.println("Inserting Institutions...");
			DatabaseInserter.insertInstitutionsFromFile("institutions.csv");
			System.out.println("Inserting libraries...");
			DatabaseInserter.insertLibrariesFromFile("libraries.csv");
			System.out.println("Inserting patrons...");
			DatabaseInserter.insertPatronsFromFile("patrons.csv");
			System.out.println("Inserting librarians...");
			DatabaseInserter.insertLibrarianFromFile("librarians.csv");
			System.out.println("Inserting administrators...");
			DatabaseInserter.insertAdministratorsFromFile("administrators.csv");
			System.out.println("Inserting books...");
			DatabaseInserter.insertBooksFromFile("books.csv");
			System.out.println("Inserting authors...");
			DatabaseInserter.insertAuthorsFromFile("authors.csv");
			System.out.println("Inserting bookCopies...");
			DatabaseInserter.insertBookCopiesFromFile("bookCopies.csv");
			System.out.println("Inserting loans...");
			DatabaseInserter.insertLoansFromFile("loans.csv");
			System.out.println("Inserting writes...");
			DatabaseInserter.insertWritesFromFile("writes.csv");
			System.out.println("Inserting requests...");
			DatabaseInserter.insertRequestsFromFile("requests.csv");
		}finally {
			DatabaseConnector.close();
		}
		System.out.println("Generation Finished");
	}
	
	public static void generation() {
		InstitutionGenerator institutionGenerator = new InstitutionGenerator(2019);
		institutionGenerator.generate(10);
		Utils.toFile("institutions.csv", institutionGenerator.getGeneratedElements());
		
		LibraryGenerator libraryGenerator = new LibraryGenerator(2000, institutionGenerator);
		libraryGenerator.generate(30);
		Utils.toFile("libraries.csv", libraryGenerator.getGeneratedElements());

		
		PatronGenerator patronGenerator = new PatronGenerator(1998, institutionGenerator);
		patronGenerator.generate(200);
		Utils.toFile("patrons.csv", patronGenerator.getGeneratedElements());
		
		LibrarianGenerator librarianGenerator = new LibrarianGenerator(2020, libraryGenerator);
		librarianGenerator.loadOccupiedPrimaryKeys(patronGenerator.getPrimaryKeySet());
		librarianGenerator.generate(40);
		Utils.toFile("librarians.csv", librarianGenerator.getGeneratedElements());

		AdministratorGenerator administratorGenerator = new AdministratorGenerator(2030, institutionGenerator);
		administratorGenerator.loadOccupiedPrimaryKeys(patronGenerator.getPrimaryKeySet());
		administratorGenerator.loadOccupiedPrimaryKeys(librarianGenerator.getPrimaryKeySet());
		administratorGenerator.generate(10);
		Utils.toFile("administrators.csv", administratorGenerator.getGeneratedElements());

		BookGenerator bookGenerator = new BookGenerator(2040);
		bookGenerator.generate(50);
		Utils.toFile("books.csv", bookGenerator.getGeneratedElements());
		
		AuthorGenerator authorGenerator = new AuthorGenerator(2050);
		authorGenerator.generate(50);
		Utils.toFile("authors.csv", authorGenerator.getGeneratedElements());
		
		BookCopyGenerator bookCopyGenerator =  new BookCopyGenerator(2060, libraryGenerator, bookGenerator);
		bookCopyGenerator.generate(100);
		Utils.toFile("bookCopies.csv", bookCopyGenerator.getGeneratedElements());
		
		LoanGenerator loanGenerator =  new LoanGenerator(2070, bookCopyGenerator, patronGenerator);
		loanGenerator.generate(100);
		Utils.toFile("loans.csv", loanGenerator.getGeneratedElements());
		
		WriteGenerator writeGenerator =  new WriteGenerator(2080, authorGenerator, bookGenerator);
		writeGenerator.generate(100);
		Utils.toFile("writes.csv", writeGenerator.getGeneratedElements());
		
		RequestGenerator requestGenerator =  new RequestGenerator(2090, bookCopyGenerator, patronGenerator);
		requestGenerator.generate(30);
		Utils.toFile("requests.csv", requestGenerator.getGeneratedElements());
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
