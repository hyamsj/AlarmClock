package alarmClock.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by pascal on 5/6/17.
 */
public class BinaryDBAdapter implements DataBaseAdapter {
    ReminderList reminders;
    String path = "reminders.ser";

    public ReminderList load() {
        //TODO Errorhandling
        this.reminders = new ReminderList();
        if (Files.exists(Paths.get(path))) {
            ArrayList<Reminder> reminderList;
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(path))) {
                //reminderList = (ArrayList<Reminder>) in.readObject();
                System.out.println("before Read");
                reminderList = (ArrayList<Reminder>) in.readObject();
                System.out.println("after Read");
                this.reminders = new ReminderList();
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

    public void save(ReminderList reminders) {
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
