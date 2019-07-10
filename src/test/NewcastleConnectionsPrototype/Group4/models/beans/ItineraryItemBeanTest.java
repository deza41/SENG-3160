package NewcastleConnectionsPrototype.Group4.models.beans;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 28/10/2017.
 */
public class ItineraryItemBeanTest {
    @Test
    public void testItineraryItemBean() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean(1,"1",1,1,1,1,1);
    }

    @Test
    public void testGetNumberOfChildren() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setNumberOfChildren(1);
        assertEquals(1,  ItineraryItemBean.getNumberOfChildren());
    }

    @Test
    public void testGetNumberOfAdults() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setNumberOfAdults(1);
        assertEquals(1,  ItineraryItemBean.getNumberOfAdults());
    }

    @Test
    public void testGetLocalBookingID() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setLocalBookingID(1);
        assertEquals(1,  ItineraryItemBean.getLocalBookingID());
    }

    @Test
    public void testGetItineraryItemId() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setItineraryItemId(1);
        assertEquals(1,  ItineraryItemBean.getItineraryItemId());
    }

    @Test
    public void testGetDayId() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setDayId("1");
        assertEquals("1",  ItineraryItemBean.getDayId());
    }

    @Test
    public void testGetHourNumber() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setHourNumber(1);
        assertEquals(1,  ItineraryItemBean.getHourNumber());
    }

    @Test
    public void testGetIntervalNumber() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setIntervalNumber(1);
        assertEquals(1,  ItineraryItemBean.getIntervalNumber());
    }

    @Test
    public void testGetIntervalAmount() throws Exception {
        ItineraryItemBean ItineraryItemBean = new ItineraryItemBean();
        ItineraryItemBean.setIntervalAmount(1);
        assertEquals(1,  ItineraryItemBean.getIntervalAmount());
    }


}