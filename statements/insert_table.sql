-- insert new Institutions
INSERT INTO Institutions VALUES('The Parisian', '472 Perry Burgs Suite 670, Montreal, QC, H0F6T5');
INSERT INTO Institutions VALUES('West Newfoundland and Labrador University', '28763 Buckridge Road Suite 801, Montreal, QC, H6E5L4');
INSERT INTO Institutions VALUES('East Krajcik Academy', '1741 Geoffrey Isle Apt. 353, Montreal, QC, H2Q8O0');
INSERT INTO Institutions VALUES('South Ontario Institute', '36126 Stracke Glens Suite 730, Montreal, QC, H3Z7V5');
INSERT INTO Institutions VALUES('Northern Streich University', '62648 Balistreri Run Apt. 366, Montreal, QC, H3X5Q7');
SELECT * FROM Institutions;

-- insert new Libraries
INSERT INTO Libraries VALUES('Gauguin Library', 'The Parisian', '6231 James Cove, Montreal, QC, H9J1T1');
INSERT INTO Libraries VALUES('Da Vinci Library', 'West Newfoundland and Labrador University', '43330 Coralie Passage, Montreal, QC, H2P0K3');
INSERT INTO Libraries VALUES('Bernini Library', 'East Krajcik Academy', '6514 Georgie Mount, Montreal, QC, H1B9B4');
INSERT INTO Libraries VALUES('Dali Library', 'South Ontario Institute', '017 Ross Plains, Montreal, QC, H1C6G7');
INSERT INTO Libraries VALUES('Seurat Library', 'Northern Streich University', '51952 Buckridge Shores, Montreal, QC, H2R4J0');
SELECT * FROM Libraries;

-- insert new Patron
-- in order to create new Patron, we have to insert new user first
INSERT INTO Users VALUES('jerold.leffler77@mail.com', 'Jerold Leffler', '017-172-3505', '0029 Lockman Mission Apt. 364, Montreal, QC, H0C6E4');
INSERT INTO Patrons VALUES('jerold.leffler77@mail.com', 8, 'South Ontario Institute');
INSERT INTO Users VALUES('javier.schuppe13@icloud.com', 'Javier Schuppe', '1-718-559-8625', '81973 Flora Flats Suite 063, Montreal, QC, H9V5G0');
INSERT INTO Patrons VALUES('javier.schuppe13@icloud.com', 5, 'East Krajcik Academy');
INSERT INTO Users VALUES('eldon.marks45@outlook.com', 'Eldon Marks', '1-672-470-7905', '416 Hector Dale Apt. 439, Montreal, QC, H1A1P3');
INSERT INTO Patrons VALUES('eldon.marks45@outlook.com', 6, 'West Newfoundland and Labrador University');
INSERT INTO Users VALUES('donn.mckenzie52@mail.com', 'Donn McKenzie', '253-894-0794', '16032 Pollich Place Apt. 822, Montreal, QC, H3S1Q7');
INSERT INTO Patrons VALUES('donn.mckenzie52@mail.com', 14, 'Northern Streich University');
INSERT INTO Users VALUES('michal.hoeger93@mail.com', 'Michal Hoeger', '1-523-884-8186', '64974 Melinda Circles Apt. 418, Montreal, QC, H0I6B3');
INSERT INTO Patrons VALUES('michal.hoeger93@mail.com', 13, 'Northern Streich University');

SELECT * FROM Users;
SELECT * FROM Patrons;

