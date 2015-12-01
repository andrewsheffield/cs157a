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
    
    Controller cont;
    Display movies;
    Friends friend;
    Profile profile;
    AdminPanel admin;

    /**
     * Create the panel.
     */
    public MainMenu(Controller cont) {
        this.cont = cont;
        
            setLayout(new GridLayout(1,1));
            movies = new Display(cont);
            friend = new Friends(cont);
            profile = new Profile(cont);
            admin = new AdminPanel(cont);

            JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
            tabbedPane.add("Showing", movies);
            tabbedPane.add("Users", friend);
            tabbedPane.add("Friends/Profile", profile);
            if (cont.model.getUser().isAdmin) {
                tabbedPane.add("Admin", admin);
            }
            add(tabbedPane);
            render();
    }

    public void render() {
        movies.render();
        friend.render();
        profile.render();
        admin.render();
    }
}
