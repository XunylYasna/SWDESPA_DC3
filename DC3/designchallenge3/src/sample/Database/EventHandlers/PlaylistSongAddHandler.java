package sample.Database.EventHandlers;


import sample.Database.DatabaseConnection;

import java.sql.*;

public class PlaylistSongAddHandler {

    PreparedStatement prepStatement;
    Connection myConn = DatabaseConnection.getDatabaseConn();
    Statement statement = null;
    ResultSet resultSet;

    public void addSongtoPlaylist(int songID, int playlistID) {

        String sql = "INSERT INTO songtoplaylist (songid, playlistid)\n" +
                "values (?, ?)";// insert insert user query here
        try {
            prepStatement = myConn.prepareStatement(sql);
            prepStatement.setInt(1, songID);
            prepStatement.setInt(2, playlistID);
            prepStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return;
    }
}
