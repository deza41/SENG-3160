package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import static org.junit.Assert.*;

public class CreateEventModelTest {
    @Test
    public void getEventTimes() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        ArrayList<EventTimes> eventTimes = new ArrayList<>();
        EventTimes eventTime;

        Calendar calendar = Calendar.getInstance();
        calendar.set( calendar.YEAR, 2017 );
        calendar.set( calendar.MONTH, calendar.SEPTEMBER);
        calendar.set( calendar.DATE, 28);

        Time testTime = new Time( calendar.getTime().getTime());

        eventTime = new EventTimes(testTime, testTime, new Date());
        eventTimes.add(eventTime);

        createEventModel.setEventTimes(eventTimes);

        Iterator it = createEventModel.getEventTimes().iterator();

        EventTimes expected = (EventTimes) it.next();

        assertEquals(eventTime.getEndTime(), expected.getEndTime());
        assertEquals(eventTime.getDayDate(), expected.getDayDate());
        assertEquals(eventTime.getStartTime(), expected.getStartTime());
    }

    @Test
    public void getStartDate() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        Date startDate = new Date();

        createEventModel.setStartDate(startDate);
        assertEquals(startDate, createEventModel.getStartDate());
    }

    @Test
    public void getEndDate() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        Date endDate = new Date();

        createEventModel.setEndDate(endDate);
        assertEquals(endDate, createEventModel.getEndDate());
    }

    @Test
    public void getTitle() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setTitle("Testing Title");
        assertEquals("Testing Title", createEventModel.getTitle());
    }

    @Test
    public void getCategory() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setCategory("Testing Category");
        assertEquals("Testing Category", createEventModel.getCategory());
    }

    @Test
    public void getEventDescription() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setEventDescription("Testing EventDescription");
        assertEquals("Testing EventDescription", createEventModel.getEventDescription());
    }

    @Test
    public void getPrice() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setPrice(1);
        assertEquals(1, createEventModel.getPrice(), 0.0001);
    }

    @Test
    public void getOldPrice() throws Exception {
        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setOldPrice(1);
        assertEquals(1, createEventModel.getOldPrice(), 0.0001);
    }

}