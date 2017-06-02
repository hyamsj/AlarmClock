package alarmClock.model.filtering;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/17/17.
 * Decides if a specific tag  was added to a Reminder.
 */
public class HasTag implements CriteriaTester {

    private String tag;

    /**
     * constructor, that takes the tag to test against as
     *
     * @param tag a String that represents the tag you want to filter by
     */
    public HasTag(String tag) {
        this.tag = tag;
    }

    /**
     * @param reminder The Reminder that gets tested.
     * @return the Answer if the Reminder reminder has the tag that was specified in the constructor.
     */
    @Override
    public boolean isTrue(Reminder reminder) {
        return reminder.getTags().contains(tag);
    }
}
