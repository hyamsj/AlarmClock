package noSceneBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Created by joni on 25/04/17.
 */
public class Model implements Serializable{

    private View view;
    Reminder reminder = new Reminder();
    ObservableList<Reminder> data = FXCollections.observableArrayList();


    public void addObserver(View view) {
        this.view = view;

    }

    public void addNewReminder(Reminder reminder) {
        view.getTable().getItems().add(reminder);
        view.getDateInput().clear();
        view.getDescriptionInput().clear();
        view.getSubjectInput().clear();
        view.getTimeInput().clear();
    }

    public void removeReminder() {
        ObservableList<Reminder> reminderSelected, allReminders;
        allReminders = view.getTable().getItems();
        reminderSelected = view.getTable().getSelectionModel().getSelectedItems();
        reminderSelected.forEach(allReminders::remove);
    }

    public ObservableList<Reminder> getReminders() {
        this.data = FXCollections.observableArrayList();
        return data;
    }


}
