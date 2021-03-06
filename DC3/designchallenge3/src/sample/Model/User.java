package sample.Model;

import sample.Database.BuildTemp.PlaylistBuildTemp;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private String firstname;
    private String lastname;
    private String password;
    private String email;
    private String type;

    private ArrayList<Playlist> playlistList;

    public User(int userID, String username, String firstname, String lastname, String password, String email, String type) {
        this.userID = userID;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
        this.type = type;
        PlaylistBuildTemp playlistBuildTemp = new PlaylistBuildTemp();
        playlistList = playlistBuildTemp.getPlaylists(userID);
    }

    public void setPlaylistList(ArrayList<Playlist> playlistList) {
        this.playlistList = playlistList;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public ArrayList<Playlist> getPlaylistList() {
        return playlistList;
    }

    public String getType() {
        return type;
    }
}
