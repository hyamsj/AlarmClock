package alarmClock.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by pascal on 5/6/17.
 * Loads and saves the Reminder to the Database
 */
public class BinaryDBAdapter implements DataBaseAdapter {
    ReminderList reminders;
    String path = "reminders.ser";


    /**
     * Loads data from the database
     *
     * @return a list with all reminders
     */
    @Override
    public ReminderList load() {
        this.reminders = new ReminderList();
        if (Files.exists(Paths.get(path))) {
            ArrayList reminderArrayList;
            try (ObjectInputStream in =
                         new ObjectInputStream(new FileInputStream(path))) {
                //reminderList = (ArrayList<Reminder>) in.readObject();
                Object o = in.readObject();
                reminderArrayList = (ArrayList<Reminder>) o;
                ReminderList rl = new ReminderList();
                rl.addAll(reminderArrayList);
                reminders = rl;

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

    /**
     * Saves the database and overwrites the old one
     *
     * @param reminders all reminder that will be saved
     */
    @Override
    public void save(ReminderList reminders) {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(path))) {
            out.writeObject(reminders.getSerializable());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
