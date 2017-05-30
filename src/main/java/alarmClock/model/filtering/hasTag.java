package alarmClock.model.filtering;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/17/17.
 * Decides if a specific tag  was added to a Reminder.
 */
public class hasTag implements CriteriaTester {

    private String tag;

    /**
     * constructor, that takes the tag to test against as
     * @param  tag a String that represents the tag you want to filter by
     */
    public hasTag(String tag) {
        this.tag = tag;
    }

    /**
     *
     * @param  r The Remeinder that gets tested.
     * @return the Answer if the Reminder r has the tag that was specified in the constructor.
     */
    @Override
    public boolean isTrue(Reminder r) {
        return r.getTags().contains(tag);
    }
}
