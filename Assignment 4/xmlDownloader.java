/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 4
 *
 * This program will download data from a url, parse it and display it
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// class: xmlDownloader
// Creates a xml interface with music pulled.
// Also adds a bouncing ball panel.
public class xmlDownloader extends JFrame implements ActionListener {

    // Constructor for the JFrame
    private xmlDownloader(String title) {
        // Set the title
        super(title);
    }

    //adds borderlayout to the frame and sets it to visible
    private void createAndShowGUI() {
        setLayout( new BorderLayout() );
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        createMenu();

        // Creates the xmlPanel and ballPanel
        xmlPanel = new xmlDownloaderPanel();
        ballPanel = new BouncingBallPanel();

        // Appends the panels to the frame
        add(xmlPanel, BorderLayout.CENTER);
        add(ballPanel, BorderLayout.LINE_END);

        pack();
        setVisible(true);
    }

    //Creates the menu and its items with functionality
    private void createMenu() {
        // Create type menu
        menu = new JMenu("Type");
        group = new ButtonGroup();

        // Create new music, recent releases and top albums entry along with actionListeners
        menuItem = new JRadioButtonMenuItem("New Music");
        menuItem.setSelected(true);
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Recent Releases");
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("Top Albums");
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);

        //add type to menu
        menuBar.add(menu);

        // Create limit menu
        menu = new JMenu("Limit");
        group = new ButtonGroup();

        // Create 10, 25, 50, and 100 entry along with actionlistener
        menuItem = new JRadioButtonMenuItem("10");
        menuItem.setSelected(true);
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("25");
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("50");
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("100");
        menuItem.addActionListener(this);
        group.add(menuItem);
        menu.add(menuItem);

        //add limit to menu
        menuBar.add(menu);

        // Create explicit menu
        menu = new JMenu("Explicit");
        group = new ButtonGroup();

        // Create yes and no entry along with action listener
        menuItem = new JRadioButtonMenuItem("Yes");
        menuItem.addActionListener(this);
        menuItem.setSelected(true);
        group.add(menuItem);
        menu.add(menuItem);
        menuItem = new JRadioButtonMenuItem("No");
        menuItem.addActionListener(this);
        menuItem.setSelected(true);
        group.add(menuItem);
        menu.add(menuItem);

        //add explicit to menu
        menuBar.add(menu);

        // sets the JMenuBar to the menuBar.
        setJMenuBar(menuBar);
    }

    //This function will find out what the menu items are selected as and set the variables accordingly for use in
    // the url
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand())
        {
            case "New Music":
                xmlPanel.setType("new-music");
                break;

            case "Recent Releases":
                xmlPanel.setType("recent-releases");
                break;

            case "Top Albums":
                xmlPanel.setType("top-albums");
                break;

            case "10":
                case "25":
                    case "50":
                        case "100":
            xmlPanel.setLimit(Integer.parseInt(e.getActionCommand()));
            break;

            case "Yes":
                case "No":
            xmlPanel.setExplicit(Boolean.parseBoolean(e.getActionCommand()));
            break;
        }
    }

    // The main method will create the frame and call the createAndShowGUI to display it
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            xmlDownloader frame = new xmlDownloader("iTunes Store Albums");
            frame.createAndShowGUI();

        });
    }


    //Private members
    private xmlDownloaderPanel xmlPanel;
    private BouncingBallPanel ballPanel;
    private JMenuBar menuBar =  new JMenuBar();
    private JMenu menu;
    private JRadioButtonMenuItem menuItem;
    private ButtonGroup group;

}
