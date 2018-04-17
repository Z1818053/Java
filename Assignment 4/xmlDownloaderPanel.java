/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 4
 *
 * This program will handle events from the get albums button and display the information downloaded
 * from the url
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class xmlDownloaderPanel extends JPanel implements ActionListener, AlbumDisplay {

    // Default constructor
    public xmlDownloaderPanel() {
        // Sets the layout to BorderLayout
        super();
        setLayout(new BorderLayout());

        // Create the "Get Album" button and add an ActionListener
        getAlbums = new JButton("Get Albums");
        getAlbums.addActionListener(this);

        // Create the text area to display the album information
        albumInfo = new JTextArea(10, 60);
        albumInfo.setEditable(false);

        // Create the panel to hold the getAlbums button.
        buttonPanel.add(getAlbums);

        // embed textarea into scrollpane
        AlbumHolder = new JScrollPane(albumInfo);

        // add button and scrollpane 
        add(buttonPanel, BorderLayout.PAGE_START);
        add(AlbumHolder, BorderLayout.CENTER);
    }

    // calls the download function but clears 
    @Override
    public void actionPerformed(ActionEvent e) {
        albumInfo.setText("");
        download();
    }

    // This method will construct the url string to download from and pass it into a new xmlDownloadTask then execute
    public void download() {
        String html = String.format("https://rss.itunes.apple.com/api/v1/us/itunes-music/%s/all/%d/%s.atom",
                feedType, resultLimit, explicit ? "explicit" : "non-explicit");

        task = new xmlDownloadTask( this, html );

        // disables getablums button while task running
        task.addPropertyChangeListener(event -> {
            switch ((SwingWorker.StateValue) event.getNewValue())
            {
                case STARTED:
                    getAlbums.setEnabled(false);
                    break;

                case DONE:
                    getAlbums.setEnabled(true);
                    break;
            }
        });

        task.execute();
    }

    // set and get for type
    public String getType(){
        return feedType; }
    public void setType(String newFeedType){
        feedType = newFeedType; }

    // set and get for limit
    public int getLimit(){
        return resultLimit; }
    public void setLimit(int newLimit){
        resultLimit = newLimit; }

    // set and get for explicit
    public boolean getExplicit(){
        return explicit; }
    public void setExplicit(boolean newExpl){
        explicit = newExpl; }

    // Adds the album information
    public void displayAlbums( Album album ) {
        albumInfo.append(album.getAlbumName());
        albumInfo.append("; ");
        albumInfo.append(album.getArtistName());
        albumInfo.append("; ");
        albumInfo.append(album.getGenre());
        albumInfo.append("\n");
    }

    //private variables
    private JButton getAlbums;
    private JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    private JScrollPane AlbumHolder;
    private JTextArea albumInfo;
    private xmlDownloadTask task;
    private String feedType = "new-music";
    private int resultLimit = 10;
    private boolean explicit = true;
}
