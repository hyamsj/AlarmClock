package alarmClock.model;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by joni on 25/03/17.
 */
public class Model implements Serializable {

    ObservableList<Reminder> reminders;
    private Poller p;

    public Model() throws IOException, ClassNotFoundException{
        String filename = "reminders.ser";
        if (Files.exists(Paths.get(filename))) {
            deserialize(filename);
        } else {
            this.reminders = FXCollections.observableArrayList();
        }

    }


    private void deserialize(String path) throws IOException, ClassNotFoundException {
        ArrayList<Reminder> reminderList;
         try (ObjectInputStream in =
         new ObjectInputStream(new FileInputStream(path))) {
             reminderList = (ArrayList<Reminder>) in.readObject();
        }
        reminders = FXCollections.observableArrayList();
        reminders.addAll(reminderList);
    }

    public void addReminder(Reminder reminder) {
        //this.reminders = data;
        reminders.add(reminder);
    }

    public ObservableList<Reminder> getReminders() {
        //TODO return only a copy of reminders instead of a reference
        return reminders;
    }

    private ArrayList<Reminder> getEditableReminders() {
        //TODO change return value to Observable<Reminder> ?
        ArrayList<Reminder> aList = new ArrayList<>();
        for (Reminder r : reminders) {
            aList.add(r);
        }
        return aList;
    }

    public void bindData() {
        reminders.addListener((Observable obs) -> {
            System.out.println("something changed");
            save();
        });

    }

    private void save() {
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

    public void removeReminders(ObservableList<Reminder> reminderSelected) {
         reminders.removeAll(reminderSelected);
    }

    public void removeReminder(Reminder reminder) {
        reminders.remove(reminder);
    }
}
