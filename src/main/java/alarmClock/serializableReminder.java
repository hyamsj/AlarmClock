package alarmClock;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by pascal on 4/25/17.
 */
public class serializableReminder implements Serializable{
    private String subject =  "";
    private  String description = "";
    private String time = "";
    private LocalDate date;

    public serializableReminder(String subject, String description, String time, LocalDate date) {
        setSubject(subject);
        setDescription(description);
        setTime(time);
        setDate(date);
    }

    public String getSubject() {
        return subject;
    }

    public String subjectProperty() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public String descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public String timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate dateProperty() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public Reminder getReminder(){
        return new Reminder(this.getSubject(),this.getDescription(),this.getTime(),this.getDate());
    }
}
