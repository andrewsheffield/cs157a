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
        
        Timestamp timestamp = Timestamp.valueOf("2007-11-24 14:00:00.0");
        
        System.out.println(model.getUpcomingShows());
        cont.createNewShowing(1, "tt2294629", timestamp);
        ArrayList<Showing> showing = model.getUpcomingShows();
        
        System.out.println(showing.get(0).movie.posterURL);
        
    }
}
