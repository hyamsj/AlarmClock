package alarmClock.model;

import alarmClock.notification.Notification;
import alarmClock.model.filtering.CriteriaTester;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
//import java.util.Set;

/**
 * Created by joni on 24/03/17.
 */
public class Reminder implements Serializable {

    private String subject = "";
    private String description = "";
    private LocalDateTime date;
    //could be a Collection too.
    private Collection<String> tags = new ArrayList<>();

    /**
     * @param subject
     * @param description
     * @param date
     */
    public Reminder(String subject, String description, LocalDateTime date) {
        setSubject(subject);
        setDescription(description);
        setDate(date);
    }

    public Reminder(String subject, String description, LocalDateTime date, Set<String> tags) {
        setSubject(subject);
        setDescription(description);
        setDate(date);
        setTags(tags);
    }

    public Collection<String> getTags() {
        return tags;
    }

    public SimpleObjectProperty<Collection<String>> getTagsProperty() {
        return new SimpleObjectProperty<Collection<String>>(tags);
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public void removeTag(String tag) {
        tags.remove(tag);
    }

    public String getSubject() {
        return subject;
    }

    public SimpleStringProperty getSubjectProperty() {
        return new SimpleStringProperty(subject);
    }


    public String getDescription() {
        return description;
    }

    public SimpleStringProperty getDescriptionProperty() {
        return new SimpleStringProperty(description);
    }

    public LocalDateTime getDate() {
        return date;
    }

    public SimpleObjectProperty<LocalDateTime> getDateProperty() {
        return new SimpleObjectProperty<>(date);
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        Reminder r;
        if (o instanceof Reminder) {
            r = (Reminder) o;
            boolean isSame =
                    r.getDescription().equals(this.getDescription())
                            && r.getSubject().equals(this.getSubject())
                            && r.getDate().equals(this.getDate());

            return isSame;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= description.hashCode() * 13;
        h *= subject.hashCode() * 17;
        h *= date.hashCode() * 11;
        return h;
    }


    @Override
    public String toString() {
        String out;
        out = "Subject: " + this.getSubject() + "\n";
        out += "Description: " + this.getDescription() + "\n";
        out += "Date: " + this.getDate() + "\n";
        return out;
    }


    public boolean notifyIf(CriteriaTester criteria) {
        if (criteria.isTrue(this)) {
            this.doNotify();
            return true;
        }
        return false;
    }

    public boolean notifyIf(Collection<CriteriaTester> criterias) {
        Boolean allTrue = true;
        for (CriteriaTester criteira : criterias) {
            allTrue &= criteira.isTrue(this);
        }
        if (allTrue) {
            this.doNotify();
        }
        return allTrue;
    }

    public boolean meetsCriteria(Collection<CriteriaTester> criterias) {
        Boolean allTrue = true;
        for (CriteriaTester criteira : criterias) {
            allTrue &= criteira.isTrue(this);
        }
        return allTrue;
    }


    public void doNotify() {
        //gets the notification Types from the config Reader / File
        ArrayList<Notification> notificationTypes = new ConfigReader().getNotificationTypes();

        for (Notification n : notificationTypes) {
            /*
            if (n instanceof JavaFxNotification) {
                Platform.runLater(() -> {
                    JavaFxNotification alert = new JavaFxNotification(this);
                    alert.setReminder(this);
                    alert.send();
                });
            } else {
            */
            n.setReminder(this);
            n.send();
            //}

        }
    }
}
