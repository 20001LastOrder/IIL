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
		InstitutionGenerator institutionGenerator = new InstitutionGenerator(2019);
		institutionGenerator.generate(5);
		Utils.toFile("institutions.csv", institutionGenerator.getGeneratedElements());
		
		LibraryGenerator libraryGenerator = new LibraryGenerator(2000, institutionGenerator);
		libraryGenerator.generate(5);
		Utils.toFile("libraries.csv", libraryGenerator.getGeneratedElements());

		
		PatronGenerator patronGenerator = new PatronGenerator(1998, institutionGenerator);
		patronGenerator.generate(5);
		Utils.toFile("patrons.csv", patronGenerator.getGeneratedElements());
		
		LibrarianGenerator librarianGenerator = new LibrarianGenerator(2020, libraryGenerator);
		librarianGenerator.loadOccupiedPrimaryKeys(patronGenerator.getPrimaryKeySet());
		librarianGenerator.generate(5);
		Utils.toFile("librarians.csv", librarianGenerator.getGeneratedElements());

		AdministratorGenerator administratorGenerator = new AdministratorGenerator(2030, institutionGenerator);
		administratorGenerator.loadOccupiedPrimaryKeys(patronGenerator.getPrimaryKeySet());
		administratorGenerator.loadOccupiedPrimaryKeys(librarianGenerator.getPrimaryKeySet());
		administratorGenerator.generate(5);
		Utils.toFile("administrators.csv", administratorGenerator.getGeneratedElements());

		BookGenerator bookGenerator = new BookGenerator(2040);
		bookGenerator.generate(5);
		Utils.toFile("books.csv", bookGenerator.getGeneratedElements());
		
		AuthorGenerator authorGenerator = new AuthorGenerator(2050);
		authorGenerator.generate(5);
		Utils.toFile("authors.csv", authorGenerator.getGeneratedElements());
		
		BookCopyGenerator bookCopyGenerator =  new BookCopyGenerator(2060, libraryGenerator, bookGenerator);
		bookCopyGenerator.generate(5);
		Utils.toFile("bookCopies.csv", bookCopyGenerator.getGeneratedElements());
		
		LoanGenerator loanGenerator =  new LoanGenerator(2070, bookCopyGenerator, patronGenerator);
		loanGenerator.generate(5);
		Utils.toFile("loans.csv", loanGenerator.getGeneratedElements());
		
		WriteGenerator writeGenerator =  new WriteGenerator(2080, authorGenerator, bookGenerator);
		writeGenerator.generate(5);
		Utils.toFile("writes.csv", writeGenerator.getGeneratedElements());
		
		RequestGenerator requestGenerator =  new RequestGenerator(2090, bookCopyGenerator, patronGenerator);
		requestGenerator.generate(5);
		Utils.toFile("requests.csv", requestGenerator.getGeneratedElements());
		
//		DatabaseInserter.insertInstitutionsFromFile("institutions.csv");
//		DatabaseInserter.insertLibrariesFromFile("libraries.csv");
//		DatabaseInserter.insertPatronsFromFile("patrons.csv");
//		DatabaseInserter.insertLibrarianFromFile("librarians.csv");
//		DatabaseInserter.insertAdministratorsFromFile("administrators.csv");
//		DatabaseInserter.insertBooksFromFile("books.csv");
//		DatabaseInserter.insertAuthorsFromFile("authors.csv");
//		DatabaseInserter.insertBookCopiesFromFile("bookCopies.csv");
//		DatabaseInserter.insertLoansFromFile("loans.csv");
//		DatabaseInserter.insertWritesFromFile("writes.csv");
		DatabaseInserter.insertRequestsFromFile("requests.csv");
		
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
