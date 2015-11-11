
import java.sql.SQLException;
import java.util.ArrayList;



public class Controller {
    
    DataAccess dal = new DataAccess();
    
    public boolean createUser(String fname, String lname, String email) {
        try {
            return dal.createUser(fname, lname, email);
        } catch (SQLException ex) {
            System.out.println(ex);
            return false;
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
    
    
}
