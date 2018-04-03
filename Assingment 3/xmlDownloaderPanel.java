import javax.swing.*;
import java.awt.*;


public class xmlDownloaderPanel extends JPanel {

    public xmlDownloaderPanel(){
        createXMLPanel();
    }

    //Creates and displays the gui
    public void createXMLPanel() {

        //initialize variables
        xmlPanel = new JPanel();
        getAlbums = new JButton("Get Albums");
        showAlbums = new JTextArea();
        albumPane = new JScrollPane(showAlbums);
        xmlPanel.setLayout(new BorderLayout());

        //set size of window
        xmlPanel.setPreferredSize(new Dimension(800,500));
        albumPane.setPreferredSize(new Dimension(800,500));

        add.(xmlPanel);
        //add button and jtextarea
        xmlPanel.add(getAlbums,BorderLayout.CENTER);
        xmlPanel.add(albumPane,BorderLayout.SOUTH);


    }


    //Private members
    private JPanel xmlPanel;
    private JButton getAlbums;
    private JTextArea showAlbums;
    private JScrollPane albumPane;
}
