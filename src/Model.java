
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sheff
 */
public class Model {
    
    private User currentUser = null;
    private ArrayList<User> friends;

    //Model has exclusive access to the data access layer.
    private final DataAccess dal = new DataAccess();
    private final MovieAccess mal = new MovieAccess();

    public boolean isLoggedIn() {
        return (currentUser != null);
    }
    
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
    public ArrayList<Showing> getShowingsByMovieTitle(String movieTitle) {
        Movie movie = mal.getMovieByName(movieTitle);
        try {
            System.out.println(movie);
            return dal.getShowingByimdbID(movie.imdbID);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public Movie getMovieByTitle(String title) {
        return mal.getMovieByName(title);
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
    public void purchaseTickets(int showingID, int amount) {
        try {
            dal.purchaseTickets(currentUser.id, showingID, amount);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Cancels all the purchased tickets by a user for a specific showing.
     * @param userID
     * @param amount
     * @param showingID
     * @return boolean true if the cancel worked.
     */
    public void cancelTicket(int showingID, int amount) {
        try {
            dal.cancelTicket(currentUser.id, showingID, amount);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
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
        try {
            dal.sendTicketToFriend(currentUser.id, FriendID, ShowingID, amount);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /** NEEDS IMPLEMENTATION
     * Gets the users purchased tickets.  Returns all future tickets owned by the user
     * @param UserID
     * @return ArrayList of tickets purchased by user or transfered to user.
     */
    public ArrayList<Ticket> getPurchasedTickets() {
        try {
            return dal.getPurchasedTickets(currentUser.id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public ArrayList<Ticket> getPurchasedTickets(int friendID) {
        try {
            return dal.getPurchasedTickets(friendID);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /** NEEDS IMPLEMENTATION
     * Gets all the tickets purchased by the user where the showing was 
     * previous to the current timestamp
     * @param UserID
     * @return ArrayList of old tickets purchased by user
     */
    public ArrayList<Ticket> getTicketHistory() {
        try {
            return dal.getTicketHistory(currentUser.id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     * Searches for the user based of the email and then matches the hashed password
     * user is returned if the authentication is successful, else it returns null
     * @param email
     * @param password
     * @return user or null
     */
    public void login(String email, String password) {
        try {
            this.currentUser = dal.login(email, password);
            if (isLoggedIn()) {
                this.friends = dal.getFriends(currentUser.id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public User getUser() {
        return currentUser;
    }
    
    public void logout() {
        currentUser = null;
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
        try {
            currentUser =  dal.createUser(fname, lname, email, password);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    
    /** NEEDS EMPLIMENTATION
     * Updates the user information.  Place null in any field that will not be
     * updated.
     * @param fname
     * @param lname
     * @param email
     * @return the User after it has been updated
     */
    public void updateUserInfo(String fname, String lname, String email) {
        try {
            dal.updateUserInfo(currentUser.id, fname, lname, email);
            User newUser = new User(currentUser.id, fname, lname, email, currentUser.isAdmin);
            currentUser = newUser;
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Takes the userid and the friendid and adds the friend to the users friend
     * list.
     * @param userID
     * @param friendID
     * @return boolean if operation is successful
     */
    public void addFriend(int friendID) {
        try {
            dal.addFriend(currentUser.id, friendID);
            friends = dal.getFriends(currentUser.id);
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Searches for users in the user databse by a searchstring.  The search string
     * can contain any substring of first name, last name, first last name, or email.
     * @param searchString
     * @return arraylist of users matching the search string
     */
    public ArrayList<User> getUserSearchResults(String searchString) {
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
    
    /** NEEDS IMPLEMENTATION
     * Gets all the friends associated with the userid.
     * @param userID
     * @return an ArrayList of Users
     */
    public ArrayList<User> getFriends() {
        return friends;
    }
    
    /** NEEDS IMPLEMENATION
     * Takes a user id and friend id and removes the friend from the friend list.
     * @param userID
     * @param friendID
     * @return boolean true if operation is successful
     */
    public void removeFriend(int friendID) {
        try {
            dal.removeFriend(currentUser.id, friendID);
            friends = dal.getFriends(currentUser.id);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    //#################ALL ADMIN MODEL###################################

    /**
     *
     * @param id
     * @return
     */
    
    public void grantAdminAccess(int id) {
        try {
            dal.grantAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    public void removeAdminAccess(int id) {
        try {
            dal.removeAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *
     * @param screenID
     * @param imdbID
     * @param timestamp
     * @return
     */
    public void createNewShowing(int screenID, String imdbID, Timestamp timestamp) {
        try {
            dal.createNewShowing(screenID, imdbID, timestamp);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Showing> getUpcomingShows() {
        Date date = new Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        try {
            return dal.getUpcomingShows(currentTimestamp);
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     *
     * @param showingID
     * @return
     */
    public void removeShowing(int showingID) {
        Date date = new Date();
        Timestamp currentTimestamp = new Timestamp(date.getTime());
        dal.removeShowing(showingID);
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
        try {
            dal.createScreen(name, size, imax, threeD, dbox, xd);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<Screen> getAllScreens() {
        try {
            return dal.getAllScreens();
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    public void deleteScreen(int id) {
        try {
            dal.deleteScreen(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
