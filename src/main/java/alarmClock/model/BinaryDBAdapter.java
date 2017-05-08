package alarmClock.model;

import com.sun.org.apache.regexp.internal.RE;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by pascal on 5/6/17.
 */
public class BinaryDBAdapter implements DataBaseAdapter {
    ObservableList<Reminder> reminders;
    String path  = "reminders.ser";

    public ObservableList<Reminder> load(){
        //TODO Errorhandling
        this.reminders = FXCollections.observableArrayList();
        if (Files.exists(Paths.get(path))) {
            ArrayList<Reminder> reminderList= new ArrayList<>();
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(path))) {
                //reminderList = (ArrayList<Reminder>) in.readObject();
                System.out.println("before Read");
                reminderList =(ArrayList<Reminder>) in.readObject();
                System.out.println("after Read");
                this.reminders = FXCollections.observableArrayList();
                System.out.println("reminders is FXCollections");
                this.reminders.addAll(reminderList);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return this.reminders;
    }

    public void save(ObservableList<Reminder>  reminders) {
        //TODO Errorhandling
        System.out.println("saving");
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(new ArrayList<>(reminders));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
