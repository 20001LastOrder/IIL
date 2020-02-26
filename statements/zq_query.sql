-- Aggregation, grouping and subqueries: 
-- Find the name and publication date of the earliest published book 
-- of each category of books. Only consider categories where there are more than
-- 10 different books.
SELECT title,publicationdate
FROM books
WHERE publicationdate IN
      ( SELECT MIN(publicationdate)
        FROM Books
        GROUP BY category
        HAVING COUNT(*) > 3)
;

-- Aggregation and 2 relations: 
-- Find the average fine of the loans made by the patrons belong to 
-- the institution "Eastern Brown"
SELECT AVG(fine) AS avgFine
FROM patrons P, Loans L
WHERE P.iName = 'Eastern Brown' AND P.email = L.email
;

-- Subquery:
-- Find the name of the institutions and the libraries belong to them which have 
-- the book "A Time to Kill"
SELECT iName,lName
FROM BookCopies
WHERE isbnNumber IN (SELECT isbnNumber
                     FROM Books
                     WHERE title = 'A Time to Kill' )
;


