
/**
 *
 * @author sheff
 */
public class User {
    
    public final int        id;
    public final String     fname;
    public final String     lname;
    public final String     email;
    public final boolean    isAdmin;

    public User(int id, String fname, String lname, String email, boolean isAdmin) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.id = id;
        this.isAdmin = isAdmin;
    }
    
    @Override
    public String toString() {
        return "{ID:"+ id + ", " + fname + ", " + lname + ", " + email + ", Admin:" + isAdmin + "}";
    }
    
}
