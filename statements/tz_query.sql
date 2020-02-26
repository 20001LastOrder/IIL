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