-- insert new librarians
-- in order to create new Librarians, we have to insert new user first
INSERT INTO Users VALUES('lacy.wyman83@outlook.com', 'Lacy Wyman', '1-276-431-7247', '01669 VonRueden Flats Suite 260, Montreal, QC, H7X8Q3');
INSERT INTO Librarians VALUES('lacy.wyman83@outlook.com', 'MR', 'East Krajcik Academy', 'Bernini Library');
INSERT INTO Users VALUES('otha.kunze64@gmail.com', 'Otha Kunze', '292-710-0326', '699 Wes Path Suite 499, Montreal, QC, H8G3T4');
INSERT INTO Librarians VALUES('otha.kunze64@gmail.com', 'MTWF', 'East Krajcik Academy', 'Bernini Library');
INSERT INTO Users VALUES('preston.bogisich67@icloud.com', 'Preston Bogisich', '1-396-154-7055', '906 Daniel Alley Apt. 118, Montreal, QC, H9X0A5');
INSERT INTO Librarians VALUES('preston.bogisich67@icloud.com', 'M', 'South Ontario Institute', 'Dali Library');
INSERT INTO Users VALUES('eldon.orn00@outlook.com', 'Eldon Orn', '309-993-1152', '0429 Gus Springs Suite 539, Montreal, QC, H8M3K7');
INSERT INTO Librarians VALUES('eldon.orn00@outlook.com', 'MF', 'East Krajcik Academy', 'Bernini Library');
INSERT INTO Users VALUES('tom.dicki15@gmail.com', 'Tom Dicki', '564-975-6266', '1313 Sue Brooks Apt. 953, Montreal, QC, H6C5M9');
INSERT INTO Librarians VALUES('tom.dicki15@gmail.com', 'TWF', 'The Parisian', 'Gauguin Library');

SELECT * FROM Users;
SELECT * FROM Librarians;

-- insert new administrators
-- in order to create new administrators, we need to insertt new user first
INSERT INTO Users VALUES('bo.ondricka13@gmail.com', 'Bo Ondricka', '1-255-831-7174', '792 Elvina Common Suite 434, Montreal, QC, H4D5M7');
INSERT INTO Administrators VALUES('bo.ondricka13@gmail.com', 'The Parisian');
INSERT INTO Users VALUES('tambra.bailey02@icloud.com', 'Tambra Bailey', '346-618-9106', '4300 Hermiston Hollow Suite 219, Montreal, QC, H1Q1V7');
INSERT INTO Administrators VALUES('tambra.bailey02@icloud.com', 'South Ontario Institute');
INSERT INTO Users VALUES('richelle.lubowitz92@outlook.com', 'Richelle Lubowitz', '1-214-461-8498', '20493 Bernhard Dale Suite 191, Montreal, QC, H8R0Q1');
INSERT INTO Administrators VALUES('richelle.lubowitz92@outlook.com', 'South Ontario Institute');
INSERT INTO Users VALUES('harold.nienow43@icloud.com', 'Harold Nienow', '069-912-4650', '262 Archie Trace Suite 327, Montreal, QC, H5F0O6');
INSERT INTO Administrators VALUES('harold.nienow43@icloud.com', 'Northern Streich University');
INSERT INTO Users VALUES('krista.bosco32@gmail.com', 'Krista Bosco', '1-589-277-1367', '572 Bartell Fall Apt. 659, Montreal, QC, H7W6O7');
INSERT INTO Administrators VALUES('krista.bosco32@gmail.com', 'East Krajcik Academy');

SELECT * FROM Users;
SELECT * FROM Administrators;

-- insert books
INSERT INTO Books VALUES('112-3-30-907355-3', 'Françoise Sagan', 'Legend', '2018-01-24', 'Manning Publications', 'Autem nostrum aliquid officia omnis excepturi beatae. Nihil consectetur voluptas non optio. Sint quis et est sapiente maiores voluptatem praesentium. At ratione et natus omnis nihil. Magni est aspernatur veniam.');
INSERT INTO Books VALUES('648-0-43-942465-1', 'Noli Me Tangere', 'Biography/Autobiography', '2013-12-05', 'Anova Books', 'Corrupti facere tenetur sint sit corrupti at delectus. Est dolorum quibusdam. Ratione eum consequatur sit dolorum.');
INSERT INTO Books VALUES('783-9-79-104646-1', 'Blue Remembered Earth', 'Speech', '2015-12-29', 'Bella Books', 'Corporis nulla sed eum aut et quia unde. Animi soluta iusto. Cum ipsam consequatur adipisci. Omnis perferendis ea sit. Ratione harum autem dignissimos.');
INSERT INTO Books VALUES('829-1-32-449156-7', 'A Darkling Plain', 'Crime/Detective', '2013-10-29', 'Barrie & Jenkins', 'Deserunt laboriosam commodi ducimus rem inventore aut quaerat. Sit magni non alias eius natus harum. Asperiores doloremque laborum provident sed est inventore.');
INSERT INTO Books VALUES('760-9-37-234627-0', 'Where Angels Fear to Tread', 'Horror', '2019-07-16', 'Shoemaker & Hoard Publishers', 'Enim rerum ipsam fugit quos corporis dolores. Eius assumenda corporis dolor omnis voluptatem non et. Molestiae minima dicta vel esse nam cum necessitatibus. Et maiores quis laborum aliquam qui ut.');

