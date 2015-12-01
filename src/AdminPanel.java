
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
/**
 *
 * @author sheff
 */
public class AdminPanel extends JPanel {
    
    private final Controller cont;
    
    public AdminPanel(Controller cont) {
        this.cont = cont;
    }
    
    public void render() {
        this.removeAll();
        this.revalidate();
        setLayout(new GridLayout(1,1));
        
		AdminUserPanel adminUser = new AdminUserPanel(cont);
                AdminScreenPanel adminScreen = new AdminScreenPanel(cont);
                AdminShowPanel adminShow = new AdminShowPanel(cont);
                
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.add("Set Admins", adminUser);
                tabbedPane.add("Screens", adminScreen);
                tabbedPane.add("Showings", adminShow);
		add(tabbedPane);
        
        
        //CREATE SCREENS SHOW SCREENS REMOVE SCREEN BUTTON
            
        
        //SEARCH MOVIES
        
        //ADD SHOWING, SHOW SHOWINGS, REMOVE SHOWINGS
    }
    
}
