package alarmClock.model;

import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by pascal on 5/6/17.
 */
public class BinaryDBAdapter implements DataBaseAdapter {
    ObservableList<Reminder> reminders;
    String path  = "reminders.ser";

    public ObservableList<Reminder> load(){
        //TODO Errorhandling

        ArrayList<Reminder> reminderList=null;
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(path))) {
            reminderList = (ArrayList<Reminder>) in.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        reminders = FXCollections.observableArrayList();
        reminders.addAll(reminderList);
        return reminders;
    }

    public void save() {
        //TODO Errorhandling
        System.out.println("saving");
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(reminders);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
