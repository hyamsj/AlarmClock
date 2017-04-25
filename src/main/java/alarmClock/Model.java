package alarmClock;

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
public class Model implements Serializable{

    ObservableList<Reminder> data;

    public Model() throws IOException, ClassNotFoundException {
        String filename = "reminders.ser";
        if (Files.exists(Paths.get(filename))){
            dezerialize(filename);
        }
        else {
            this.data = FXCollections.observableArrayList();
        }
    }

    private void dezerialize(String path)throws IOException, ClassNotFoundException {
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(path))) {
            ArrayList<serializableReminder> alist = new ArrayList<>();
            alist = (ArrayList<serializableReminder>) in.readObject();
            ArrayList<Reminder> reminderList = new ArrayList<>();
            for (serializableReminder sr : alist) {
                reminderList.add(sr.getReminder());
//                System.out.println(value);
            }
            data = FXCollections.observableArrayList();
            data.addAll(reminderList);
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
       // ObservableList<Reminder> reminders = FXCollections.observableArrayList();
        //reminders.add(new Reminder("subject-comes-here","descritpion-comes-here","time",null));
        return data;


        //return reminders;
    }

    public ArrayList<serializableReminder> getEditableReminders() {
        ArrayList<serializableReminder> aList = new ArrayList<>();
        for (Reminder r : data){
            serializableReminder sr = r.getSerializable();
            aList.add(sr);
        }
        return aList;
    }


}
