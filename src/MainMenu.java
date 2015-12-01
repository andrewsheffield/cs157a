import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class MainMenu extends JPanel {

	/**
	 * Create the panel.
	 */
	public MainMenu(Controller cont) {
		setLayout(new GridLayout(1,1));
		Display movies = new Display(cont);
		Friends friend = new Friends(cont);
		Profile profile = new Profile(cont);
		AdminPanel admin = new AdminPanel(cont);
                
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.add("Showing", movies);
		tabbedPane.add("Users", friend);
		tabbedPane.add("Friends/Profile", profile);
                if (cont.model.getUser().isAdmin) {
                    tabbedPane.add("Admin", admin);
                }
		add(tabbedPane);
		
	}
}
