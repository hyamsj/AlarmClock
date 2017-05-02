package alarmClock;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.time.LocalDate;
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
    @FXML
    private Label currentTimeLabel;

    private Model model;

    private String subject, description, time;
    private LocalDate date;

    public void addButtonPressed() {
        subject = subjectField.getText();
        description = descriptionField.getText();
        time = timeField.getText();
        date = datePickerField.getValue();
        model.addData(reminderTable.getItems(), subject, description, time, date);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Model model = null;
        try {
            model = new Model();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        this.model = model;
        model.bindData(this);
        reminderTable.setItems(model.getReminders());

    }

    public void save() {
        System.out.println("saving");
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("reminders.ser"))) {
            out.writeObject(model.getEditableReminders());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
