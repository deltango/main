package seedu.address.commons.util;

import seedu.address.model.task.date.DateTime;
import seedu.address.model.task.date.DateValue;

/**
 * Helper functions for handling DateValue.
 */
public class DateUtil {

    /* Number of milliseconds in one day */
    public static final long ONE_DAY_IN_MILLISECONDS = 24 * 3600 * 1000;

    public static final long MONDAY = 1;
    public static final long SUNDAY = 0;

    /**
     * Returns DateValue object with date set to today.
     */
    public static DateValue getToday() {
        return (DateValue) new DateTime();
    }

    /**
     * Returns DateValue object with date set to tomorrow.
     */
    public static DateValue getTomorrow() {
        return getNextDay(getToday());
    }

    /**
     * Returns true if the two pairs of Dates intersecting
     *
     * @param firstBegin beginning Date of the first pair
     * @param firstEnd   ending Date of the first pair
     * @param secondBegin beginning Date of the first pair
     * @param secondEnd  ending Date of the first pair
     */
    public static boolean haveIntersection(DateValue firstBegin, DateValue firstEnd,
                                                DateValue secondBegin, DateValue secondEnd) {
        if (firstBegin.after(secondEnd) || secondBegin.after(firstEnd)) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Return the next day of given date.
     */
    public static DateValue getNextDay(DateValue date) {
        DateValue newDate = new DateTime(date);
        return newDate.setTime(newDate.getTime() + ONE_DAY_IN_MILLISECONDS);
    }

    /**
     * Return the previous day of given date.
     */
    public static DateValue getPreviousDay(DateValue date) {
        DateValue newDate = new DateTime(date);
        return newDate.setTime(newDate.getTime() - ONE_DAY_IN_MILLISECONDS);
    }

    /**
     * Returns the beginning of day at time 00:00:00.
     */
    public static DateValue getBeginOfDay(DateValue date) {
        DateValue newDate = new DateTime(date);
        return newDate.setHours(0).setMinutes(0).setSeconds(0);
    }

    /**
     * Returns the end of day at time 23:59:59.
     */
    public static DateValue getEndOfDay(DateValue date) {
        DateValue newDate = new DateTime(date);
        return newDate.setHours(23).setMinutes(59).setSeconds(59);
    }

    /**
     * Returns the beginning of week at time Monday, 00:00:00.
     */
    public static DateValue getBeginOfWeek(DateValue date) {
        DateValue newDate = new DateTime(date);
        while (newDate.getDay() != MONDAY) {
            newDate = getPreviousDay(newDate);
        }
        return getBeginOfDay(newDate);
    }

    /**
     * Returns the end of week at time Sunday, 23:59:59.
     */
    public static DateValue getEndOfWeek(DateValue date) {
        DateValue newDate = new DateTime(date);
        while (newDate.getDay() != SUNDAY) {
            newDate = getNextDay(newDate);
        }
        return getEndOfDay(newDate);
    }

}
