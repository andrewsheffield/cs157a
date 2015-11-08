SELECT a.UserID, b.UserID, b.FirstName, b.LastName, b.Email FROM
user AS a, friend, user AS b
WHERE
b.UserID = friend.FriendID
ORDER BY b.LastName, b.FirstName ASC;

DROP VIEW IF EXISTS friendView;
CREATE VIEW friendView AS
SELECT friend.UserID, user.UserID AS FriendID, user.FirstName, user.LastName, user.Email FROM
friend, user
WHERE
friendID = user.UserID
ORDER BY user.LastName ASC, user.FirstName ASC;

DROP TrIGGER IF EXISTS deleteFriendTrigger;
CREATE TRIGGER deleteFriendTrigger ON friendView
INSTEAD OF DELETE
AS
BEGIN



END