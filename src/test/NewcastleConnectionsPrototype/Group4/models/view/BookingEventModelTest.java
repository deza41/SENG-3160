package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by simon janmaat on 19/09/2017.
 */
public class BookingEventModelTest extends StrutsTestCase {

    BookingEventModel testModel;
    Date testDate = new Date();

    @Before
    public void setUp() throws Exception{
        super.setUp();

        ArrayList<Time> startTimeList = new ArrayList<Time>();
        ArrayList<Time> endTimeList = new ArrayList<Time>();

        for(int i = 0; i < 23; i ++){
            startTimeList.add(new Time(i,0,0));
            endTimeList.add(new Time(i,0,0));
        }

        testModel = new BookingEventModel(1, 1, testDate, testDate, startTimeList, endTimeList, "ImageUrl", false, 10.50, 20.0,  "Title", "Event Desciption");

    }

    @Test
    public void testsetBusinessId() throws Exception {

        assertEquals(1, testModel.getBusinessId());

        testModel.setBusinessId(2);
        assertEquals(2, testModel.getBusinessId());

    }

    @Test
    public void testsetEventID() throws Exception {
        assertEquals(1, testModel.getEventID());

        testModel.setEventID(2);
        assertEquals(2, testModel.getEventID());
    }

    @Test
    public void testsetStartDate() throws Exception {
        assertEquals(testDate, testModel.getStartDate());

        Date newDate = new Date();
        testModel.setStartDate(newDate);
        assertEquals(newDate, testModel.getStartDate());

    }

    @Test
    public void testsetEndDate() throws Exception {
        assertEquals(testDate, testModel.getEndDate());

        Date newDate = new Date();
        testModel.setEndDate(newDate);
        assertEquals(newDate, testModel.getEndDate());
    }

    @Test
    public void testsetStartTime() throws Exception {


        assertEquals(23, testModel.getStartTime().size());
        assertEquals(0, testModel.getStartTime().get(0).getHours());

        ArrayList<Time> timeList = new ArrayList<Time>();

        timeList.add(new Time(1,0,0));
        testModel.setStartTime(timeList);
        assertEquals(1, testModel.getStartTime().size());
        assertEquals(timeList.get(0), testModel.getStartTime().get(0));

    }

    @Test
    public void testsetEndTime() throws Exception {


        assertEquals(23, testModel.getEndTime().size());
        assertEquals(0, testModel.getEndTime().get(0).getHours());

        ArrayList<Time> timeList = new ArrayList<Time>();

        timeList.add(new Time(5,0,0));
        testModel.setEndTime(timeList);
        assertEquals(1, testModel.getEndTime().size());
        assertEquals(timeList.get(0), testModel.getEndTime().get(0));
    }


    @Test
    public void testsetEventImage() throws Exception {

        testModel.setEventImage("testtest");
        assertEquals("testtest", testModel.getEventImage());
    }

    @Test
    public void testsetBusinessImage() throws Exception {

        testModel.setBusinessImage("testatesta");
        assertEquals("testatesta", testModel.getBusinessImage());
    }

    @Test
    public void testsetMultistartOrEndTime() throws Exception {

        testModel.setMultistartOrEndTime(false);
        assertEquals(false, testModel.isMultistartOrEndTime());

        testModel.setMultistartOrEndTime(true);
        assertEquals(true, testModel.isMultistartOrEndTime());
    }

    @Test
    public void testsetOldPrice() throws Exception {

        assertEquals(10.50, testModel.getOldPrice());

        testModel.setOldPrice(55);
        assertEquals(55.0, testModel.getOldPrice());
    }

    @Test
    public void testsetPrice() throws Exception {

        assertEquals(20.0, testModel.getPrice());
        testModel.setPrice(65);
        assertEquals(65.0, testModel.getPrice());
    }

    @Test
    public void testsetSavings() throws Exception {

        assertEquals(-9.5, testModel.getSavings());
        testModel.setPrice(10.5);
        assertEquals(-9.5, testModel.getSavings());
    }

    @Test
    public void testsetEventTitle() throws Exception {

        assertEquals("Title", testModel.getEventTitle());
        testModel.setEventTitle("testatesta");
        assertEquals("testatesta", testModel.getEventTitle());
    }

    @Test
    public void testsetEventDescription() throws Exception {
        assertEquals("Event Desciption", testModel.getEventDescription());
        testModel.setEventDescription("testateaasta");
        assertEquals("testateaasta", testModel.getEventDescription());
    }

    @Test
    public void testsetQuantity() throws Exception {
        int oldQuant = testModel.getQuantity();

        testModel.setQuantity(5);
        // quantity actually gets added
        assertEquals(5 + oldQuant, testModel.getQuantity());
    }

    @Test
    public void testsetSavingsPercentage() throws Exception {
        testModel.setSavingsPercentage("test");
        assertEquals("test", testModel.getSavingsPercentage());
    }

    @Test
    public void testsetTimeDayToUse() throws Exception {
        testModel.setTimeDayToUse(0);
        assertEquals(0, testModel.getTimeDayToUse());
    }

}