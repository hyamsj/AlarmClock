package noSceneBuilder;

import javafx.collections.ObservableList;

import java.io.Serializable;

/**
 * Created by joni on 25/04/17.
 */
public class Model implements Serializable{

    private View view;
    Reminder reminder = new Reminder();

    public Model() {

    }

    public ObservableList<Reminder> getReminders() {

        reminder.getReminders();
        return reminder.getReminders();
    }

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
}
