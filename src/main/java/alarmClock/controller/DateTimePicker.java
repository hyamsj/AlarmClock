package alarmClock.controller;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.DatePicker;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * A DateTimePicker with configurable datetime format where both date and time can be changed
 * via the text field and the date can additionally be changed via the JavaFX default date picker.
 * Taken from: http://stackoverflow.com/questions/28493097/is-there-any-date-and-time-picker-available-for-javafx
 */
@SuppressWarnings("unused")
public class DateTimePicker extends DatePicker {
    public static final String DefaultFormat = "dd-MM-yyyy HH:mm";

    private DateTimeFormatter formatter;
    private ObjectProperty<LocalDateTime> dateTimeValue = new SimpleObjectProperty<>(LocalDateTime.now());
    private ObjectProperty<String> format = new SimpleObjectProperty<String>() {
        @Override
        public void set(String newValue) {
            super.set(newValue);
            formatter = DateTimeFormatter.ofPattern(newValue);
        }
    };

    /**
     * Constructor
     */
    public DateTimePicker() {
        getStyleClass().add("datetime-picker");
        setFormat(DefaultFormat);
        setConverter(new InternalConverter());

        // Synchronize changes to the underlying date value back to the dateTimeValue
        valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) {
                dateTimeValue.set(null);
            } else {
                if (dateTimeValue.get() == null) {
                    dateTimeValue.set(LocalDateTime.of(newValue, LocalTime.now()));
                } else {
                    LocalTime time = dateTimeValue.get().toLocalTime();
                    dateTimeValue.set(LocalDateTime.of(newValue, time));
                }
            }
        });

        // Synchronize changes to dateTimeValue back to the underlying date value
        dateTimeValue.addListener((observable, oldValue, newValue) -> {
            setValue(newValue == null ? null : newValue.toLocalDate());
        });

        // Persist changes onblur
        getEditor().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                simulateEnterPressed();
        });

    }

    /**
     * Event Handler
     */
    private void simulateEnterPressed() {
        getEditor().fireEvent(new KeyEvent(getEditor(), getEditor(), KeyEvent.KEY_PRESSED, null, null, KeyCode.ENTER, false, false, false, false));
    }

    /**
     * Gets the DateTimeValue
     * @return tje dateTimeValue
     */
    public LocalDateTime getDateTimeValue() {
        return dateTimeValue.get();
    }

    /**
     * Sets the DateTimeValue
     * @param dateTimeValue the dateTimeValue (LocalDateTime.class)
     */
    public void setDateTimeValue(LocalDateTime dateTimeValue) {
        this.dateTimeValue.set(dateTimeValue);
    }

    /**
     * Returns the dateTimeValueProperty
     * @return dateTimeValueProperty
     */
    public ObjectProperty<LocalDateTime> dateTimeValueProperty() {
        return dateTimeValue;
    }

    /**
     * Returns the format (eg. dd-MM-yyyy HH:mm)
     * @return format
     */
    public String getFormat() {
        return format.get();
    }

    /**
     * Returns the formatProperty
     * @return formatProperty
     */
    public ObjectProperty<String> formatProperty() {
        return format;
    }

    /**
     * Sets the format
     * @param format
     */
    public void setFormat(String format) {
        this.format.set(format);
    }

    /**
     * Converts the time to the correct format
     */
    class InternalConverter extends StringConverter<LocalDate> {
        public String toString(LocalDate object) {
            LocalDateTime value = getDateTimeValue();
            return (value != null) ? value.format(formatter) : "";
        }

        public LocalDate fromString(String value) {
            if (value == null) {
                dateTimeValue.set(null);
                return null;
            }

            dateTimeValue.set(LocalDateTime.parse(value, formatter));
            return dateTimeValue.get().toLocalDate();
        }
    }
}