import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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

    static Controller cont = new Controller();
    static User currentUser = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        startApp();
    }
    
    private static void startApp() {
        Scanner scan = new Scanner(System.in);
        
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
        } else {
            System.out.println("1:Search Users\t2:Add Friend\t3:Search Movies");
            int choice = scan.nextInt();
            switch (choice) {
                case 1: 
                    searchUsers();
                    break;
                case 2:
                    addNewFriend();
                    break;
                case 3:
                    searchMovies();
                    break;
                default:
                    System.out.println("Invalid selection, try again.");
                    startApp();
                    break;
            }
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

    private static void searchMovies() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Search Movie by Name: ");
        String movieName = scan.next();
        
        URL imdb;
        try {
            imdb = new URL("http://www.omdbapi.com/?t=Frozen&y=&plot=short&r=json");
            URLConnection yc = imdb.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                Object obj = JSONValue.parse(inputLine);
                JSONObject json = (JSONObject) obj;
                System.out.println(json.get("imdbID"));
                System.out.println(json.get("Metascore"));
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    
    }

}
