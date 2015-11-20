
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller {
    
    //Controller has exclusive access to the data access layer.
    final Model model = new Model();
    
    /** NEEDS IMPLEMENTED
     * User search for movies by a timestamp.  Movies will displayed
     * for the DAY of the timestamp but only after the current date and
     * time.  ie If the user selects movies for today only the movie which
     * have not started will be displayed.  This will return any movie regardless
     * of extra features.
     * @param searchDate
     * @return ArrayList
     */
    public ArrayList<Showing> searchShowingsByFilter(Timestamp searchDate) {
        ArrayList<Showing> arraylist= new ArrayList<>();
        return arraylist;
    }
    
    /** NEEDS IMPLEMENTED
     * User search for movies by a timestamp.  Movies will displayed
     * for the DAY of the timestamp but only after the current date and
     * time.  ie If the user selects movies for today only the movie which
     * have not started will be displayed.  If the boolean is set to false
     * the return will ignore that as an option. else will return only the
     * values selected as true.
     * @param searchDate
     * @param isIMAX
     * @param isXD
     * @param is3D
     * @param isDBOX
     * @return ArrayList
     */
    public ArrayList<Showing> searchShowingsByFilter(Timestamp searchDate, boolean isIMAX, boolean isXD, boolean is3D, boolean isDBOX) {
        ArrayList<Showing> arraylist= new ArrayList<>();
        return arraylist;
    }
    
    /** NEEDS ADDED CHECK FOR AMOUNT
     * Purchases tickets for the user for a specific showing.  The amount of 
     * tickets can be anywhere from 1 to the screen size.  or 1 to the remainder
     * of tickets for this show.
     * @param userID
     * @param showingID
     * @param amount
     * @return
     */
    public void purchaseTickets(int userID, int showingID, int amount) {
        model.purchaseTickets(userID, showingID, amount);
    }
    
    /**
     * Cancels all the purchased tickets by a user for a specific showing.
     * @param userID
     * @param showingID
     * @return boolean true if the cancel worked.
     */
    public void cancelTicket(int userID, int showingID) {
        model.cancelTicket(userID, showingID);
    }
    
    /** NEEDS IMPLEMENTATION
     * Finds a ticket from the UserID and ShowingID and changes the owner to the FriendID
     * After this change the FriendID will control this ticket.
     * @param UserID
     * @param FriendID
     * @param ShowingID
     * @return true if transfer is successful
     */
    public boolean sendTicketToFriend(int UserID, int FriendID, int ShowingID) {
        return false;
    }
    
    /** NEEDS IMPLEMENTATION
     * Gets the users purchased tickets.  Returns all future tickets owned by the user
     * @param UserID
     * @return ArrayList of tickets purchased by user or transfered to user.
     */
    public ArrayList<Ticket> viewPurchasedTickets(int UserID) {
        return null;
    }
    
    /** NEEDS IMPLEMENTATION
     * Gets all the tickets purchased by the user where the showing was 
     * previous to the current timestamp
     * @param UserID
     * @return ArrayList of old tickets purchased by user
     */
    public ArrayList<Ticket> viewTicketHistory(int UserID) {
        return null;
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
     * Updates the screens model to reflect changes that may have been made
     * by other connections.
     */
    public void refreshScreens() {
        model.refreshScreens();
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