SELECT * FROM Books;

-- insert authors
INSERT INTO Authors VALUES(982, 'Mr. Denisha Wintheiser');
INSERT INTO Authors VALUES(570, 'Arnold Koss');
INSERT INTO Authors VALUES(871, 'Winnifred MacGyver');
INSERT INTO Authors VALUES(98, 'Charity Schroeder');
INSERT INTO Authors VALUES(608, 'Mrs. Justin Feil');

SELECT * FROM Authors;

-- insert book copies
INSERT INTO BookCopies VALUES('0-779-774-803-6', 'Gauguin Library', 'The Parisian', '783-9-79-104646-1');
INSERT INTO BookCopies VALUES('1-496-774-035-0', 'Gauguin Library', 'The Parisian', '648-0-43-942465-1');
INSERT INTO BookCopies VALUES('6-092-929-853-2', 'Da Vinci Library', 'West Newfoundland and Labrador University', '829-1-32-449156-7');
INSERT INTO BookCopies VALUES('1-962-473-990-0', 'Seurat Library', 'Northern Streich University', '648-0-43-942465-1');
INSERT INTO BookCopies VALUES('0-426-141-379-1', 'Seurat Library', 'Northern Streich University', '829-1-32-449156-7');

SELECT * FROM BookCopies;

-- insert loans
INSERT INTO Loans VALUES('415510', '2018-11-19', '2018-12-01', '2018-12-06', '25.0', 'false', 'donn.mckenzie52@mail.com', '1-496-774-035-0');
INSERT INTO Loans (loanId, startDate, requiredReturnDate, fine, hasRequest, email, barCode) VALUES('892066', '2020-02-13', '2020-03-30', '0.0', 'false', 'eldon.marks45@outlook.com', '0-779-774-803-6');
INSERT INTO Loans VALUES('279097', '2019-06-30', '2019-07-25', '2019-07-06', '0.0', 'false', 'eldon.marks45@outlook.com', '0-779-774-803-6');
INSERT INTO Loans (loanId, startDate, requiredReturnDate, fine, hasRequest, email, barCode) VALUES('342993', '2020-02-01', '2020-03-29', '0.0', 'true', 'javier.schuppe13@icloud.com', '6-092-929-853-2');
INSERT INTO Loans (loanId, startDate, requiredReturnDate, fine, hasRequest, email, barCode) VALUES('214333', '2020-02-21', '2020-02-28', '0.0', 'false', 'eldon.marks45@outlook.com', '0-426-141-379-1');

SELECT * FROM Loans;

-- insert writes
INSERT INTO Writes VALUES(570, '783-9-79-104646-1');
INSERT INTO Writes VALUES(982, '783-9-79-104646-1');
INSERT INTO Writes VALUES(871, '112-3-30-907355-3');
INSERT INTO Writes VALUES(608, '760-9-37-234627-0');
INSERT INTO Writes VALUES(871, '648-0-43-942465-1');

SELECT * FROM Writes;


-- insert request
INSERT INTO Requests VALUES('michal.hoeger93@mail.com', '0-426-141-379-1', '2019-12-31', 'approved');
INSERT INTO Requests VALUES('javier.schuppe13@icloud.com', '0-426-141-379-1', '2018-01-18', 'declined');
INSERT INTO Requests VALUES('donn.mckenzie52@mail.com', '1-962-473-990-0', '2018-05-27', 'approved');
INSERT INTO Requests VALUES('jerold.leffler77@mail.com', '0-779-774-803-6', '2017-12-12', 'in review');
INSERT INTO Requests VALUES('michal.hoeger93@mail.com', '1-496-774-035-0', '2016-06-02', 'approved');

SELECT * FROM Requests;