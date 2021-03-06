package sample.Controllers.EventHandlers;


import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import sample.Database.EventHandlers.PlayListAddHandler;
import sample.Model.Playlist;

public class addPlaylistController {

    @FXML
    private JFXTextField playlistNameTf;
    @FXML
    private JFXTextField descriptionTf;
    @FXML
    private Labeled statusLbl;

    private int userID;
    PlayListAddHandler playListAddHandler = new PlayListAddHandler();
    Playlist playlistadded;


    @FXML
    void confirm(ActionEvent event){
        String playlistName = playlistNameTf.getText();
        String description = descriptionTf.getText();

        playlistadded = playListAddHandler.addPlaylist(playlistName,description,userID);

        if(playlistadded != null){
            statusLbl.setText("Playlist added. You may now close the window");
        }
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }


    public Playlist getPlaylistadded() {
        return playlistadded;
    }
}
