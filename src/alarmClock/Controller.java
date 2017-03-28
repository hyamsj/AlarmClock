package alarmClock;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {
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
    @FXML
    private Label currentTimeLabel;

    private Model model;

    private String subject, description, time, date;

    // TODO add Model via constructor Parameters. JavaFX makes this a bit difficult
    public Controller() {
        Model model = new Model();
        this.model = model.getModel();
    }

    public void addButtonPressed() {
        subject = subjectField.getText();
        description = descriptionField.getText();
        time = timeField.getText();
        model.addData(reminderTable.getItems(), subject, description, time);
        subjectField.setText("");
        descriptionField.setText("");
        timeField.setText("");
        datePickerField.setChronology(null);

    }

    public void rmButtonPressed() {
        ObservableList<Reminder> reminderSelected, allReminders;
        allReminders = reminderTable.getItems();
        reminderSelected = reminderTable.getSelectionModel().getSelectedItems();
        reminderSelected.forEach(allReminders::remove);
    }

}
