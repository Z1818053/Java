/***************************
 * Matt Rycraft - z1818053
 * Tommy Franczak - z1796882
 *
 * CSCI 470
 *
 * Assignment 3
 *
 * Stores album information
 */

public class Album {

    public Album() {}

    // Sets the variables to the given Strings
    public Album(String album, String artist, String gen){
        albumName = album;
        artistName = artist;
        genre = gen;
    }

    // set and get album name
    public String getAlbumName() {
        return albumName; }
    public void setAlbumName( String newName ) {
        albumName = newName; }

    // set and get artist name
    public String getArtistName() {
        return artistName; }
    public void setArtistName( String newName ) {
        artistName = newName; }

    // set and get genre
    public String getGenre() {
        return genre; }
    public void setGenre( String newGenre) {
        genre = newGenre; }

    //private variables
    private String albumName;
    private String artistName;
    private String genre;
}