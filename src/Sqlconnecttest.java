import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest {

    static Controller cont = new Controller();
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startApp();
    }
    
    private static void startApp() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1:Create User\t2:Search users\t3:Add a friend");
        int choice = scan.nextInt();
        switch (choice) {
            case 1: 
                createUser();
                break;
            case 2:
                searchUsers();
                break;
            case 3:
                addNewFriend();
                break;
            default:
                System.out.println("Invalid selection, try again.");
                startApp();
                break;
        }
    }    

    private static void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstname = scan.next();
        System.out.print("Last Name: ");
        String lastname = scan.next();
        System.out.print("Email: ");
        String email = scan.next();
        
        User user = cont.createUser(firstname, lastname, email);
        
        System.out.println(user);
        startApp();
        
    }

    private static void searchUsers() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type in a search, a search may include any combonation of (First Last) or an email:");
        String searchString = scan.next();
        ArrayList list = cont.searchUsers(searchString);
        System.out.println(list);
        startApp();
    }

    private static void addNewFriend() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Current UserID: ");
        int userid = scan.nextInt();
        System.out.print("FriendsID: ");
        int friendid = scan.nextInt();
        boolean result = cont.addFriend(userid, friendid);
        if (result) System.out.println("Friend added");
        startApp();
    }
}
