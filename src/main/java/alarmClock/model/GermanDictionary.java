package alarmClock.model;

/**
 * Created by pascal on 5/8/17.
 */
public class GermanDictionary implements Dictionary {
    @Override
    public String getDate() {
        return  "Datum";
    }

    @Override
    public String getDescription() {
        return "Beschreibung";
    }

    @Override
    public String getSubject() {
        return "Titel";
    }

    @Override
    public String getTime() {
        return "Zeit";
    }

}
