import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		
		Login login = new Login();
		CreateUser create = new CreateUser();
		MainMenu homePage = new MainMenu();
		
		contentPane.add(login, "login");
		System.out.println(login.btnCreateUser);
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
				CardLayout cl = (CardLayout) (contentPane.getLayout());
				cl.show(contentPane, "home");
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
		
		create.btnCreate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CardLayout cl = (CardLayout) (contentPane.getLayout());
			
				cl.show(contentPane, "login");
				
			}
		});
		
		contentPane.add(homePage, "home");
	}
	
}
