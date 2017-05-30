package alarmClock.controller;

import alarmClock.model.Model;
import alarmClock.model.Reminder;
import alarmClock.model.ReminderList;
import javafx.beans.binding.BooleanBinding;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * The Controller according to the MVC Pattern. It gets the FXML objects from the mainWindow.fxml
 */
public class Controller implements Initializable {
    @FXML
    private TableView<Reminder> reminderTable;
    @FXML
    private TextField subjectField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DateTimePicker datePickerField;
    @FXML
    private Button addButton;

    private Model model;
    private String subject;
    private String description;
    private LocalDateTime date;

    /**
     * Handles addButton clicked event. Empties the fields after the button ist pressed
     * Adds a reminder to the reminderlist, which is located in the model
     */
    @FXML
    public void addButtonPressed() {
        if (!Objects.equals(subjectField.getText(), "") && datePickerField.getValue() != null) {
            subject = subjectField.getText();
            description = descriptionField.getText();
            date = datePickerField.getDateTimeValue();
            model.addReminder(new Reminder(subject, description, date));
            subjectField.setText("");
            descriptionField.setText("");
            datePickerField.setChronology(null);
        }

    }

    /**
     * Handles removeButton clicked event.
     * Allows multiple items in the table to be selected and removed at the same time
     * Removes reminder from the reminder list
     */
    @FXML
    public void rmButtonPressed() {
        ReminderList reminderSelected;
        reminderSelected = new ReminderList(reminderTable.getSelectionModel().getSelectedItems());
        model.removeReminders(reminderSelected);
    }

    /**
     * Serves as the constructor. Is called before the JavaFX components are created.
     * Doing this in the constructor would not work, as JavaFX components are loaded afterwards
     *
     * @param location is not used. Requested by JavaFX
     * @param resources is not used. Requested by JavaFX
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        reminderTable.setEditable(true);

        //Binding for disabling addButton if input is invalid
        BooleanBinding addBinding = subjectField.textProperty().isNotEmpty().and(datePickerField.valueProperty().isNotNull());
        addButton.disableProperty().bind(addBinding.not());

        //Allows to select multiple items in the table
        reminderTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        // Initialize the Model
        Model model = null;
        try {
            model = new Model();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.model = model;

        // Creates binding with table that overwrites the DB, if anything has changed
        model.bindData();

        // Puts items in the table
        reminderTable.setItems(model.getReminders());

    }

}
