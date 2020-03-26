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

	/**
	 * By ZiQi
	 * Input:
	 *     a book name
	 *     Ex: 'Tiger! Tiger!'
	 * Output:
	 *     Find the name of the institutions and the libraries belong to them which have
	 *     the requested book. Show all the libraries that satisfies this query.
	 * @param bookname the name of book requested
	 */
	public static void option1(String bookname) {
		Statement stat = DatabaseConnector.getStatement();

		try {
			ResultSet result = stat.executeQuery(
					"SELECT iName,lName\n" +
							"FROM BookCopies\n" +
							"WHERE isbnNumber IN (SELECT isbnNumber\n" +
							"FROM Books\n" +
							"WHERE title = " + "'" + bookname + "'" + " )");
			// retrieve the result row by row
			String iName;
			String lName;
			// note: Cursor initially set just before first row, so call next() make it pointing to the first row
			while (result.next()) {
				iName = result.getString("iName");
				lName = result.getString("lName");
				System.out.println("Institution: " + iName + "   " + "Library: " + lName);
			}

			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseConnector.closeStatement(stat);
		}
	}

	/**
	 * By ZiQi
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
	public static void option2(String email, String isbn) {
		Statement stat = DatabaseConnector.getStatement();
		ResultSet result = null;

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


					Boolean insertRequest = stat.execute(
							"INSERT INTO requests VALUES (" + "'" + email + "'" + "," + "'" + barCode + "'" + ",\n" +
									"'" + date.toString() + "'" + ",'in review')"
					);
				}
				catch (SQLException e)
				{
					// if the request is already existed in the database, prompt a error message to user
					System.out.println("There is already a request made by " + email + " on the book: " + isbn);
				}
				//System.out.println(insertRequest);
				System.out.println("The request on the book " + isbn + " has been made successfully by " + email);
			}
			else
			{
				System.out.println("The requested book is not available in the patron's institution.");
			}

			result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DatabaseConnector.closeStatement(stat);
			}

	}



}
