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
//    private LocalDate date;

    /**
     * Handles addButton clicked event. Name is defined in mainWindow.fxml
     */
    @FXML
    public void addButtonPressed() {
        if (!Objects.equals(subjectField.getText(), "") && datePickerField.getValue() != null) {
            subject = subjectField.getText();
            description = descriptionField.getText();
            //TODO  controller may only accept parsable timeFild inputs, could do so by adding a LocalDateTimePicker
            //date = LocalDateTime.parse(timeField.getText());
            //TODO remove after the above is fixed
            LocalDateTime later = LocalDateTime.now().plusMinutes(3);
            date = datePickerField.getDateTimeValue();
            model.addReminder(new Reminder(subject, description, date));
            subjectField.setText("");
            descriptionField.setText("");
            datePickerField.setChronology(null);
        }

    }

    /**
     * Handles removeButton clicked event. Name is defined in mainWindow.fxml
     * Allows multiple items in the table to be selected and removed at the same time
     */
    @FXML
    public void rmButtonPressed() {
        ReminderList reminderSelected;
        //ObservableList<Reminder>  allReminders;
        //allReminders = reminderTable.getItems();
        reminderSelected = new ReminderList(reminderTable.getSelectionModel().getSelectedItems());
        // allReminders.removeAll(reminderSelected);
        model.removeReminders(reminderSelected);
    }

    //TODO remove?
    public void undoButtonPressed() {
        model.undo();
        System.out.printf("undo");
    }

    //TODO remove?
    public void redoButtonPressed() {
        System.out.println("redo");
        model.redo();
    }

    /**
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Binding for addButton
        BooleanBinding addBinding = subjectField.textProperty().isNotEmpty().and(datePickerField.valueProperty().isNotNull());
        addButton.disableProperty().bind(addBinding.not());

        reminderTable.getSelectionModel().setSelectionMode(
                SelectionMode.MULTIPLE
        );

        Model model = null;
        try {
            model = new Model();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.model = model;
        model.bindData();
        reminderTable.setItems(model.getReminders());

    }

}
