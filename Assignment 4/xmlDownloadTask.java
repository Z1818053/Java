/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 4
 *
 * Creates the task function that downloads info
 */

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class xmlDownloadTask extends SwingWorker<ArrayList<Album>, Album> {

    // Constructor sets display, urlStr, and albums
    xmlDownloadTask(AlbumDisplay setdisplay, String setUrl) {
        display = setdisplay;
        urlStr = setUrl;
        albums = new ArrayList<>();
    }

    // Creates background thread
    @Override
    public ArrayList<Album> doInBackground() {
        String xmlStr;
        HttpURLConnection conn = null;

        try {
            URL url = new URL(urlStr);

            // Opens the connection to the given URL
            conn = (HttpURLConnection) url.openConnection();

            // Tries to get the information.
            conn.setRequestMethod("GET");

            // Once it is able to connect then it will start building the string and save it to the xmlString
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                StringBuilder xmlResponse = new StringBuilder();

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));

                String Line;

                while ((Line = input.readLine()) != null)
                    xmlResponse.append(Line);

                xmlStr = xmlResponse.toString();

                // calls the sacParser to pars the resulting xml string
                try {
                    SAXParserFactory factory = SAXParserFactory.newInstance();
                    SAXParser saxParser = factory.newSAXParser();

                    saxParser.parse(new InputSource(new ByteArrayInputStream(
                            xmlStr.getBytes("utf-8"))), new AlbumHandler());
                } catch (ParserConfigurationException e) {
                    System.out.println("Error: ParserConfigurationException thrown");
                } catch (SAXException e) {
                    System.out.println("Error: SAXException thrown");
                }
                input.close();
            }
        } catch (MalformedURLException e) {
            System.out.println("Error: Invalid URL.");
        } catch (IOException e) {
            System.out.println("Error: could not open connection.");
        }
        // Makes sure that the connection is disconnected
        finally {
            if (conn != null)
                conn.disconnect();
        }

        // Returns albums
        return albums;
    }

    // Calls the displayAlbums
    @Override
    protected void process(List<Album> blocks) {
        for (Album album : blocks)
            display.displayAlbums(album);
    }

    // This class parses the url string and adds a new album based on the information given
    public class AlbumHandler extends DefaultHandler {
        private boolean Name = false;
        private boolean Artist = false;
        private boolean Category = true;

        private String name, artist, label;

        // Checks the opening tag and set the matching boolean to true if it is found.
        public void startElement(String URL, String LocalName, String equal, Attributes attributes) {
            if (equal.equalsIgnoreCase("im:name")) {
                Name = true;
                name = "";
            }

            if (equal.equalsIgnoreCase("im:artist")) {
                Artist = true;
                artist = "";
            }

            //sets the label attrivute of the category, sets the category to flase to ensure it is the correct infomration
            if (equal.equalsIgnoreCase("category") && Category) {
                label = attributes.getValue("label");
                Category = false;
            }
        }

        // Sets the name or artist depending on which tag was found
        public void characters(char ch[], int start, int length) {
            if (Name)
                name = name + new String(ch, start, length);

            if (Artist)
                artist = artist + new String(ch, start, length);
        }

        // After finding the ending tag sets the matching boolean to false
        public void endElement(String uri, String localName, String equal) {
            if (equal.equalsIgnoreCase("im:name"))
                Name = false;

            if (equal.equalsIgnoreCase("im:artist"))
                Artist = false;

            // If it is the end of the entry then set firstCat to true, create a new album, and publish the album
            if (equal.equalsIgnoreCase("entry")) {
                Category = true;
                Album album = new Album(name, artist, label);
                publish(album);
            }
        }
    }

    //private variables
    private String urlStr;
    private AlbumDisplay display;
    private ArrayList<Album> albums;
}

// An interface for displayAlbum
interface AlbumDisplay
{
    void displayAlbums(Album album);
}
