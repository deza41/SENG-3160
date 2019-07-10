package NewcastleConnectionsPrototype.Group4.models.view;

import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;

import java.sql.Time;
import java.util.Date;


/**
 * Created by William on 16/10/2017.
 */
public class BookingModelTest extends StrutsTestCase {
    private BookingModel bookingModel;

    @Before
    public void setUp() throws Exception {
        super.setUp();
        BusinessModel bm = new BusinessModel();

        BookingDealModel bdm = new BookingDealModel(0, bm, "dealtitle", "dealimage", 0, 0, "dealdescription", 1, 1 );

        bookingModel = new BookingModel(1, new Time(0,0,0), new Time(0,0,0), new Date(), 1,1, bdm);

    }

    @Test
    public void testsetDeal() throws Exception{
        BusinessModel bm = new BusinessModel();
        BookingDealModel bdm = new BookingDealModel(5, bm, "dealtitle", "dealimage", 0, 0, "dealdescription", 1, 1 );

        bookingModel.setDeal(bdm);

        assertEquals(5, bookingModel.getDeal().getDealID());
    }

    @Test
    public void testsetStartTime() throws Exception{

        bookingModel.setStartTime(new Time(5,0,0));
        assertEquals(5, bookingModel.getStartTime().getHours());
    }

    @Test
    public void testsetEndTime() throws Exception{

        bookingModel.setEndTime(new Time(5,0,0));
        assertEquals(5, bookingModel.getEndTime().getHours());
    }

    @Test
    public void testsetDaysDate() throws Exception{

        Date d = new Date();
        bookingModel.setDaysDate(d);
        assertEquals(d.getTime(), bookingModel.getDaysDate().getTime());
    }

    @Test
    public void testsetNumberOfAdults() throws Exception{

        bookingModel.setNumberOfAdults(3);
        assertEquals(3, bookingModel.getNumberOfAdults());
    }

    @Test
    public void testsetNumberOfChildren() throws Exception{

        bookingModel.setNumberOfChildren(5);
        assertEquals(5, bookingModel.getNumberOfChildren());
    }

}