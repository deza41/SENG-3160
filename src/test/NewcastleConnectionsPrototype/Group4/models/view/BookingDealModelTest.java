package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by simon janmaat on 19/09/2017.
 */
public class BookingDealModelTest {
    private BusinessModel testBusiness = new BusinessModel();
    private BookingDealModel bookingdealmodeltest = new BookingDealModel(1, testBusiness, "test Deal", "Test URL", 10.00, 20.00, "test description", 1, 3);

    @Test
    public void getNumberOfChildren() throws Exception {
        assertEquals(1, bookingdealmodeltest.getNumberOfChildren());
    }

    @Test
    public void getNumberOfAdults() throws Exception {
        assertEquals(3, bookingdealmodeltest.getNumberOfAdults());
    }

    @Test
    public void getDealDescription() throws Exception {
        assertEquals("test description", bookingdealmodeltest.getDealDescription());
    }

    @Test
    public void getDealID() throws Exception {
        assertEquals(1, bookingdealmodeltest.getDealID());
    }

    @Test
    public void getDealTitle() throws Exception {
        assertEquals("test Deal", bookingdealmodeltest.getDealTitle());
    }

    @Test
    public void getDealImage() throws Exception {
        assertEquals("Test URL", bookingdealmodeltest.getDealImage());
    }

    @Test
    public void getPrice() throws Exception {
        assertEquals(10.00, bookingdealmodeltest.getPrice(), 0.001);
    }

    @Test
    public void getOldPrice() throws Exception {
        assertEquals(20.00, bookingdealmodeltest.getOldPrice(), 0.001);
    }

    @Test
    public void getSavings() throws Exception {
        assertEquals(10.00, bookingdealmodeltest.getSavings(), 0.001);
    }

    @Test
    public void getSavingsPercentage() throws Exception {
        assertEquals("10.0 (50%)", bookingdealmodeltest.getSavingsPercentage());
    }

    @Test
    public void getQuantity() throws Exception {
        assertEquals(1, bookingdealmodeltest.getQuantity());
    }

    @Test
    public void getBusiness() throws Exception {
        assertEquals(testBusiness, bookingdealmodeltest.getBusiness());
    }

    @Test
    public void setQuantity() throws Exception {
        bookingdealmodeltest.setQuantity(1);
        assertEquals(2, bookingdealmodeltest.getQuantity());
    }

    @Test
    public void setNumberOfAdults() throws Exception {
        bookingdealmodeltest.setNumberOfAdults(6);
        assertEquals(6, bookingdealmodeltest.getNumberOfAdults());
    }

    @Test
    public void setNumberOfChildren() throws Exception {
        bookingdealmodeltest.setNumberOfChildren(4);
        assertEquals(4, bookingdealmodeltest.getNumberOfChildren());
    }

}