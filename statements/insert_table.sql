-- insert new Institutions
INSERT INTO Institutions VALUES('The Parisian', '472 Perry Burgs, Montreal, QC, H7C6C7');
INSERT INTO Institutions VALUES('Hermiston University', '8282 Yost Village, Montreal, QC, H7M6S3');
INSERT INTO Institutions VALUES('Southern Howell', '654 Perry Overpass, Montreal, QC, H8C6R6');
INSERT INTO Institutions VALUES('North Lind Institute', '9235 Prohaska Bypass, Montreal, QC, H0J4Z6');
INSERT INTO Institutions VALUES('Huel Academy', '61261 Shanna Causeway, Montreal, QC, H3F0B3');
SELECT * FROM Institutions;

-- insert new Libraries
INSERT INTO Libraries VALUES('Gauguin Library', 'The Parisian', '6231 James Cove, Montreal, QC, H9J1T1');
INSERT INTO Libraries VALUES('Da Vinci Library', 'Hermiston University', '43330 Coralie Passage, Montreal, QC, H2P0K3');
INSERT INTO Libraries VALUES('Bernini Library', 'Southern Howell', '6514 Georgie Mount, Montreal, QC, H1B9B4');
INSERT INTO Libraries VALUES('Dali Library', 'North Lind Institute', '017 Ross Plains, Montreal, QC, H1C6G7');
INSERT INTO Libraries VALUES('Seurat Library', 'Huel Academy', '51952 Buckridge Shores, Montreal, QC, H2R4J0');
SELECT * FROM Libraries;

-- insert new Patron
-- in order to create new Patron, we have to insert new user first
INSERT INTO Users VALUES('emmett.ernser79@outlook.com', 'Emmett Ernser', '1-171-723-5053', '0296 Reynolds Rapid Apt. 640, Montreal, QC, H6E4U4');
INSERT INTO Patrons VALUES('emmett.ernser79@outlook.com', 13, 'North Lind Institute');
INSERT INTO Users VALUES('garth.cummings.dvm98@gmail.com', 'Garth Cummings DVM', '185-598-6252', '733 Nadine Parkways Apt. 639, Montreal, QC, H5G0T7');
INSERT INTO Patrons VALUES('garth.cummings.dvm98@gmail.com', 14, 'The Parisian');
INSERT INTO Users VALUES('xiao.ondricka97@outlook.com', 'Xiao Ondricka', '247-079-0561', '694 Maisha Squares Suite 911, Montreal, QC, H3W8A1');
INSERT INTO Patrons VALUES('xiao.ondricka97@outlook.com', 5, 'Southern Howell');
INSERT INTO Users VALUES('chance.hahn94@gmail.com', 'Chance Hahn', '389-407-9449', '321 Moira Shoals Suite 231, Montreal, QC, H7L8O0');
INSERT INTO Patrons VALUES('chance.hahn94@gmail.com', 10, 'Hermiston University');
INSERT INTO Users VALUES('jen.murphy.i86@gmail.com', 'Jen Murphy I', '1-388-481-8648', '4548 Huels Fields Suite 806, Montreal, QC, H3Z6N5');
INSERT INTO Patrons VALUES('jen.murphy.i86@gmail.com', 13, 'Southern Howell');

SELECT * FROM Users;
SELECT * FROM Patrons;

-- insert new librarians
-- in order to create new Librarians, we have to insert new user first
INSERT INTO Users VALUES('lacy.wyman83@outlook.com', 'Lacy Wyman', '1-276-431-7247', '01669 VonRueden Flats Suite 260, Montreal, QC, H7X8Q3');
INSERT INTO Librarians VALUES('lacy.wyman83@outlook.com', 'MR', 'Southern Howell', 'Bernini Library');
INSERT INTO Users VALUES('otha.kunze64@gmail.com', 'Otha Kunze', '292-710-0326', '699 Wes Path Suite 499, Montreal, QC, H8G3T4');
INSERT INTO Librarians VALUES('otha.kunze64@gmail.com', 'MTWF', 'Southern Howell', 'Bernini Library');
INSERT INTO Users VALUES('preston.bogisich67@icloud.com', 'Preston Bogisich', '1-396-154-7055', '906 Daniel Alley Apt. 118, Montreal, QC, H9X0A5');
INSERT INTO Librarians VALUES('preston.bogisich67@icloud.com', 'M', 'North Lind Institute', 'Dali Library');
INSERT INTO Users VALUES('eldon.orn00@outlook.com', 'Eldon Orn', '309-993-1152', '0429 Gus Springs Suite 539, Montreal, QC, H8M3K7');
INSERT INTO Librarians VALUES('eldon.orn00@outlook.com', 'MF', 'Southern Howell', 'Bernini Library');
INSERT INTO Users VALUES('tom.dicki15@gmail.com', 'Tom Dicki', '564-975-6266', '1313 Sue Brooks Apt. 953, Montreal, QC, H6C5M9');
INSERT INTO Librarians VALUES('tom.dicki15@gmail.com', 'TWF', 'The Parisian', 'Gauguin Library');

