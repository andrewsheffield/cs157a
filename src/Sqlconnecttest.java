import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        Controller cont = new Controller();
        
        //boolean result = cont.createUser("Andrew", "Sheffield", "andrewsheffield@cs157a.com");
        
        //System.out.println(result);
        
        ArrayList al = cont.searchUsers("@cs157a.com");
        System.out.println(al);
        
        System.out.println(cont.addFriend(12, 3));
        
    }
    
}
