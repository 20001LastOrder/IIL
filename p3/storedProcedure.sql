CREATE OR REPLACE FUNCTION createLoansFromApprovedRequest(selectedIname VARCHAR(100), selectedLname VARCHAR(100), loanLength INTEGER) RETURNS INTEGER AS $$
	DECLARE rec RECORD;	
			createdLoanCount INTEGER;
			currentDate Date;
			patronBookPairs CURSOR FOR 
					(WITH BookRequests AS
					(SELECT R.email, R.barcode, R.status FROM requests R, Bookcopies B
					WHERE R.barcode = B.barcode AND B.lname = selectedLname AND B.iname = selectedIname)
					SELECT br.email, br.barcode
                    FROM BookRequests br
                    WHERE br.status = 'approved');
	BEGIN
		createdLoanCount := 0;
		currentDate := (SELECT CURRENT_DATE);
					
		FOR rec IN patronBookPairs LOOP
			createdLoanCount := createdLoanCount + 1;
			INSERT INTO Loans(startdate, requiredreturndate, actualreturndate, fine, hasrequest, email, barcode) VALUES
			(currentDate,currentDate + loanLength,NULL, 0,true,rec.email,rec.barcode);
			
			DELETE FROM Requests r WHERE r.email = rec.email AND r.barcode = rec.barcode; 
		END LOOP;
		RETURN createdLoanCount;
    END;
$$ LANGUAGE plpgsql;

DELETE FROM Requests WHERE barcode IN (SELECT barcode FROM BookCopies WHERE lname = 'Gauguin Library' AND iname = 'The Parisian');
SELECT * FROM Requests WHERE barcode IN (SELECT barcode FROM BookCopies WHERE lname = 'Gauguin Library' AND iname = 'The Parisian');

INSERT INTO Requests VALUES ('jerold.leffler77@mail.com', '1-496-774-035-0', (SELECT * FROM CURRENT_DATE), 'approved');
INSERT INTO Requests VALUES ('shayna.treutel65@mail.com', '0-554-732-880-2', (SELECT * FROM CURRENT_DATE), 'approved');
INSERT INTO Requests VALUES ('gretchen.mckenzie39@gmail.com', '1-926-480-684-4', (SELECT * FROM CURRENT_DATE), 'approved');
SELECT * FROM Requests WHERE barcode IN (SELECT barcode FROM BookCopies WHERE lname = 'Gauguin Library' AND iname = 'The Parisian');

DELETE FROM Loans WHERE (email, barcode) IN (('jerold.leffler77@mail.com', '1-496-774-035-0'), ('shayna.treutel65@mail.com', '0-554-732-880-2'), ('gretchen.mckenzie39@gmail.com', '1-926-480-684-4'));
SELECT * FROM Loans WHERE (email, barcode) IN (('jerold.leffler77@mail.com', '1-496-774-035-0'), ('shayna.treutel65@mail.com', '0-554-732-880-2'), ('gretchen.mckenzie39@gmail.com', '1-926-480-684-4'));

SELECT createLoansFromApprovedRequest('The Parisian', 'Gauguin Library', 100);

SELECT * FROM Loans WHERE (email, barcode) IN (('jerold.leffler77@mail.com', '1-496-774-035-0'), ('shayna.treutel65@mail.com', '0-554-732-880-2'), ('gretchen.mckenzie39@gmail.com', '1-926-480-684-4'));