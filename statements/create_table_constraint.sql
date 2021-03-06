CREATE TABLE Users(
  email VARCHAR(100),
  uName VARCHAR(30),
  phoneNumber VARCHAR(15),
  uAddress VARCHAR(100),
  PRIMARY KEY(email)
);

CREATE TABLE Institutions(
  iName VARCHAR(100),
  iAddress VARCHAR(100),
  PRIMARY KEY(iName)
);

CREATE TABLE Patrons(
  email VARCHAR(100),
  maxBooks INTEGER,
  iName VARCHAR(100) NOT NULL,
  PRIMARY KEY(email),
  FOREIGN KEY(iName) REFERENCES Institutions,
  FOREIGN KEY(email) REFERENCES Users
);

CREATE TABLE Libraries
(
  lName varchar(100),
  iName varchar(100),
  lAddress varchar(100),
  PRIMARY KEY(lName,iName),
    FOREIGN KEY(iName) REFERENCES Institutions
);

CREATE TABLE Librarians(
  email VARCHAR(100),
  workingDays VARCHAR(7),
  iName VARCHAR(100) NOT NULL,
  lName VARCHAR(100) NOT NULL,
  PRIMARY KEY(email),
  FOREIGN KEY(email) REFERENCES Users,
  FOREIGN KEY(lName,iName) REFERENCES Libraries
);

CREATE TABLE Administrators(
  email VARCHAR(100),
  iName VARCHAR(100),
  PRIMARY KEY(email),
  FOREIGN KEY(email) REFERENCES Users,
  FOREIGN KEY(iName) REFERENCES Institutions
);

CREATE TABLE Books(
  isbnNumber VARCHAR(17),
  title VARCHAR(100),
  category VARCHAR(100),
  publicationDate DATE,
  publisher VARCHAR(100),
  description VARCHAR(500),
  PRIMARY KEY(isbnNumber)
);

CREATE TABLE Authors(
  authorId SERIAL,
  aName VARCHAR(100),
  PRIMARY KEY(authorId)
);

CREATE TABLE BookCopies(
  barCode VARCHAR(50),
  lName VARCHAR(100) NOT NULL,
  iName VARCHAR(100) NOT NULL,
  isbnNumber CHAR(17) NOT NULL,
  PRIMARY KEY(barCode),
  FOREIGN KEY(lName,iName) REFERENCES Libraries,
  FOREIGN KEY(isbnNumber) REFERENCES Books
);

CREATE TABLE Loans
(
  loanId SERIAL,
  startDate DATE,
  requiredReturnDate DATE,
  actualReturnDate DATE,
  fine FLOAT,
  hasRequest BOOLEAN,
  email varchar(100) NOT NULL,
  barCode varchar(50) NOT NULL,
  PRIMARY KEY(loanid),
  FOREIGN KEY(email) REFERENCES Patrons,
  FOREIGN KEY(barCode) REFERENCES BookCopies,
  CONSTRAINT loandateconstraint CHECK (NOT(requiredReturnDate < startDate OR actualReturnDate < startDate))
);

CREATE TABLE Writes
(
  authorId INTEGER,
  isbnNumber varchar(17),
  PRIMARY KEY(authorId, isbnNumber),
  FOREIGN KEY(authorId) REFERENCES Authors,
  FOREIGN KEY(isbnNumber) REFERENCES Books

);

CREATE TABLE Requests
(
  email varchar(100),
  barCode varchar(50),
  date DATE,
  status varchar(20) NOT NULL,
  PRIMARY KEY(email, barCode),
  FOREIGN KEY(email) REFERENCES Patrons,
  FOREIGN KEY(barCode) REFERENCES BookCopies,
  CONSTRAINT requeststatus CHECK (status ='declined' or status = 'in review' or status = 'approved')
);

