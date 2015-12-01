
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author sheff
 */
class AdminShowPanel extends JPanel {
    
    Controller cont;

    public AdminShowPanel(Controller cont) {
        this.cont = cont;
        render();
    }
    
    public void render() {
        this.removeAll();
        this.revalidate();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        JPanel movieSearchPanel = new JPanel();
        
        JLabel movieSearchLabel = new JLabel("<html>Search Movies:</html>");
        movieSearchPanel.add(movieSearchLabel);
        
        JTextField searchMovieField = new JTextField(30);
        movieSearchPanel.add(searchMovieField);
        
        JButton searchMovieButton = new JButton("Search");
        movieSearchPanel.add(searchMovieButton);
        
        searchMovieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cont.model.movieSearchString = searchMovieField.getText();
                render();
            }
        });
        
        JPanel movieSearchResultsPanel = new JPanel();
        Movie movie = null;
       final ArrayList imdbID = new ArrayList();
        
        if (cont.model.movieSearchString.isEmpty()) {
            JLabel movieSearch = new JLabel("<html>Type a search string to begin.</html>");
            movieSearchResultsPanel.add(movieSearch);
        } else {
            movie = cont.model.getMovieByTitle(cont.model.movieSearchString);
            imdbID.add(movie.imdbID);
            
            JLabel movieSearch = new JLabel("<html>" + movie.title + " " + movie.year + " " + movie.rating + " " + movie.rated + "</html>");
            movieSearchResultsPanel.add(movieSearch);
        }
        
        ArrayList<Screen> screens = cont.model.getAllScreens();
        
        String[] screenTitles = new String[screens.size()];
        
        for (int i=0;i<screens.size();i++) {
            screenTitles[i] = screens.get(i).name;
        }
        

        //Create the combo box, select item at index 4.
        //Indices start at 0, so 4 specifies the pig.
        JComboBox screenList = new JComboBox(screenTitles);
        movieSearchResultsPanel.add(screenList);
        JTextField setShowingTime = new JTextField("hh:mm");
        movieSearchResultsPanel.add(setShowingTime);
        JTextField setShowingDate = new JTextField("yyyy-mm-dd");
        movieSearchResultsPanel.add(setShowingDate);
        
        JButton addMovieBtn = new JButton("Add Showing");
        if (movie == null) addMovieBtn.setEnabled(false);
        addMovieBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int screenID = screens.get(screenList.getSelectedIndex()).id;
                Timestamp showtime = Timestamp.valueOf( setShowingDate.getText() + " "+ setShowingTime.getText() + ":00.0");
                cont.createNewShowing(screenID, (String) imdbID.get(0), showtime);
                render();
            }
        });
        movieSearchResultsPanel.add(addMovieBtn);
        
        //Table of showings
        //Search Results
        JPanel showingsListPanel = new JPanel();
            ArrayList<Showing> showingsResult = cont.model.getUpcomingShows();
            
            JScrollPane showingsScrollPane = new JScrollPane();
           
            
            String[] ShowingColumnNames = {"ID", "Screen", "imdbID", "Time", "Remove"};
            Object[][] showingData = {};
            
            DefaultTableModel showingModel = new DefaultTableModel(showingData, ShowingColumnNames){
                @Override
                public Class<?> getColumnClass(int columnIndex) {
                    switch(columnIndex) {
                        case 4:
                            return Boolean.class;
                    }
                    return String.class;
                }
                @Override
                 public boolean isCellEditable(int row, int column) {
                   switch(column){
                       case 4:
                           return true;
                   default:
                       return false;
                   }
                 }
            };
            for (Showing s : showingsResult) {
                Object[] showingArray = {s.showingID, s.screen.name, s.imdbID, s.timestamp, new Boolean(false)};
                showingModel.addRow(showingArray);
            }
            JTable showingTable = new JTable(showingModel);
            showingsScrollPane.setViewportView(showingTable);
            showingsListPanel.add(showingsScrollPane);
            
            JButton removeScreenBtn = new JButton("Remove Selected");
            showingsListPanel.add(removeScreenBtn);
            
            removeScreenBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int rowCount = showingTable.getRowCount();
                    TableModel data = showingTable.getModel();
                    for (int i=0;i<rowCount;i++) {
                        int id = (Integer) data.getValueAt(i, 0);
                        boolean delete = (boolean) data.getValueAt(i, 4);
                        if (delete) {
                            cont.removeShowing(id);
                            render();
                        }
                    }
                }
            });
        
        //ADD ALL COMPONENTS
        add(movieSearchPanel);
        add(movieSearchResultsPanel);
        add(showingsListPanel);
        
    }
    
}
