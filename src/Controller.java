
import java.sql.Timestamp;
import java.util.ArrayList;



public class Controller {
    
    //Controller has exclusive access to the data access layer.
    final Model model = new Model();
    
    /** NEEDS ADDED CHECK FOR AMOUNT
     * Purchases tickets for the user for a specific showing.  The amount of 
     * tickets can be anywhere from 1 to the screen size.  or 1 to the remainder
     * of tickets for this show.
     * @param userID
     * @param showingID
     * @param amount
     * @return
     */
    public void purchaseTickets(int showingID, int amount) {
        model.purchaseTickets(showingID, amount);
    }
    
    /**
     * Cancels all the purchased tickets by a user for a specific showing.
     * @param userID
     * @param amount
     * @param showingID
     * @return boolean true if the cancel worked.
     */
    public void cancelTicket(int showingID, int amount) {
        model.cancelTicket(showingID, amount);
    }
    
    /** NEEDS IMPLEMENTATION
     * Finds a ticket from the UserID and ShowingID and changes the owner to the FriendID
     * After this change the FriendID will control this ticket.
     * @param UserID
     * @param FriendID
     * @param amount
     * @param ShowingID
     * @return true if transfer is successful
     */
    public void sendTicketToFriend(int FriendID, int ShowingID, int amount) {
        model.sendTicketToFriend(FriendID, ShowingID, amount);
    }
    
    /**
     * Searches for the user based of the email and then matches the hashed password
     * user is returned if the authentication is successful, else it returns null
     * @param email
     * @param password
     * @return user or null
     */
    public void login(String email, String password) {
        model.login(email, password);
    }
    
    public void logout() {
        model.logout();
    }
    
    /**
     * Takes specific parameters and creates a new user.  The default admin status of the user
     * is false;
     * @param fname
     * @param lname
     * @param email
     * @param password
     * @return
     */
    public void createUser(String fname, String lname, String email, String password) {
        model.createUser(fname, lname, email, password);
    }
    
    /** NEEDS EMPLIMENTATION
     * Updates the user information.  Place null in any field that will not be
     * updated.
     * @param fname
     * @param lname
     * @param email
     * @return the User after it has been updated
     */
    public User updateUserInfo(String fname, String lname, String email) {
        return null;
    }
    
    /**
     * Takes the userid and the friendid and adds the friend to the users friend
     * list.
     * @param userID
     * @param friendID
     * @return boolean if operation is successful
     */
    public void addFriend(int friendID) {
        model.addFriend(friendID);
    }
    
    /** NEEDS IMPLEMENTATION
     * Gets all the friends associated with the userid.
     * @param userID
     * @return an ArrayList of Users
     */
    public ArrayList<User> getFriends() {
        return model.getFriends();
    }
    
    /** NEEDS IMPLEMENATION
     * Takes a user id and friend id and removes the friend from the friend list.
     * @param userID
     * @param friendID
     * @return boolean true if operation is successful
     */
    public void removeFriend(int id) {
        model.removeFriend(id);
    }
    
    //#################ADMIN ONLY FUNCTIONS###################################

    /**
     *
     * @param id
     * @return
     */
    public void grantAdminAccess(int id) {
        model.grantAdminAccess(id);
    }

    /**
     *
     * @param id
     * @return
     */
    public void removeAdminAccess(int id) {
        model.removeAdminAccess(id);
    }
    
    /**
     *
     * @param screenID
     * @param imdbID
     * @param timestamp
     * @return
     */
    public void createNewShowing(int screenID, String imdbID, Timestamp timestamp) {
        model.createNewShowing(screenID, imdbID, timestamp);
    }
    
    /**
     *
     * @param showingID
     * @return
     */
    public void removeShowing(int showingID) {
        model.removeShowing(showingID);
    }
    
    /**
     *
     * @param name
     * @param size
     * @param imax
     * @param threeD
     * @param dbox
     * @param xd
     * @return
     */
    public void createScreen(String name, int size, boolean imax, boolean threeD, boolean dbox, boolean xd) {
        model.createScreen(name, size, imax, threeD, dbox, xd);
    }
    
    /**
     *
     * @param id
     * @return
     */
    public void deleteScreen(int id) {
        model.deleteScreen(id);
    }

}
