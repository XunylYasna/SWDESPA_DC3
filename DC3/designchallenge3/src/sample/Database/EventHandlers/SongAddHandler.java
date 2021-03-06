package sample.Database.EventHandlers;

import sample.Database.DatabaseConnection;
import sample.Model.Song;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;

public class SongAddHandler {

    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public Song addSong(String title, String artist, String genre, String album, File photo, File song) {
// Check yung same parameter names sa titles ng mga columns for songhub.song (Possible error??)
//      temp
        FileInputStream input;
        String sql = "INSERT INTO song (title, artist, genre, album, albumcover, songblob)\n" +
                "values (?, ?, ?, ?, ?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, title);
            prepStatement.setString(2, artist);
            prepStatement.setString(3, genre);
            prepStatement.setString(4, album );
            if(photo != null){
                 input = new FileInputStream(photo);
                prepStatement.setBinaryStream(5,input);
            }
            else{
                prepStatement.setBinaryStream(5,null);
            }
            if(song != null){
                input = new FileInputStream(song);
                prepStatement.setBinaryStream(6,input);
            }
            else{
                prepStatement.setBinaryStream(6,null);
            }
            prepStatement.execute();

            statement = myConn.createStatement();
            sql = "Select * from song where idsong=LAST_INSERT_ID();";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);

            int songID = 0;
            String newSongTitle = "error";
            String newArtist = "error";
            String newAlbum = "error";
            String newGenre = "error";

            while (resultSet.next()){
                songID = resultSet.getInt("idsong");
                newSongTitle = resultSet.getString("MusicTitle");
                newArtist = resultSet.getString("Artist");
                newAlbum = resultSet.getString("Genre");
                newGenre = resultSet.getString("AlbumBuilder");
            }


            return new Song(songID, newSongTitle,newArtist, newAlbum, newGenre);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Song addSong(String songTitle, String artist, String genre, File coverFile, File songFile) {
        FileInputStream input;
        String sql = "INSERT INTO song (title, artist, genre, album, albumcover, songblob, date_added)\n" +
                "values (?, ?, ?, ?, ?, ?,?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setString(1, songTitle);
            prepStatement.setString(2, artist);
            prepStatement.setString(3, genre);
            prepStatement.setString(4, null );
            if(coverFile != null){
                input = new FileInputStream(coverFile);
                prepStatement.setBinaryStream(5,input);
            }
            else{
                prepStatement.setBinaryStream(5,null);
            }
            if(songFile != null){
                input = new FileInputStream(songFile);
                prepStatement.setBinaryStream(6,input);
            }
            else{
                prepStatement.setBinaryStream(6,null);
            }
            prepStatement.setDate(7, new Date(new java.util.Date().getTime()));
            prepStatement.execute();

            statement = myConn.createStatement();
            sql = "Select * from song where idsong = LAST_INSERT_ID();";
            statement = myConn.createStatement();
            resultSet = statement.executeQuery(sql);

            int songID = 0;
            String newSongTitle = "error";
            String newArtist = "error";
            String newAlbum = "error";
            String newGenre = "error";

            while (resultSet.next()){
                songID = resultSet.getInt("idsong");
                newSongTitle = resultSet.getString("title");
                newArtist = resultSet.getString("artist");
                newAlbum = resultSet.getString("album");
                newGenre = resultSet.getString("genre");
            }


            return new Song(songID, newSongTitle,newArtist, newAlbum, newGenre);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}
