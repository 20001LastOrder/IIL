ALTER TABLE loans 
ADD CONSTRAINT loandateconstraint
CHECK (requiredReturnDate > startDate and actualReturnDate > startDate);

ALTER TABLE Requests
ADD CONSTRAINT requeststatus
CHECK (status ='declined' or status = 'in review' or status = 'approved');


INSERT INTO Requests VALUES('gfutianzhu@gmail.com','2-123-432-345-0','2019-12-30','lalala');