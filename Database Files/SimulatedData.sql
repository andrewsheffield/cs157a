USE theaterpro;

-- Create Screens to be used
INSERT INTO screen(Name, Size, IMAX, 3D, XD, DBOX) VALUES
("Screen 1", 20, 0, 1, 0, 0),
("Screen 2", 25, 0, 1, 0, 0),
("Screen 3", 30, 0, 1, 0, 0),
("Screen 4", 35, 0, 0, 1, 0),
("Screen 5", 40, 0, 0, 1, 0),
("Screen 6", 40, 0, 1, 1, 0),
("Screen 7", 40, 0, 0, 0, 1),
("Screen 8", 50, 0, 0, 0, 1),
("Screen 9", 80, 1, 0, 0, 0);

-- This theater only shows pixar movies
-- "Inside Out" = tt2096673
-- "Cars" = tt0317219
-- "Toy Story" = tt0114709
-- "Monsters Inc" = tt0198781
INSERT INTO showing(ScreenID, imdbID, startTimestamp) VALUES
(1, "tt2096673", "2015-12-20 13:00:00"),
(2, "tt2096673", "2015-12-20 13:45:00"),
(3, "tt2096673", "2015-12-20 14:30:00"),
(1, "tt2096673", "2015-12-20 15:00:00"),
(2, "tt2096673", "2015-12-20 15:45:00"),
(3, "tt2096673", "2015-12-20 16:30:00"),
(4, "tt0317219", "2015-12-20 13:00:00"),
(5, "tt0317219", "2015-12-20 13:45:00"),
(6, "tt0317219", "2015-12-20 14:30:00"),
(4, "tt0317219", "2015-12-20 15:00:00"),
(5, "tt0317219", "2015-12-20 15:45:00"),
(6, "tt0317219", "2015-12-20 16:30:00"),
(7, "tt0114709", "2015-12-20 13:00:00"),
(8, "tt0114709", "2015-12-20 14:00:00"),
(7, "tt0114709", "2015-12-20 15:00:00"),
(8, "tt0114709", "2015-12-20 16:00:00"),
(9, "tt0198781", "2015-12-20 13:00:00"),
(9, "tt0198781", "2015-12-20 15:00:00"),
(9, "tt0198781", "2015-12-20 17:00:00");

-- Data added to simulate archive
INSERT INTO showing(ScreenID, imdbID, startTimestamp) VALUES
(1, "tt2096673", "2013-12-20 13:00:00"),
(2, "tt2096673", "2013-12-20 13:45:00"),
(3, "tt2096673", "2013-12-20 14:30:00"),
(1, "tt2096673", "2013-12-20 15:00:00"),
(2, "tt2096673", "2013-12-20 15:45:00"),
(3, "tt2096673", "2013-12-20 16:30:00"),
(4, "tt0317219", "2013-12-20 13:00:00"),
(5, "tt0317219", "2013-12-20 13:45:00"),
(6, "tt0317219", "2013-12-20 14:30:00"),
(4, "tt0317219", "2013-12-20 15:00:00"),
(5, "tt0317219", "2013-12-20 15:45:00"),
(6, "tt0317219", "2013-12-20 16:30:00"),
(7, "tt0114709", "2013-12-20 13:00:00"),
(8, "tt0114709", "2013-12-20 14:00:00"),
(7, "tt0114709", "2013-12-20 15:00:00"),
(8, "tt0114709", "2013-12-20 16:00:00"),
(9, "tt0198781", "2013-12-20 13:00:00"),
(9, "tt0198781", "2013-12-20 15:00:00"),
(9, "tt0198781", "2013-12-20 17:00:00");

-- Update old movies updatedAt times to allow to archive
UPDATE showing SET updatedAt="2013-12-20 13:00:00" 
WHERE ShowingID>19;

-- BUILD SAMPLE USERS

INSERT INTO user(FirstName, LastName, Email) VALUES
("Andrew", "Sheffield", "andrewsheffield@cs157a.com"),
("Peter", "Pham", "peterpham@cs157a.com"),
("Bowen", "Chan", "bowenchan@cs157a.com"),
("Suneuy", "Kim", "suneuy.kim@sjsu.edu");

INSERT INTO auth(UserID, hPassword) VALUES
(1, 312768894),
(2, 312768894),
(3, 312768894),
(4, 312768894);

-- ADD each user as friends
INSERT INTO friend(UserID, FriendID) VALUES
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 4),
(4, 1),
(4, 2),
(4, 3);


-- every test user gets a 2 free tickets to the first showing
INSERT INTO ticket(UserID, ShowingID) VALUES
(1, 1),
(1, 1),
(2, 1),
(2, 1),
(3, 1),
(3, 1),
(4, 1),
(4, 1);

-- create tickets to archive
INSERT INTO ticket(UserID, ShowingID) VALUES
(1, 20),
(1, 20),
(2, 20),
(2, 20),
(3, 20),
(3, 20),
(4, 20),
(4, 20);

-- update tickets to allow to be archived
UPDATE ticket SET updatedAt="2013-12-20 13:00:00"
WHERE ShowingID=20;
