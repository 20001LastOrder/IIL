SELECT COUNT(*) as AuthorCount From Authors;
SELECT COUNT(*) as BookCopyCount From BookCopies;
SELECT COUNT(*) as BookCount From Books;
SELECT COUNT(*) as InstitutionCount From Institutions;
SELECT COUNT(*) as LibraryCount From Libraries;
SELECT COUNT(*) as LibrarianCount From Librarians;
SELECT COUNT(*) as LoanCount From Loans;
SELECT COUNT(*) as AdministratorCount From Administrators;
SELECT COUNT(*) as PatronCount From Patrons;
SELECT COUNT(*) as UserCount From Users;
SELECT COUNT(*) as RequestCount From Requests;
SELECT COUNT(*) as WriteCount From Writes;

SELECT * FROM Authors LIMIT 10;
SELECT * FROM BookCopies LIMIT 10;
SELECT * FROM Books LIMIT 10;
SELECT * FROM Institutions LIMIT 10;
SELECT * FROM Libraries LIMIT 10;
SELECT * FROM Librarians LIMIT 10;
SELECT * FROM Loans LIMIT 10;
SELECT * FROM Administrators LIMIT 10;
SELECT * FROM Patrons LIMIT 10;
SELECT * FROM Users LIMIT 10;
SELECT * FROM Requests LIMIT 10;
SELECT * FROM Writes LIMIT 10;
