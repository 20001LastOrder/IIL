-- create view to record the loan information for all currently running loans
CREATE VIEW activeLoans (email, title, lname, iname, startDate, requiredReturnDate ) AS 
SELECT l.email, b.title, b.lname, b.iname, l.startDate, l.requiredReturnDate FROM Loans l, 
(SELECT DISTINCT b.title, bc.barcode, bc.iname, lname FROM BookCopies bc, Books b WHERE bc.isbnNumber = b.isbnNumber) b WHERE
b.barcode = l.barcode AND l.actualReturnDate isnull;

SELECT * from activeLoans LIMIT 10;

-- does not work, it involves multiple relations
UPDATE activeLoans SET title='none' WHERE title='Blue Remembered Earth';

CREATE VIEW InReviewRequests(barcode, email, status) AS 
SELECT barcode, email, status FROM Requests WHERE status='in review';

SELECT * FROM InReviewRequests LIMIT 10;

--fail
UPDATE InReviewRequests SET email = 'doesnot.exist@gmail.com' WHERE email='kip.olson27@mail.com';
--success
UPDATE InReviewRequests SET email = 'shaunna.heller15@gmail.com' WHERE email='kip.olson27@mail.com';
--success
UPDATE InReviewRequests SET status = 'approved';

-- nothing anymore
SELECT * FROM InReviewRequests LIMIT 10;


