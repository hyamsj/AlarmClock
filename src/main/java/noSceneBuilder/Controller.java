package noSceneBuilder;

/**
 * Created by joni on 25/04/17.
 */
public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }

    public void addButtonClicked(String text, String descriptionInputText, String timeInputText, String dateInputText) {
        Reminder reminder = new Reminder(text, descriptionInputText, timeInputText, dateInputText);
        model.addNewReminder(reminder);

    }

    public void deleteButtonClicked() {
        model.removeReminder();
    }
}
