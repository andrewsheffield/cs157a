DROP VIEW IF EXISTS friendView;
CREATE VIEW friendView AS
SELECT friend.UserID, user.UserID AS FriendID, user.FirstName, user.LastName, user.Email FROM
friend, user
WHERE
friendID = user.UserID
ORDER BY user.LastName ASC, user.FirstName ASC;

DROP TRIGGER IF EXISTS deleteFriendTrigger;
DELIMITER //
CREATE TRIGGER deleteFriendTrigger
BEFORE DELETE ON user FOR EACH ROW
BEGIN
	DELETE FROM friend WHERE old.UserID = UserID;
	DELETE FROM friend WHERE old.UserID = FriendID;
END //
DELIMITER ;

DROP PROCEDURE IF EXISTS getAllUsers;
DELIMITER //
CREATE PROCEDURE getAllUsers ()
BEGIN
	SELECT * FROM user;
END //
DELIMITER ;

