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

    static Controller cont = new Controller();
    static Model model = cont.model;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        cont.login("sheff@apple.com", "password");
        //System.out.println(model.getUserSearchResults("sheff"));
        
        Timestamp currentTimestamp = Timestamp.valueOf("2015-11-30 14:00:00.0");
        
        //cont.createNewShowing(1, "tt0277434", currentTimestamp);
        System.out.println(model.getShowingsByMovieTitle("We Were Soldiers"));
        
    }
}
