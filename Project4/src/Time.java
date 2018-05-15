@javax.jdo.annotations.PersistenceCapable

public class Time
{
    // Represents a time in hour:minute. For example, 8:52 is 8:52 AM and 15:4 is 3:04 PM.

    int hour; // 0 -- 23
    int minute; // 0 -- 59

    public int differenceFrom(Time earlierTime)

	/* Returns the time difference in minutes between "earlierTime" and the target time object,
	   with "earlierTime" always regarded as the earlier time. Note that the time interval may include midnight.
           For example, suppose the target object represents 1:30. Then:

           if earlierTime = 23:30, the function will return 120 (minutes);
           if earlierTime = 0:30, the function will return 60 (minutes);
	   if earlierTime = 9:30, the function will return 960 (minutes), etc. */

    {
        int timeDifferent;
        int eTimeMinute = ((earlierTime.hour * 60) + earlierTime.minute);
        int tTimeMinute = ((hour * 60) + minute);
        timeDifferent = ((1440 % eTimeMinute) + (tTimeMinute % 1440));
        return timeDifferent;
    }

    public boolean isInInterval(int h1, int m1, int h2, int m2)

	/* Checks if the target time object is in the time interval from h1:m1 to h2:m2, inclusive,
	   with h1:m1 always regarded as earlier than h2:m2. Note that the interval may include midnight. */

    {
        int Time1 = ((h1 * 60)+m1);
        int Time2 = ((h2 * 60)+m2);
        int earlierTime = ((hour * 60)+minute);
        if(Time1<= earlierTime && earlierTime<=Time2){
            return true;
        }else
            return false;
    }
}