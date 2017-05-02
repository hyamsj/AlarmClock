package alarmClock.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable {

    ObservableList<Reminder> reminders;

    public Model() throws IOException, ClassNotFoundException {
        String filename = "reminders.ser";
        if (Files.exists(Paths.get(filename))) {
            deserialize(filename);
        } else {
            this.reminders = FXCollections.observableArrayList();
        }
    }

    private void deserialize(String path) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(path))) {
            ArrayList<serializableReminder> alist = (ArrayList<serializableReminder>) in.readObject();
            ArrayList<Reminder> reminderList = new ArrayList<>();
            for (serializableReminder sr : alist) {
                reminderList.add(sr.getReminder());
//                System.out.println(value);
            }
            reminders = FXCollections.observableArrayList();
            reminders.addAll(reminderList);
        }


    }

    public void addData(ObservableList<Reminder> data, String subject, String description, String time, LocalDate date) {
        this.reminders = data;
        data.add(new Reminder(subject, description, time, date));
    }

    public ObservableList<Reminder> getReminders() {
        return reminders;

    }

    public ArrayList<serializableReminder> getEditableReminders() {
        ArrayList<serializableReminder> aList = new ArrayList<>();
        for (Reminder r : reminders) {
            serializableReminder sr = r.getSerializable();
            aList.add(sr);
        }
        return aList;
    }

    public void bindData() {
        reminders.addListener((Observable obs) -> {
            System.out.println("something changed");
            save();
        });

    }

    public void save() {
        System.out.println("saving");
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("reminders.ser"))) {
            out.writeObject(getEditableReminders());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
