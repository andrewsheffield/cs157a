import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Friends extends JPanel {
	private JTextField friendID;
	private JTextField txtSearchfriendid;
	private JTextField movieID;
	private JTextField giveFriendID;

	/**
	 * Create the panel.
	 */
	public Friends() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 40, 0, 0, 32, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblAddFriend = new JLabel("Add Friend");
		GridBagConstraints gbc_lblAddFriend = new GridBagConstraints();
		gbc_lblAddFriend.insets = new Insets(0, 0, 5, 5);
		gbc_lblAddFriend.gridx = 1;
		gbc_lblAddFriend.gridy = 1;
		add(lblAddFriend, gbc_lblAddFriend);
		
		friendID = new JTextField();
		GridBagConstraints gbc_friendID = new GridBagConstraints();
		gbc_friendID.fill = GridBagConstraints.HORIZONTAL;
		gbc_friendID.insets = new Insets(0, 0, 5, 5);
		gbc_friendID.gridx = 3;
		gbc_friendID.gridy = 1;
		add(friendID, gbc_friendID);
		friendID.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		GridBagConstraints gbc_btnAdd = new GridBagConstraints();
		gbc_btnAdd.insets = new Insets(0, 0, 5, 0);
		gbc_btnAdd.gridx = 5;
		gbc_btnAdd.gridy = 1;
		add(btnAdd, gbc_btnAdd);
		
		JLabel lblSearchFriend = new JLabel("Search User");
		GridBagConstraints gbc_lblSearchFriend = new GridBagConstraints();
		gbc_lblSearchFriend.insets = new Insets(0, 0, 5, 5);
		gbc_lblSearchFriend.gridx = 1;
		gbc_lblSearchFriend.gridy = 4;
		add(lblSearchFriend, gbc_lblSearchFriend);
		
		txtSearchfriendid = new JTextField();
		GridBagConstraints gbc_txtSearchfriendid = new GridBagConstraints();
		gbc_txtSearchfriendid.insets = new Insets(0, 0, 5, 5);
		gbc_txtSearchfriendid.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtSearchfriendid.gridx = 3;
		gbc_txtSearchfriendid.gridy = 4;
		add(txtSearchfriendid, gbc_txtSearchfriendid);
		txtSearchfriendid.setColumns(10);
		
		JButton btnFind = new JButton("Find");
		GridBagConstraints gbc_btnFind = new GridBagConstraints();
		gbc_btnFind.insets = new Insets(0, 0, 5, 0);
		gbc_btnFind.gridx = 5;
		gbc_btnFind.gridy = 4;
		add(btnFind, gbc_btnFind);
		
		JLabel lblGiveAFriend = new JLabel("Give a Friend a Ticket");
		GridBagConstraints gbc_lblGiveAFriend = new GridBagConstraints();
		gbc_lblGiveAFriend.insets = new Insets(0, 0, 5, 5);
		gbc_lblGiveAFriend.gridx = 1;
		gbc_lblGiveAFriend.gridy = 6;
		add(lblGiveAFriend, gbc_lblGiveAFriend);
		
		JLabel lblMovieid = new JLabel("MovieID");
		GridBagConstraints gbc_lblMovieid = new GridBagConstraints();
		gbc_lblMovieid.insets = new Insets(0, 0, 5, 5);
		gbc_lblMovieid.gridx = 1;
		gbc_lblMovieid.gridy = 7;
		add(lblMovieid, gbc_lblMovieid);
		
		movieID = new JTextField();
		GridBagConstraints gbc_movieID = new GridBagConstraints();
		gbc_movieID.insets = new Insets(0, 0, 5, 5);
		gbc_movieID.fill = GridBagConstraints.HORIZONTAL;
		gbc_movieID.gridx = 3;
		gbc_movieID.gridy = 7;
		add(movieID, gbc_movieID);
		movieID.setColumns(10);
		
		JLabel lblFriendid = new JLabel("FriendID");
		GridBagConstraints gbc_lblFriendid = new GridBagConstraints();
		gbc_lblFriendid.insets = new Insets(0, 0, 0, 5);
		gbc_lblFriendid.gridx = 1;
		gbc_lblFriendid.gridy = 8;
		add(lblFriendid, gbc_lblFriendid);
		
		giveFriendID = new JTextField();
		GridBagConstraints gbc_giveFriendID = new GridBagConstraints();
		gbc_giveFriendID.insets = new Insets(0, 0, 0, 5);
		gbc_giveFriendID.fill = GridBagConstraints.BOTH;
		gbc_giveFriendID.gridx = 3;
		gbc_giveFriendID.gridy = 8;
		add(giveFriendID, gbc_giveFriendID);
		giveFriendID.setColumns(10);
		
		JButton btnGive = new JButton("Give");
		GridBagConstraints gbc_btnGive = new GridBagConstraints();
		gbc_btnGive.gridx = 5;
		gbc_btnGive.gridy = 8;
		add(btnGive, gbc_btnGive);

	}

}
