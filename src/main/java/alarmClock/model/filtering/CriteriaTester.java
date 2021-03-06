package alarmClock.model.filtering;

import alarmClock.model.Reminder;

/**
 * Created by pascal on 5/16/17.
 * Interface for the criteria tester. Brings some kind of higher order functions to java.
 * Since Java has no real higher order functions, it is not possible to pass a  function as an inputParameter.
 * In Java it is only possible to to pass primitives or objects as inputParameters.
 * To replicate this behavior, we Created a object, that has exactly one Function. This way we can mimic
 * higher order functions.
 * Pleas read the documentation for further information.
 * As a convention we named the classes like according to the boolean name convention. But with a Capital Letter,
 * since they are classes.
 */
public interface CriteriaTester {
    /**
     * @param reminder the Reminder that gets tested. for the specific criteria.
     * @return boolean that answers the question if the  Criteria is met or not.
     */
    boolean isTrue(Reminder reminder);
}
