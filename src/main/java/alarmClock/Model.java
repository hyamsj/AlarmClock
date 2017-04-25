package alarmClock;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable{

    ObservableList<Reminder> data;

    public Model() throws IOException, ClassNotFoundException {
        String filename = "reminders.ser";
        if (Files.exists(Paths.get(filename)))
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(filename))) {
//                data = (ObservableList<Reminder>) in.readObject();
                ArrayList<Reminder> alist = new ArrayList<>();
                alist = (ArrayList<Reminder>) in.readObject();
                data.addAll(alist);
//                System.out.println(value);
            }
        else {
            this.data = FXCollections.observableArrayList();
        }
    }


    public void addData(ObservableList<Reminder> data, String subject, String description, String time, LocalDate date) {
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

    public ArrayList<Reminder> getEditableReminders() {
        ArrayList<Reminder> aList = new ArrayList<>();
        for (Reminder r : data){
            aList.add(r);
        }
        return aList;
    }
}
