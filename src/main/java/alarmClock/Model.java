package alarmClock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;

/**
 * Created by joni on 25/03/17.
 */
public class Model {

    ObservableList<Reminder> data;

    public Model getModel(){
        return this;
    }

    public void addData(ObservableList<Reminder> data, String subject, String description, String time,LocalDate date) {
        this.data = data;
        data.add(new Reminder(subject, description, time, date));
    }

    public ObservableList<Reminder> getData() {
        return data;
    }

    public ObservableList<Reminder> getReminders() {
        ObservableList<Reminder> reminders = FXCollections.observableArrayList();
        reminders.add(new Reminder("subject-comes-here","descritpion-comes-here","time",null));
        return reminders;
    }
}
