package alarmClock;

import javafx.collections.ObservableList;

/**
 * Created by joni on 25/03/17.
 */
public class Model {

    ObservableList<Reminder> data;

    public Model getModel(){
        return this;
    }

    public void addData(ObservableList<Reminder> data, String subject, String description, String time) {
        this.data = data;
        data.add(new Reminder(subject, description, time, ""));
    }

    public ObservableList<Reminder> getData() {
        return data;
    }
}
