package alarmClock.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Created by pascal on 4/25/17.
 */
public class serializableReminder implements Serializable{
    private String subject =  "";
                        private  String description = "";
    private LocalDateTime time =LocalDateTime.now();
    private LocalDate date;

    public serializableReminder(String subject, String description, LocalDateTime time, LocalDate date) {
        setSubject(subject);
        setDescription(description);
        setTime(time);
        setDate(date);
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public LocalDate getDate() {
        return date;
    }

    public Reminder getReminder(){
        return new Reminder(this.getSubject(),this.getDescription(),this.getTime(),this.getDate());
    }

    private void setSubject(String subject) {
        this.subject = subject;
    }

    private void setDescription(String description) {
        this.description = description;
    }
    private void setTime(LocalDateTime time) {
        this.time = time;
    }
    private void setDate(LocalDate date) {
        this.date = date;
    }
}
