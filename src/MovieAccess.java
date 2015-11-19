
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author sheff
 */
public class MovieAccess {
    
    public Movie getMovieByName(String movieName) {
        movieName = movieName.trim();
        movieName = movieName.replaceAll(" ", "+");
        
        URL imdb;
        try {
            imdb = new URL("http://www.omdbapi.com/?t=" + movieName + "&y=&plot=short&r=json");
            URLConnection yc = imdb.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                Object obj = JSONValue.parse(inputLine);
                JSONObject json = (JSONObject) obj;
                
                String title = json.get("Title").toString();
                String imdbID = json.get("imdbID").toString();
                String plot = json.get("Plot").toString();
                String year = json.get("Year").toString();
                String rated = json.get("Rated").toString();
                String runtime = json.get("Runtime").toString();
                String posterURL = json.get("Poster").toString();
                double rating = Double.parseDouble(json.get("imdbRating").toString());
                
                Movie movie = new Movie(title, imdbID, plot, year, rated, runtime, posterURL, rating);
                return movie;
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Movie getMovieByID(String imdbID) {
        URL imdbURL;
        try {
            imdbURL = new URL("http://www.omdbapi.com/?i=" + imdbID + "&y=&plot=short&r=json");
            URLConnection yc = imdbURL.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                Object obj = JSONValue.parse(inputLine);
                JSONObject json = (JSONObject) obj;
                
                String title = json.get("Title").toString();
                String plot = json.get("Plot").toString();
                String year = json.get("Year").toString();
                String rated = json.get("Rated").toString();
                String runtime = json.get("Runtime").toString();
                String posterURL = json.get("Poster").toString();
                double rating = Double.parseDouble(json.get("imdbRating").toString());
                
                Movie movie = new Movie(title, imdbID, plot, year, rated, runtime, posterURL, rating);
                return movie;
            }
            in.close();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } catch (IOException ex) {
            Logger.getLogger(Sqlconnecttest.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;
    }
    
}
