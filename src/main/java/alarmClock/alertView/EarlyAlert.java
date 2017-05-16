package alarmClock.alertView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * Created by joni on 16/05/17.
 */
public class EarlyAlert extends Stage{

    Label label;

    public EarlyAlert() {
        label = new Label("Hello: ");
        Button okButton = new Button("Ok");
        okButton.setOnAction(e -> {
            this.close();
        });
        HBox pane = new HBox(10, okButton, label);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(10));
        Scene scene = new Scene(pane, 200, 50);
        setScene(scene);
        show();
    }
}
