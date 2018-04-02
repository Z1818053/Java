import javax.swing.*;
import java.awt.*;


public class xmlDownloader extends JFrame {

    public xmlDownloader(){
        createAndShowGUI();
        setTitle("iTunes Store Albums");
        setSize(new Dimension(800,500));
        setVisible(true);
    }

    //Creates and displays the gui
    public void createAndShowGUI() {


        //initialize variables
        xmlPanel = new JPanel();
        menuBar = new JMenuBar();
        getAlbums = new JButton("Get Albums");
        showAlbums = new JTextArea();
        albumPane = new JScrollPane(showAlbums);
        Type = new JMenu("Type");
        Limit = new JMenu("Limit");
        Explicit = new JMenu("Explicit");
        setLayout(new BorderLayout());


        xmlPanel.setPreferredSize(new Dimension(800,500));
        albumPane.setPreferredSize(new Dimension(800,500));



        add(xmlPanel, BorderLayout.CENTER);
        //add menubar to frame
        menuBar.add(Type);
        menuBar.add(Limit);
        menuBar.add(Explicit);
        add(menuBar, BorderLayout.NORTH);
        xmlPanel.add(getAlbums,BorderLayout.CENTER);
        xmlPanel.add(albumPane,BorderLayout.SOUTH);





        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();


    }

    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new xmlDownloader();
            }
        });

    }

    //Private members
    private JFrame frame;
    private JPanel xmlPanel;
    private JMenuBar menuBar;
    private JButton getAlbums;
    private JTextArea showAlbums;
    private JScrollPane albumPane;
    private JMenu Type;
    private JMenu Limit;
    private JMenu Explicit;

}
