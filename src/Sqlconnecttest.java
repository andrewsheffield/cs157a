import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author sheff
 */
public class Sqlconnecttest {

    static MovieAccess mal = new MovieAccess();
    static Controller cont = new Controller();
    static User currentUser = null;
    private static Movie movieSearchResult;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startApp();
    }
    
    private static void startApp() {
        Scanner scan = new Scanner(System.in);
            editShowings();
            /*
            if (currentUser == null) {
            System.out.println("1:Create User\t2:Login");
            int choice = scan.nextInt();
            switch (choice) {
            case 1:
            createUser();
            break;
            case 2:
            login();
            break;
            default:
            System.out.println("Invalid selection, try again.");
            startApp();
            break;
            }
            }
            else if (!currentUser.isAdmin) {
            System.out.println("1:Search Users\t2:Add Friend\t3:Buy Ticket(s)");
            int choice = scan.nextInt();
            switch (choice) {
            case 1:
            searchUsers();
            break;
            case 2:
            addNewFriend();
            break;
            case 3:
            buyTickets();
            break;
            default:
            System.out.println("Invalid selection, try again.");
            startApp();
            break;
            }
            }
            else {
            System.out.println("1:Edit Screens\t2:Edit Showings\t3:Edit Admins");
            int choice = scan.nextInt();
            switch (choice) {
            case 1:
            editScreens();
            break;
            case 2:
            editShowings();
            break;
            case 3:
            editAdmins();
            break;
            default:
            System.out.println("Incorrect choice.");
            startApp();
            break;
            }
            }*/
    }    

    private static void createUser() {
        Scanner scan = new Scanner(System.in);
        System.out.print("First Name: ");
        String firstname = scan.next();
        System.out.print("Last Name: ");
        String lastname = scan.next();
        System.out.print("Email: ");
        String email = scan.next();
        System.out.print("Password: ");
        String password = scan.next();
        
        User user = cont.createUser(firstname, lastname, email, password);
        
        System.out.println(user);
        startApp();
        
    }
    
    private static void login() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Email: ");
        String email = scan.next();
        System.out.print("Password: ");
        String password = scan.next();
        User user = cont.login(email, password);
        
        if(user == null) {
            System.out.println("Incorrent credentials, try again.");
            login();
        } else {
            currentUser = user;
            System.out.println("Login Successful.");
            startApp();
        }
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
        System.out.print("FriendsID: ");
        int friendid = scan.nextInt();
        boolean result = cont.addFriend(currentUser.id, friendid);
        if (result) System.out.println("Friend added");
        startApp();
    }

    private static void buyTickets() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void editScreens() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1:Create Screen\t2:Shows Screens\t3:Delete Screen");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                createScreen();
                break;
            case 2:
                showsScreens();
                break;
            case 3:
                deleteScreen();
                break;
            default:
                System.out.println("Incorrect choice.");
                editScreens();
                break;
        }
    }

    private static void editShowings() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1:Create Showing\t2:Show Upcoming Shows\t3:Remove Showing");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                {
                    try {
                        createNewShowing();
                    } catch (ParseException ex) {
                        Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                break;
            case 2:
                getUpcomingShows();
                break;
            case 3:
                removeShowing();
                break;
            default:
                System.out.println("Not a valid selection");
                startApp();
                break;
        }
    }

    private static void editAdmins() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1:Search Users\t2:Add Admin Access\t3:Remove Admin Access");
        int choice = scan.nextInt();
        switch (choice) {
            case 1:
                System.out.print("Search string: ");
                String searchString = scan.next();
                System.out.println(cont.searchUsers(searchString));
                editAdmins();
                break;
            case 2:
                grantAdminAccess();
                break;
            case 3:
                removeAdminAccess();
                break;
            default:
                System.out.println("Incorrect choice.");
                editAdmins();
                break;
        }
    }
    
    private static void createScreen() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Screen name: ");
        String name = scan.nextLine();
        System.out.print("Screen size: ");
        int size = scan.nextInt();
        System.out.print("Is IMAX?(y,n): ");
        String imax = scan.next();
        System.out.print("Is 3D?(y,n): ");
        String threeD = scan.next();
        System.out.print("Is DBOX?(y,n): ");
        String dbox = scan.next();
        System.out.print("Is XD?(y,n): ");
        String xd = scan.next();
        
        System.out.println(cont.createScreen(name, size, (imax.equals("y")), (threeD.equals("y")), (dbox.equals("y")), (xd.equals("y"))));
        editScreens();
        
    }

    private static void showsScreens() {
        ArrayList<Screen> screenList = cont.getAllScreens();
        screenList.stream().forEach((s) -> {
            System.out.println(s);
        });
        editScreens();
    }

    private static void deleteScreen() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter the ID to be deleted: ");
        int id = scan.nextInt();
        if (cont.deleteScreen(id)) System.out.println("Screen has been removed.");
        editScreens();
    }

    private static void grantAdminAccess() {
        Scanner scan = new Scanner(System.in);
        System.out.print("ID to give Admin access: ");
        int id = scan.nextInt();
        if (cont.grantAdminAccess(id)) System.out.println("Access has been granted.");
        editAdmins();
    }

    private static void removeAdminAccess() {
        Scanner scan = new Scanner(System.in);
        System.out.print("ID to remove Admin access: ");
        int id = scan.nextInt();
        if (cont.removeAdminAccess(id)) System.out.println("Access has been removed.");
        editAdmins();
    }

    private static void createNewShowing() throws ParseException {
        Scanner scan = new Scanner(System.in);
        searchMovieByName();
        System.out.println(movieSearchResult);
        System.out.println("Use this movie(1) or search again(2)?");
        
        switch (scan.nextInt()) {
            case 1:
                System.out.print("ScreenID to use: ");
                int screenID = scan.nextInt();
                System.out.print("Date of Showing(yyyy-mm-dd): ");
                String date = scan.next();
                System.out.print("Time of Showing 24 hour(hh:mm): ");
                String time = scan.next();
                Timestamp timestamp = Timestamp.valueOf(date + " " + time + ":00.0");
                System.out.println(cont.createNewShowing(screenID, movieSearchResult.imdbID, timestamp));
                startApp();
                break;
            case 2:
                createNewShowing();
                break;
            default:
                System.out.println("Incorrect Selection");
                startApp();
                break;
        }
    
    }

    private static void getUpcomingShows() {
        ArrayList<Showing> al = cont.getUpcomingShows();
        al.stream().forEach((s) -> {
            System.out.println(s);
        });
    }

    private static void removeShowing() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static void searchMovieByName() {
        System.out.println("Search by movie Name: ");
        Scanner scan = new Scanner(System.in);
        String searchString = scan.nextLine();
        movieSearchResult = mal.getMovieByName(searchString);
    }

}