SELECT * FROM Users;
SELECT * FROM Librarians;

-- insert new administrators
-- in order to create new administrators, we need to insertt new user first
INSERT INTO Users VALUES('bo.ondricka13@gmail.com', 'Bo Ondricka', '1-255-831-7174', '792 Elvina Common Suite 434, Montreal, QC, H4D5M7');
INSERT INTO Administrators VALUES('bo.ondricka13@gmail.com', 'The Parisian');
INSERT INTO Users VALUES('tambra.bailey02@icloud.com', 'Tambra Bailey', '346-618-9106', '4300 Hermiston Hollow Suite 219, Montreal, QC, H1Q1V7');
INSERT INTO Administrators VALUES('tambra.bailey02@icloud.com', 'North Lind Institute');
INSERT INTO Users VALUES('richelle.lubowitz92@outlook.com', 'Richelle Lubowitz', '1-214-461-8498', '20493 Bernhard Dale Suite 191, Montreal, QC, H8R0Q1');
INSERT INTO Administrators VALUES('richelle.lubowitz92@outlook.com', 'North Lind Institute');
INSERT INTO Users VALUES('harold.nienow43@icloud.com', 'Harold Nienow', '069-912-4650', '262 Archie Trace Suite 327, Montreal, QC, H5F0O6');
INSERT INTO Administrators VALUES('harold.nienow43@icloud.com', 'Huel Academy');
INSERT INTO Users VALUES('krista.bosco32@gmail.com', 'Krista Bosco', '1-589-277-1367', '572 Bartell Fall Apt. 659, Montreal, QC, H7W6O7');
INSERT INTO Administrators VALUES('krista.bosco32@gmail.com', 'Southern Howell');

SELECT * FROM Users;
SELECT * FROM Administrators;

-- insert books
INSERT INTO Books VALUES('112-3-30-907355-3', 'Françoise Sagan', 'Legend', '2018-01-24', 'Manning Publications', 'Autem nostrum aliquid officia omnis excepturi beatae. Nihil consectetur voluptas non optio. Sint quis et est sapiente maiores voluptatem praesentium. At ratione et natus omnis nihil. Magni est aspernatur veniam.');
INSERT INTO Books VALUES('648-0-43-942465-1', 'Noli Me Tangere', 'Biography/Autobiography', '2013-12-05', 'Anova Books', 'Corrupti facere tenetur sint sit corrupti at delectus. Est dolorum quibusdam. Ratione eum consequatur sit dolorum.');
INSERT INTO Books VALUES('783-9-79-104646-1', 'Blue Remembered Earth', 'Speech', '2015-12-29', 'Bella Books', 'Corporis nulla sed eum aut et quia unde. Animi soluta iusto. Cum ipsam consequatur adipisci. Omnis perferendis ea sit. Ratione harum autem dignissimos.');
INSERT INTO Books VALUES('829-1-32-449156-7', 'A Darkling Plain', 'Crime/Detective', '2013-10-28', 'Barrie & Jenkins', 'Deserunt laboriosam commodi ducimus rem inventore aut quaerat. Sit magni non alias eius natus harum. Asperiores doloremque laborum provident sed est inventore.');
INSERT INTO Books VALUES('760-9-37-234627-0', 'Where Angels Fear to Tread', 'Horror', '2019-07-16', 'Shoemaker & Hoard Publishers', 'Enim rerum ipsam fugit quos corporis dolores. Eius assumenda corporis dolor omnis voluptatem non et. Molestiae minima dicta vel esse nam cum necessitatibus. Et maiores quis laborum aliquam qui ut.');

SELECT * FROM Books;

-- insert authors
INSERT INTO Authors VALUES(982, 'Mr. Denisha Wintheiser');
INSERT INTO Authors VALUES(570, 'Arnold Koss');
INSERT INTO Authors VALUES(871, 'Winnifred MacGyver');
INSERT INTO Authors VALUES(98, 'Charity Schroeder');
INSERT INTO Authors VALUES(608, 'Mrs. Justin Feil');

SELECT * FROM Authors;