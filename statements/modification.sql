-- This data modification command deletes the rows 
-- in the table LOANS for which a loan 
-- has been returned in a timely fashion.

DELETE FROM loans 
	WHERE requiredreturndate >= actualreturndate ;

-- This data modification command updates 
-- the value of a request that was done 
-- before the year 2018 and that is still “in review” to “declined”.

UPDATE requests
SET status = 'declined'
WHERE status = 'in review' AND date <= '2018-01-01'::date;

-- A new deal between the libraries and 
-- Northern Stretch University and East Krajcik Academy 
-- allows the students from both schools to borrow an additional two books.
UPDATE patrons
SET maxbooks = maxbooks + 2
WHERE iname = 'Northern Stretch University' OR iname = 'East Krajcik Academy';

-- Create a loan for all approved requests and set them hasRequests to True
-- This command will be used for next deliverable 
INSERT INTO Loans(barCode, email, hasRequest) (
	SELECT barCode, email, 'true' FROM Requests WHERE status = 'approved'
);

-- Delete every approved request. 
-- This command will be used for next deliverable 
-- we create a loan from an approved request.
DELETE FROM requests
WHERE status = 'approved';