package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MainView extends Stage {

    private MainModel model;
    private MainController controller;
    private Label label;

    public MainView(MainModel m, MainController c) {
        model = m;
        controller = c;
        TextField hours = new NumberTextField();
        TextField mins = new NumberTextField();
        TextField secs = new NumberTextField();
        hours.setPromptText("hh");
        mins.setPromptText("mm");
        secs.setPromptText("ss");
        hours.setPrefWidth(60);
        hours.setMaxWidth(60);
        hours.setMinWidth(60);
        mins.setPrefWidth(60);
        mins.setMaxWidth(60);
        mins.setMinWidth(60);
        secs.setPrefWidth(60);
        secs.setMaxWidth(60);
        secs.setMinWidth(60);

        Button start = new Button("Start");
        start.setPrefWidth(120);
        start.setMaxWidth(120);
        start.setMinWidth(120);
        start.setOnAction(e -> controller.increment());
        label = new Label();

        BorderPane layout = new BorderPane();
        DatePicker date = new DatePicker();
        HBox hBox = new HBox(10, hours, mins, secs, date, start, label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        MenuBar menuBar = new MyMenu();
        layout.setTop(menuBar);
        layout.setCenter(hBox);
        layout.setStyle("-fx-font: 24 Arial");
        Scene scene = new Scene(layout);
        this.sizeToScene();
        setScene(scene);
        show();
        model.addObserver(this);
    }

    public void update() {
        label.setText("Value: " + model.getValue());
    }
}
