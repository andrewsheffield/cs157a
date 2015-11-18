
/**
 *
 * @author sheff
 */
public class Screen {
    
    public int id;
    public String name;
    public int size;
    public boolean imax;
    public boolean threeD;
    public boolean dbox;
    public boolean xd;

    Screen(int id, String name, int size, boolean imax, boolean threeD, boolean dbox, boolean xd) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.imax = imax;
        this.threeD = threeD;
        this.dbox = dbox;
        this.xd = xd;
    }
    
    @Override
    public String toString() {
        return "{ID:" + id + ", " + name + ", size:" + size + ", IMAX:" + imax + ", 3D:" + threeD + ", DBOX:" + dbox + ", XD:" + xd + "}";
    }
    
}
