import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainFrame extends JFrame {

	private JPanel contentPane;
        Controller cont;

	/**
	 * Create the frame.
	 */
	public MainFrame(Controller cont) {
            this.cont = cont;
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 1000, 800);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(new CardLayout(0, 0));

            Login login = new Login();
            CreateUser create = new CreateUser();

            contentPane.add(login, "login");
            login.btnCreateUser.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            CardLayout cl = (CardLayout) (contentPane.getLayout());
                            {
                                    cl.show(contentPane, "create");
                            }
                    }
            });

            login.btnLogin.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            login.login(cont);
                            if (cont.model.isLoggedIn()) {
                                MainMenu homePage = new MainMenu(cont);
                                contentPane.add(homePage, "home");
                                CardLayout cl = (CardLayout) (contentPane.getLayout());
                                cl.show(contentPane, "home");
                            } else {
                                CardLayout cl = (CardLayout) (contentPane.getLayout());
                                login.setFalseCred();
                                cl.show(contentPane, "login");
                            }
                            
                    }

            });
            contentPane.add(create, "create");
            create.btnBack.addActionListener(new ActionListener(){

                    @Override
                    public void actionPerformed(ActionEvent e) {
                            // TODO Auto-generated method stub
                            CardLayout cl = (CardLayout) (contentPane.getLayout());
                            cl.show(contentPane, "login");

                    }
            });
            create.btnCreate.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {                    
                    // TODO Auto-generated method stub
                    create.createUser(cont);
                    if (cont.model.isLoggedIn()) {
                        MainMenu homePage = new MainMenu(cont);
                        contentPane.add(homePage, "home");
                        CardLayout cl = (CardLayout) (contentPane.getLayout());
                        cl.show(contentPane, "home");
                    } else {
                        CardLayout cl = (CardLayout) (contentPane.getLayout());
                        cl.show(contentPane, "login");
                    }

                }
            });



            
	}
	
}
