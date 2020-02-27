-- requiredReturnDate cannot be earlier than startDate, actualReturnDate cannot be earlier than startDate
ALTER TABLE loans 
ADD CONSTRAINT loandateconstraint
CHECK (NOT(requiredReturnDate < startDate OR actualReturnDate < startDate));

INSERT INTO loans Values(default, '2020-02-10', '2020-02-09', null, 0, false, 'kelley.mcglynn24@mail.com', '8-964-571-616-8');

-- the status of a review should be either of the three string value
ALTER TABLE Requests
ADD CONSTRAINT requeststatus
CHECK (status ='declined' or status = 'in review' or status = 'approved');

INSERT INTO Requests VALUES('aleisha.walsh82@mail.com', '7-407-065-278-5', '2020-02-26', 'not what we want');