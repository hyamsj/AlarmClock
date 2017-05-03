package alarmClock.controller;

import alarmClock.model.Model;
import alarmClock.model.Reminder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private GridPane pane;
    @FXML
    private TableView<Reminder> reminderTable;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField descriptionField;
    @FXML
    private TextField timeField;
    @FXML
    private DatePicker datePickerField;
    @FXML
    private Button addButton;
    @FXML
    private Button rmButton;

    private Model model;
    private InputChecker helper = new InputChecker();

    private String subject;
    private String description;
    private LocalDateTime time;
    private LocalDate date;

    public void addButtonPressed() {
        if (!Objects.equals(subjectField.getText(), "") && !Objects.equals(timeField.getText(), "") && datePickerField.getValue() != null) {
            subject = subjectField.getText();
            description = descriptionField.getText();
            //TODO  controller may only accept parsable timeFild inputs, could do so by adding a LocalDateTimePicker
            //time = LocalDateTime.parse(timeField.getText());
            //TODO remove after the above is fixed
            time = LocalDateTime.MAX;
            date = datePickerField.getValue();
            model.addReminder(new Reminder(subject, description, time, date));
            subjectField.setText("");
            descriptionField.setText("");
            timeField.setText("");
            datePickerField.setChronology(null);
        }

    }

    public void rmButtonPressed() {
        ObservableList<Reminder> reminderSelected;
        //ObservableList<Reminder>  allReminders;
        //allReminders = reminderTable.getItems();
        reminderSelected = reminderTable.getSelectionModel().getSelectedItems();
        // allReminders.removeAll(reminderSelected);
        model.removeReminders(reminderSelected);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // timeField should only allow a pattern like this: HH:MM
        helper.checkTimeInput(timeField);


        reminderTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        Model model = null;
        try {
            model = new Model();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.model = model;
        model.bindData();
        reminderTable.setItems(model.getReminders());

    }

}
