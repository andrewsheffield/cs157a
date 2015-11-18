
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller {
    
    //Controller has exclusive access to the data access layer.
    private final DataAccess dal = new DataAccess();
    
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
    
    /** NEEDS IMPLEMENTED
     * Searches for showings that match a movie title.
     * Search result will be an ArrayList containing showings where
     * the movie title matches the search result AND the time of the showing
     * is greater than the current time.  Will return null if no items are found.
     * @param movieTitle
     * @return ArrayList of Showing
     */
    public ArrayList<Showing> searchShowingsByMovie(String movieTitle) {
        return null;
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
    public boolean purchaseTickets(int userID, int showingID, int amount) {
        
        try {
            return dal.purchaseTickets(userID, showingID, amount);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    /**
     * Cancels all the purchased tickets by a user for a specific showing.
     * @param userID
     * @param showingID
     * @return boolean true if the cancel worked.
     */
    public boolean cancelTicket(int userID, int showingID) {
        try {
            return dal.cancelTicket(userID, showingID);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
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
    public User login(String email, String password) {
        try {
            return dal.login(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
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
    public User createUser(String fname, String lname, String email, String password) {
        try {
            return dal.createUser(fname, lname, email, password);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
    
    /** NEEDS EMPLIMENTATION
     *
     * @param fname
     * @param lname
     * @param email
     * @return
     */
    public User updateUserInfo(String fname, String lname, String email) {
        return null;
    }
    
    //Search should check firstname || last name || both || email
    public ArrayList searchUsers(String searchString) {
        try {
            searchString = searchString.trim();
            
            if (searchString.contains("@")) {
                return dal.searchUserEmail(searchString);
            }
            else if (searchString.contains(" ")) {
                String searchFirst = searchString.split(" ")[0];
                String searchLast = searchString.split(" ")[1];
                
                return dal.searchUserFirstAndLast(searchFirst, searchLast);
            }
            else {
                return dal.searchUsersOneName(searchString);
            }
        }
        catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean addFriend(int userID, int friendID) {
        
        try {
            return dal.addFriend(userID, friendID);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;  
        }
    }

    boolean getFriendsForUser(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Screen createScreen(String name, int size, boolean imax, boolean threeD, boolean dbox, boolean xd) {
        try {
            return dal.createScreen(name, size, imax, threeD, dbox, xd);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
   

    public ArrayList getAllScreens() {
        try {
            return dal.getAllScreens();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public boolean deleteScreen(int id) {
        try {
            return dal.deleteScreen(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean grantAdminAccess(int id) {
        try {
            return dal.grantAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean removeAdminAccess(int id) {
        try {
            return dal.removeAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Showing createNewShowing(int screenID, String imdbID, Timestamp timestamp) {
        try {
            return dal.createNewShowing(screenID, imdbID, timestamp);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    //Get shows past current time and date
    ArrayList<Showing> getUpcomingShows() {
        try {
            Date date = new Date();
            Timestamp currentTimestamp = new Timestamp(date.getTime());
            return dal.getUpcomingShows(currentTimestamp);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
}
