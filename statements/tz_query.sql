SELECT books.title
FROM books
WHERE isbnNumber IN (SELECT bookcopies.isbnNumber
					From loans, bookcopies
					WHERE (loans.barcode = bookcopies.barcode AND loans.actualreturndate isnull))


SELECT lname, iname
FROM libraries
WHERE iname= 'North Quebec College'


SELECT DISTINCT authors.aname
FROM writes, authors
WHERE writes.authorid = authors.authorid AND writes.authorid IN(
	SELECT authorid
	FROM writes
	GROUP BY authorid
	HAVING COUNT(*) >4
)
