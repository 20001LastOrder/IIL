CREATE VIEW activeLoans (email, title, lname, iname, startDate, requiredReturnDate ) AS 
SELECT l.email, b.title, b.lname, b.iname, l.startDate, l.requiredReturnDate FROM Loans l, 
(SELECT DISTINCT b.title, bc.barcode, bc.iname, lname FROM BookCopies bc, Books b WHERE bc.isbnNumber = b.isbnNumber) b WHERE
b.barcode = l.barcode AND l.actualReturnDate isnull;

SELECT * from activeLoans;

UPDATE activeLoans SET title='none' WHERE title='Blue Remembered Earth';

CREATE VIEW approvedRequests(barcode, email, status) AS 
SELECT barcode, email, status FROM Requests WHERE status='declined';

SELECT * FROM approvedRequests;
DROP VIEW approvedRequests;

UPDATE approvedRequests SET email = 'shaunna.heller15@gmail.com' WHERE email='daysi.mueller74@icloud.com';

SELECT * FROM Requests WHERE status='approved';

