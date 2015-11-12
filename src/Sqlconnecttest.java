import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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
        
        //System.out.println(cont.createUser("Peter", "Pham", "pham@cs157a.com"));
        
        ArrayList al = cont.searchUsers("and eff");
        System.out.println(al);
        
        System.out.println(cont.addFriend(1, 2));
        
    }
    
}
