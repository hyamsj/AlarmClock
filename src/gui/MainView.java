package gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
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
        Button start = new Button("Start");
        start.setOnAction(e -> controller.increment());
        Button stop = new Button("Stop");
        stop.setOnAction(e -> controller.reset());
        label = new Label("Value: " + model.getValue());
        BorderPane layout = new BorderPane();
        HBox hBox = new HBox(10, start, stop, label);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(10));
        MenuBar menuBar = new MyMenu();
        layout.setTop(menuBar);
        layout.setCenter(hBox);
        layout.setStyle("-fx-font: 24 Arial");
        Scene scene = new Scene(layout, 600, 150);
        setScene(scene);
        show();

        model.addObserver(this);
    }

    public void update() {
        label.setText("Value: " + model.getValue());
    }
}
