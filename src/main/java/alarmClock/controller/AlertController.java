package alarmClock.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by joni on 16/05/17.
 */
public class AlertController implements Initializable{

    @FXML
    Button closeButton;
    Stage stage;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        this.stage = (Stage) closeButton.getScene().getWindow();
    }

    public void okButtonPressed(){
        stage.close();
    }

    public void addStage(Stage alertStage) {
        this.stage = alertStage;
    }
}
