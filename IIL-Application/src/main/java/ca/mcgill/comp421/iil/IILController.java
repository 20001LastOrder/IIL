package ca.mcgill.comp421.iil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.comp421.iil.database.DatabaseConnector;
import ca.mcgill.comp421.ill.model.ResultTable;

public class IILController {
	/**
	 * @author ZiQi
	 * Input:
	 *     a book name
	 *     Ex: 'Tiger! Tiger!'
	 * Output:
	 *     Find the name of the institutions and the libraries belong to them which have
	 *     the requested book. Show all the libraries that satisfies this query.
	 * @param bookname the name of book requested
	 */
	public ResultTable findBookLocation(String bookname) {
		Statement stat = DatabaseConnector.getStatement();
		List<String> keyType = new ArrayList<String>();
		keyType.add("iName");
		keyType.add("lName");
		ResultTable table = null;
		
		try {
			ResultSet result = stat.executeQuery(
					"SELECT iName,lName\n" +
							"FROM BookCopies\n" +
							"WHERE isbnNumber IN (SELECT isbnNumber\n" +
							"FROM Books\n" +
							"WHERE title = " + "'" + bookname + "'" + " )");
			// retrieve the result row by row
			table = new ResultTable(keyType);
			table.loadResult(result);
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConnector.closeStatement(stat);
		}
		return table;
	}
	
	/**
	 * @author ZiQi
	 * Input:
	 *     email, book isbnNumber
	 *     Ex: 'ronny.mayer22@mail.com', '005-9-72-540567-1'
	 * Output:
	 *     Ask the patron to enter his/her email and book isbnNumber, if any libraries that belongs to
	 *     the patron's institution have such a book, make a request on one of the book copies of this Book.
	 *     Otherwise, prompt "NO available bookcopy" message to the user.
	 *
	 *     If the barcode list is not empty, make a request on one of the bookcopies in the list
	 * 	   for the patron. Ex: barCode: '9-695-397-170-5'. Using the current date.
	 * @param email email of the user
	 * @param isbn isbn of the requested book
	 */
	public String makeRequest(String email, String isbn) {
		Statement stat = DatabaseConnector.getStatement();
		ResultSet result = null;
		String resultToReturn = "";
		try {
			//Find the requested Book Copy's barcode list
			result = stat.executeQuery(
					"SELECT barcode\n" +
							"FROM bookcopies Bc, patrons P\n" +
							"WHERE Bc.isbnnumber = " + "'" + isbn + "'" + "\n" +
							"  AND Bc.iname = P.iname AND P.email = " + "'" + email + "'" + "\n"
			);

			// Get the current date
			long millis = System.currentTimeMillis();
			java.sql.Date date = new java.sql.Date(millis);

			// If the barcode list "result" is not null, make a request on any one of the book copy,
			// the date parameter is the current date.
			if (result.next())
			{
				String barCode = result.getString("barcode");
				try {


					stat.execute(
							"INSERT INTO requests VALUES (" + "'" + email + "'" + "," + "'" + barCode + "'" + ",\n" +
									"'" + date.toString() + "'" + ",'in review')"
					);
				}
				catch (SQLException e)
				{
					// if the request is already existed in the database, prompt a error message to user
					resultToReturn = "There is already a request made by " + email + " on the book: " + isbn;
				}
				resultToReturn = "The request on the book " + isbn + " has been made successfully by " + email;
			}
			else
			{
				resultToReturn = "The requested book is not available in the patron's institution.";
			}

			result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnector.closeStatement(stat);
			}
		return resultToReturn;
	}
	
	public String addNewPatron(String email, String uName,  String phoneNumber,String uAddress, String iname) {
		String resultToReturn = "";
		ResultSet result = null;
		Statement stat = DatabaseConnector.getStatement();
		
		try {
			result = stat.executeQuery(String.format("SELECT * FROM INSTITUTIONS WHERE iname='%s'", iname));
			
			// first check if the institution exists
			int count = 0;
			while(result.next()) count ++;
		
			if(count > 0) {
				// insert the new patron if the institution exist
				stat.execute(String.format("INSERT INTO Users VALUES ('%s', '%s', '%s', '%s')", email, uName, phoneNumber, uAddress)); 
				stat.execute(String.format("INSERT INTO Patrons VALUES ('%s', %d, '%s')", email, 10, iname)); 
				resultToReturn = "New patron inserted";
			}else {
				resultToReturn = "ERROR: cannot find the institution";
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			resultToReturn = "Error: Inserting new patron failed";
		}finally {
			DatabaseConnector.closeStatement(stat);
		}
		return resultToReturn;
	}
	
	public ResultTable getAllLoansForPatron(String email) {
		ResultSet result = null;
		ResultTable table = null;
		Statement stat = DatabaseConnector.getStatement();
		List<String> keys = new ArrayList<String>(Arrays.asList("loanId", "startDate", "requiredReturnDate", "actualReturnDate", "fine", "isbnNumber", "title", "iname", "lname"));

		try {
			result = stat.executeQuery(String.format("SELECT l.loanId, l.startDate, l.requiredReturnDate, l.actualReturnDate, l.fine, b.isbnNumber, b.title, bc.iname, bc.lname "
					+ "FROM Loans l, BookCopies bc, Books b "
					+ "WHERE l.barcode = bc.barcode and bc.isbnNumber = b.isbnNumber and l.email = '%s'", email));
			table = new ResultTable(keys);
			table.loadResult(result);
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConnector.closeStatement(stat);
		}
	
		return table;
	}

}
