package alarmClock.model;

import alarmClock.model.filtering.CriteriaTester;
import alarmClock.notification.JavaFxNotification;
import alarmClock.notification.Notification;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;


/**
 * Created by joni on 24/03/17.
 * The heart of the program. Everything uses reminder objects.
 * Reminders store the data such as subject, description, date and optionally tags.
 */
public class Reminder implements Serializable {

    private String subject = "";
    private String description = "";
    private LocalDateTime date;
    //could be a Collection too.
    private Collection<String> tags = new ArrayList<>();

    /**
     * Constructor
     *
     * @param subject     The subject of the reminder
     * @param description The description of the reminder
     * @param date        The date of the reminder
     */

    public Reminder(String subject, String description, LocalDateTime date) {
        setSubject(subject);
        setDescription(description);
        setDate(date);
    }

    /**
     * Constructor with tags
     *
     * @param subject     The subject of the reminder
     * @param description The description of the reminder
     * @param date        The date of the reminder
     * @param tags        The tags of the reminder
     */
    public Reminder(String subject, String description, LocalDateTime date, Set<String> tags) {
        setSubject(subject);
        setDescription(description);
        setDate(date);
        setTags(tags);
    }

    /**
     * Returns the tags of the reminder
     *
     * @return a Collection with the  tags of the reminder
     */
    public Collection<String> getTags() {
        return tags;
    }

    /**
     * Sets the tags of the reminder
     *
     * @param tags The tags of the reminder
     */
    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    /**
     * Returns the tags property of the reminder, this is required by JavaFx
     *
     * @return the tag property of the reminder
     */
    public SimpleObjectProperty<Collection<String>> getTagsProperty() {
        return new SimpleObjectProperty<>(tags);
    }

    /**
     * Adds a tag to the reminder
     *
     * @param tag Adds the tag of the reminder
     */
    public void addTag(String tag) {
        tags.add(tag);
    }

    /**
     * Removes tag from the reminder
     *
     * @param tag The tag that should get removed
     */
    public void removeTag(String tag) {
        tags.remove(tag);
    }

    /**
     * Returns the subject of the reminder
     *
     * @return The subject of the reminder
     */
    public String getSubject() {
        return subject;
    }

    /**
     * Sets the subject of the reminder
     *
     * @param subject the subject that the reminder should have
     */
    private void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Returns the subjectProperty, is required by JavaFx
     *
     * @return The subjectProperty
     */
    public SimpleStringProperty getSubjectProperty() {
        return new SimpleStringProperty(subject);
    }

    /**
     * Returns the description of the reminder
     *
     * @return the description of the reminder
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the reminder
     *
     * @param description the description that the reminder should have
     */
    private void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the descriptionProperty of the reminder, is required by JavaFx
     *
     * @return the descriptionProperty of the reminder
     */
    public SimpleStringProperty getDescriptionProperty() {
        return new SimpleStringProperty(description);
    }

    /**
     * Returns the date of the reminder
     *
     * @return the date of the reminder
     */
    public LocalDateTime getDate() {
        return date;
    }

    /**
     * Sets the date of the reminder
     *
     * @param date the date that the reminder should have
     */
    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    /**
     * Returns the dateProperty of the Reminder, is required by
     *
     * @return the dateProperty of the Reminder
     */
    public SimpleObjectProperty<LocalDateTime> getDateProperty() {
        return new SimpleObjectProperty<>(date);
    }

    /**
     * Equals method
     *
     * @param object any object
     * @return true if they are the same, false if they are not
     */
    @Override
    public boolean equals(Object object) {
        Reminder reminder;
        if (object instanceof Reminder) {
            reminder = (Reminder) object;
            boolean isSame =
                    reminder.getDescription().equals(this.getDescription())
                            && reminder.getSubject().equals(this.getSubject())
                            && reminder.getDate().equals(this.getDate());

            return isSame;
        } else {
            return false;
        }

    }

    /**
     * Creates hashes of the reminder object
     *
     * @return the hash of the reminder object
     */
    @Override
    public int hashCode() {
        int hash = 1;
        hash *= description.hashCode() * 13;
        hash *= subject.hashCode() * 17;
        hash *= date.hashCode() * 11;
        return hash;
    }


    /**
     * Intended for debugging
     *
     * @return the String value of this reminder object
     */
    @Override
    public String toString() {
        String out;
        out = "Subject: " + this.getSubject() + "\n";
        out += "Description: " + this.getDescription() + "\n";
        out += "Date: " + this.getDate() + "\n";
        return out;
    }


    /**
     * Returns true if the criteria is fulfilled
     *
     * @param criteria of the reminder
     * @return boolean
     */
    public boolean notifyIf(CriteriaTester criteria) {
        if (criteria.isTrue(this)) {
            this.doNotify();
            return true;
        }
        return false;
    }

    /**
     * Loops through all criteria checks if any are fulfilled. Only returns true if all are fulfilled.
     * If all are fulfilled, doNotify gets called
     *
     * @param criterias List of criterias for the reminder
     * @return boolean, returns true only if all are fulfilled
     */
    public boolean notifyIf(Collection<CriteriaTester> criterias) {
        Boolean allTrue = true;
        for (CriteriaTester criteria : criterias) {
            allTrue &= criteria.isTrue(this);
        }
        if (allTrue) {
            this.doNotify();
        }
        return allTrue;
    }

    /**
     * Loops through all criteria checks if any are fulfilled. Only returns true if all are fulfilled.
     *
     * @param criterias List of criterias for the reminder
     * @return boolean, returns true only if all are fulfilled
     */
    public boolean meetsCriteria(Collection<CriteriaTester> criterias) {
        Boolean allTrue = true;
        for (CriteriaTester criteira : criterias) {
            allTrue &= criteira.isTrue(this);
        }
        return allTrue;
    }


    /**
     * Every notification in the list is given this reminder and sends the notification
     */
    public void doNotify() {
        //gets the notification Types from the config Reader / File
        ArrayList<Notification> notificationTypes = new ConfigReader().getNotificationTypes();

        for (Notification notification : notificationTypes) {
            if (notification instanceof JavaFxNotification) {
                JavaFxNotification alert = new JavaFxNotification(this);
                alert.setReminder(this);
                alert.send();
            } else {
                notification.setReminder(this);
                notification.send();
            }

        }
    }
}
