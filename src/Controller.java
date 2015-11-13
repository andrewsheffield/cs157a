
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Controller {
    
    private DataAccess dal = new DataAccess();
    
    public User createUser(String fname, String lname, String email, String password) {
        try {
            return dal.createUser(fname, lname, email, password);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
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

    User login(String email, String password) {
        try {
            return dal.login(email, password);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    Screen createScreen(String name, int size, boolean imax, boolean threeD, boolean dbox, boolean xd) {
        try {
            return dal.createScreen(name, size, imax, threeD, dbox, xd);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public boolean purchaseTicket(int userID, int showingID) {

        try {
            return dal.purchaseTicket(userID, showingID);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean purchaseMultipleTickets(int userID, int showingID, int amount) {

        try {
            return dal.purchaseMultipleTickets(userID, showingID, amount);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean cancelTicket(int userID, int showingID) {

        try {
            return dal.cancelTicket(userID, showingID);
        }
        catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    ArrayList getAllScreens() {
        try {
            return dal.getAllScreens();
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    boolean deleteScreen(int id) {
        try {
            return dal.deleteScreen(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean grantAdminAccess(int id) {
        try {
            return dal.grantAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    boolean removeAdminAccess(int id) {
        try {
            return dal.removeAdminAccess(id);
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    
}
