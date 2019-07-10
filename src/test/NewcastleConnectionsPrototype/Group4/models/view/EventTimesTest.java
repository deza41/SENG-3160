package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Rhys on 23/10/2017.
 */
public class EventTimesTest {

    @Test
    public void getStartTime()
    {
        //start time
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY,  10);
        startCalendar.set(Calendar.MINUTE, 10);
        Date startDate = startCalendar.getTime();
        Time startTime = new java.sql.Time(startDate.getTime());

        //end time
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY,  12);
        endCalendar.set(Calendar.MINUTE, 10);
        endCalendar.set(Calendar.SECOND, 00);
        Date endDate = endCalendar.getTime();
        Time endTime = new java.sql.Time(endDate.getTime());

        //date
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dayDate = dateformat.parse("15/10/2017");
            EventTimes eventTimes = new EventTimes(startTime, endTime, dayDate);

            eventTimes.setStartTime(startTime);

            assertEquals(startTime, eventTimes.getStartTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEndTime()
    {
        //start time
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY,  10);
        startCalendar.set(Calendar.MINUTE, 10);
        Date startDate = startCalendar.getTime();
        Time startTime = new java.sql.Time(startDate.getTime());

        //end time
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY,  12);
        endCalendar.set(Calendar.MINUTE, 10);
        endCalendar.set(Calendar.SECOND, 00);
        Date endDate = endCalendar.getTime();
        Time endTime = new java.sql.Time(endDate.getTime());

        //date
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dayDate = dateformat.parse("15/10/2017");
            EventTimes eventTimes = new EventTimes(startTime, endTime, dayDate);

            eventTimes.setEndTime(endTime);

            assertEquals(endTime, eventTimes.getEndTime());

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDayDate()
    {
        //start time
        Calendar startCalendar = Calendar.getInstance();
        startCalendar.set(Calendar.HOUR_OF_DAY,  10);
        startCalendar.set(Calendar.MINUTE, 10);
        Date startDate = startCalendar.getTime();
        Time startTime = new java.sql.Time(startDate.getTime());

        //end time
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.set(Calendar.HOUR_OF_DAY,  12);
        endCalendar.set(Calendar.MINUTE, 10);
        Date endDate = endCalendar.getTime();
        Time endTime = new java.sql.Time(endDate.getTime());

        //date
        SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date dayDate = dateformat.parse("15/10/2017");
            EventTimes eventTimes = new EventTimes(startTime, endTime, dayDate);

            assertEquals(dateformat.parse("15/10/2017"), dayDate);

            eventTimes.setDayDate(dateformat.parse("11/10/2018"));

            assertEquals(dateformat.parse("11/10/2018"), eventTimes.getDayDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
