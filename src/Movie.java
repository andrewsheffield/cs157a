
/**
 *
 * @author sheff
 */
public class Movie {
    
    String title;
    String imdbID;
    String plot;
    String year;
    String rated;
    String runtime;
    String posterURL;
    double rating;

    public Movie(String title, String imdbID, String plot, String year, String rated, String runtime, String posterURL, double rating) {
        this.title = title;
        this.imdbID = imdbID;
        this.plot = plot;
        this.year = year;
        this.rated = rated;
        this.runtime = runtime;
        this.posterURL = posterURL;
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "{" 
                + "imdbID:" + this.imdbID
                + ", title:" + this.title
                + ", year:" + this.year
                + ", rating:" + this.rating
                + ", rated:" + this.rated
                + ", runtime:" + this.runtime
                + ", posterURL:" + this.posterURL
                + ", plot:" + this.plot
                + "}";
    }
    
}
