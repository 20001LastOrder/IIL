SELECT books.title
FROM books
WHERE isbnNumber IN (SELECT bookcopies.isbnNumber
					From loans, bookcopies
					WHERE (loans.barcode = bookcopies.barcode AND loans.actualreturndate isnull))
limit 10;


SELECT lname, iname
FROM libraries
WHERE iname= 'North Quebec College'
limit 10;


SELECT DISTINCT authors.aname
FROM writes, authors
WHERE writes.authorid = authors.authorid AND writes.authorid IN(
	SELECT authorid
	FROM writes
	GROUP BY authorid
	HAVING COUNT(*) >4
)
limit 10;

-- Aggregation, grouping and subqueries: 
-- Find the name and publication date of the earliest published book 
-- of each category of books. Only consider categories where there are more than
-- 3 different books. Show only the first 10 rows that satisfies this query.
SELECT title,publicationdate
FROM books
WHERE publicationdate IN
      ( SELECT MIN(publicationdate)
        FROM Books
        GROUP BY category
        HAVING COUNT(*) > 3)
LIMIT 10
;

-- Aggregation and 2 relations: 
-- Find the average fine of the loans made by the patrons belong to 
-- the institution "Eastern Brown". 
SELECT AVG(fine) AS avgFine
FROM patrons P, Loans L
WHERE P.iName = 'Eastern Brown' AND P.email = L.email
;

-- Subquery:
-- Find the name of the institutions and the libraries belong to them which have 
-- the book "A Time to Kill". Show only the first 10 rows that satisfies this query.
SELECT iName,lName
FROM BookCopies
WHERE isbnNumber IN (SELECT isbnNumber
                     FROM Books
                     WHERE title = 'A Time to Kill' )
LIMIT 10
;






