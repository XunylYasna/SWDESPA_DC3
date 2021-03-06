package sample.Controllers.EventHandlers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import sample.Database.EventHandlers.SongAddHandler;
import sample.Model.Song;
import sample.Model.User;

import java.io.File;

public class addSongController {
    @FXML
    public Label message;

    @FXML
    private JFXButton songCBtn;

    @FXML
    private JFXButton uploadFBtn;

    @FXML
    private JFXButton confirmBtn;

    @FXML
    private JFXTextField titleTf;
    @FXML
    private JFXTextField genreTf;

    @FXML
    private Labeled songFileLbl;
    @FXML
    private Labeled statusLbl;


    File coverFile = null;
    File songFile = null;

    FileChooser fileChooser = new FileChooser();

    SongAddHandler songAddHandler = new SongAddHandler();

    Song songAdded = null;

    User artist;

    @FXML
    void chooseSongCover(ActionEvent event){
        coverFile = fileChooser.showOpenDialog(null);
        Image cover = new Image(coverFile.toURI().toString());
        BackgroundImage myBI= new BackgroundImage(cover,
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                new BackgroundSize(115,115,false,false,false,false));
        songCBtn.setBackground(new Background(myBI));
        songCBtn.setText("");
    }

    @FXML
    void confirm(ActionEvent event){
        String songTitle = titleTf.getText();
        String artist = this.artist.getUsername();
        String genre = genreTf.getText();

//        if(coverFile == null){
//            coverFile = new File("defaultCover.png");
//        }

        songAdded = songAddHandler.addSong(songTitle, artist, genre, coverFile, songFile);
        if(songAdded != null){
            statusLbl.setText("Song added. You may now close the window");
        }
    }

    @FXML
    void uploadSongFile(ActionEvent event){
        songFile = fileChooser.showOpenDialog(null);
        songFileLbl.setText(songFile.getName());
    }


    public Song getSongAdded(){
        return songAdded;
    }

    public void setArtist(User artist){
        this.artist = artist;
    }
}
