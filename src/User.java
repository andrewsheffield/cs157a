
/**
 *
 * @author sheff
 */
public class User {
    
    private final String fname;
    private final String lname;
    private final String email;

    public User(String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "{" + fname + ", " + lname + ", " + email + "}";
    }
    
}
