import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateUser extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblCreateUser;
	public JButton btnBack;
	public JButton btnCreate;
	private JTextField email;
	private JTextField lName;
	private JTextField fName;

	/**
	 * Create the panel.
	 */
	public CreateUser() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewUser = new JLabel("New User");
		GridBagConstraints gbc_lblNewUser = new GridBagConstraints();
		gbc_lblNewUser.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewUser.gridx = 2;
		gbc_lblNewUser.gridy = 1;
		add(lblNewUser, gbc_lblNewUser);
		
		JLabel lblFirstName = new JLabel("First Name");
		GridBagConstraints gbc_lblFirstName = new GridBagConstraints();
		gbc_lblFirstName.insets = new Insets(0, 0, 5, 5);
		gbc_lblFirstName.gridx = 2;
		gbc_lblFirstName.gridy = 3;
		add(lblFirstName, gbc_lblFirstName);
		
		fName = new JTextField();
		GridBagConstraints gbc_fName = new GridBagConstraints();
		gbc_fName.insets = new Insets(0, 0, 5, 5);
		gbc_fName.fill = GridBagConstraints.HORIZONTAL;
		gbc_fName.gridx = 2;
		gbc_fName.gridy = 4;
		add(fName, gbc_fName);
		fName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		GridBagConstraints gbc_lblLastName = new GridBagConstraints();
		gbc_lblLastName.insets = new Insets(0, 0, 5, 5);
		gbc_lblLastName.gridx = 2;
		gbc_lblLastName.gridy = 5;
		add(lblLastName, gbc_lblLastName);
		
		lName = new JTextField();
		GridBagConstraints gbc_lName = new GridBagConstraints();
		gbc_lName.insets = new Insets(0, 0, 5, 5);
		gbc_lName.fill = GridBagConstraints.HORIZONTAL;
		gbc_lName.gridx = 2;
		gbc_lName.gridy = 6;
		add(lName, gbc_lName);
		lName.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		GridBagConstraints gbc_lblEmail = new GridBagConstraints();
		gbc_lblEmail.insets = new Insets(0, 0, 5, 5);
		gbc_lblEmail.gridx = 2;
		gbc_lblEmail.gridy = 7;
		add(lblEmail, gbc_lblEmail);
		
		email = new JTextField();
		GridBagConstraints gbc_email = new GridBagConstraints();
		gbc_email.insets = new Insets(0, 0, 5, 5);
		gbc_email.fill = GridBagConstraints.HORIZONTAL;
		gbc_email.gridx = 2;
		gbc_email.gridy = 8;
		add(email, gbc_email);
		email.setColumns(10);
		
		btnBack = new JButton("Back");
		GridBagConstraints gbc_btnBack = new GridBagConstraints();
		gbc_btnBack.insets = new Insets(0, 0, 5, 5);
		gbc_btnBack.gridx = 1;
		gbc_btnBack.gridy = 9;
		add(btnBack, gbc_btnBack);
		
		btnCreate = new JButton("Create");
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 5, 0);
		gbc_btnCreate.gridx = 3;
		gbc_btnCreate.gridy = 9;
		add(btnCreate, gbc_btnCreate);
		
	}

}
