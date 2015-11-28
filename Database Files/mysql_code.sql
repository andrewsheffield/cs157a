9. View movie watching history
SELECT imdbID 
FROM Showing
where ShowingID in (select showingID from Ticket);

#Has no way to correlate users with Showings

10. Log into Theater account
Select *
from User, Auth
where USERSID = ? and
h(password) = ?;

11. Logout

12. Register for a Theater Account
Insert into User(UsersID, Email, FirstName, LastName, isAdmin)
	Values(?, ?, ?, ?, FALSE);

13. Disable account
Delete FROM Users where USERSID = ?;

14. Update user information/preferences
Update Users set FirstName = NEW.FirstName, LastName = NEW.LastName, Email = NEW.EMAIL,
WHERE 
	UsersID = old.USERSID;

15. Add a friend to list
Insert into Friend(UsersID, FriendsID) 
	VALUES(?, ?)

16. view/search friend list
CREATE VIEW friendView AS
SELECT friend.UserID, user.UserID AS FriendID, user.FirstName, user.LastName, user.Email FROM
friend, user
WHERE
friendID = user.UserID
ORDER BY user.LastName ASC, user.FirstName ASC;

17. remove a friend
CREATE TRIGGER deleteFriendTrigger
BEFORE DELETE ON user FOR EACH ROW
BEGIN
	DELETE FROM friend WHERE old.UserID = UserID;
	DELETE FROM friend WHERE old.UserID = FriendID;
END

18.View friend movie watching profile


19. Notify user if friend is watching same movie
select Distinct ShowingID, FriendID from friend, ticket WHERE friend.UserID=1 AND friendID=ticket.UserID;

20. Trigger for first user is automatically admin