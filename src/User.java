
/**
 *
 * @author sheff
 */
public class User {
    
    public final int   id;
    public final String fname;
    public final String lname;
    public final String email;

    public User(int id, String fname, String lname, String email) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.id = id;
    }
    
    @Override
    public String toString() {
        return "{"+ id + ", " + fname + ", " + lname + ", " + email + "}";
    }
    
}
