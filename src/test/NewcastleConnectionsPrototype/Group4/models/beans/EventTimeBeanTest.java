package NewcastleConnectionsPrototype.Group4.models.beans;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 28/10/2017.
 */
public class EventTimeBeanTest {

    @Test
    public void TestEventTimeBean() throws Exception {
        EventTimeBean EventTimeBean = new EventTimeBean("1","10");
    }

    @Test
    public void TestGetTimeId() throws Exception {
        EventTimeBean EventTimeBean = new EventTimeBean();
        EventTimeBean.setTimeId("1");
        assertEquals("1",  EventTimeBean.getTimeId());

    }

    @Test
    public void testGetTimeAmount() throws Exception {
        EventTimeBean EventTimeBean = new EventTimeBean();
        EventTimeBean.setTimeAmount("10");
        assertEquals("10",  EventTimeBean.getTimeAmount());

    }

}