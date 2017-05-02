package alarmClock.controller;

import javafx.scene.control.TextField;

/**
 * Created by joni on 02/05/17.
 */
public class InputChecker {

    public void checkTimeInput(TextField timeField) {
        timeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                timeField.setText(newValue.replaceAll("[^\\d:]", ""));
            }
            if (timeField.getText().length() > 5) {
                timeField.setText(timeField.getText().substring(0, 5));
            }
            if (newValue.matches("\\d{4}")) {
                String[] input = newValue.split("");
                timeField.setText(String.format("%s%s:%s%s", input[0], input[1], input[2], input[3]));
            }
        });
    }
}
