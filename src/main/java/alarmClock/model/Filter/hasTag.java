package alarmClock.model.Filter;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/17/17.
 */
public class hasTag implements CriteriaTester{

    String tag;
    public hasTag(String tag){
        this.tag = tag;
    }
    @Override
    public boolean isTrue(Reminder r) {
        return r.getTags().contains(tag);
    }
}
