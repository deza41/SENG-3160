package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 11/10/2017.
 */
public class BusinessModelTest {
    private BusinessModel testBusinessModel = new BusinessModel();

    @Test
    public void getBusinessID() throws Exception {

    }

    @Test
    public void getUserName() throws Exception {
        testBusinessModel.setUserName("Testing Username");
        assertEquals("Testing Username", testBusinessModel.getUserName());
    }

    @Test
    public void getLatitude() throws Exception {
        testBusinessModel.setLatitude("192");
        assertEquals("192", testBusinessModel.getLatitude());
    }

    @Test
    public void getLongitude() throws Exception {
        testBusinessModel.setLongitude("244");
        assertEquals("244", testBusinessModel.getLongitude());
    }

    @Test
    public void getFirstName() throws Exception {
        testBusinessModel.setFirstName("Testing Firstname");
        assertEquals("Testing Firstname", testBusinessModel.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        testBusinessModel.setLastName("Testing Lastname");
        assertEquals("Testing Lastname", testBusinessModel.getLastName());
    }

    @Test
    public void getPassword() throws Exception {
        testBusinessModel.setPassword("password");
        assertEquals("password", testBusinessModel.getPassword());
    }

    @Test
    public void getPhoneNumber() throws Exception {
        testBusinessModel.setPassword("password");
        assertEquals("password", testBusinessModel.getPassword());
    }

    @Test
    public void getEmail() throws Exception {
        testBusinessModel.setEmail("email@hotmail.com");
        assertEquals("email@hotmail.com", testBusinessModel.getEmail());
    }

    @Test
    public void getPostCode() throws Exception {
        testBusinessModel.setPostCode("2298");
        assertEquals("2298", testBusinessModel.getPostCode());
    }

    @Test
    public void getBusinessName() throws Exception {
        testBusinessModel.setBusinessName("email@hotmail.com");
        assertEquals("email@hotmail.com", testBusinessModel.getBusinessName());
    }

    @Test
    public void getStreet() throws Exception {
        testBusinessModel.setStreet("swag");
        assertEquals("swag", testBusinessModel.getStreet());
    }

    @Test
    public void getNumber() throws Exception {
        testBusinessModel.setNumber("0495969344");
        assertEquals("0495969344", testBusinessModel.getNumber());
    }

    @Test
    public void getSuburb() throws Exception {
        testBusinessModel.setSuburb("jesmond");
        assertEquals("jesmond", testBusinessModel.getSuburb());
    }

    @Test
    public void getUnit() throws Exception {
        testBusinessModel.setUnit("24");
        assertEquals("24", testBusinessModel.getUnit());
    }

    @Test
    public void getProfileImageURL() throws Exception {
        testBusinessModel.setProfileImageURL("swagggrr");
        assertEquals("swagggrr", testBusinessModel.getProfileImageURL());
    }

    @Test
    public void getBusinessOpen() throws Exception {
        Date date = new Date();
        testBusinessModel.setBusinessOpen(date);
        assertEquals(date, testBusinessModel.getBusinessOpen());
    }

    @Test
    public void getBusinessClose() throws Exception {
        Date date = new Date();
        testBusinessModel.setBusinessClose(date);
        assertEquals(date, testBusinessModel.getBusinessClose());
    }

    @Test
    public void getBusinessDescription() throws Exception {
        testBusinessModel.setBusinessDescription("swagggrr");
        assertEquals("swagggrr", testBusinessModel.getBusinessDescription());
    }

    @Test
    public void getCategoryID() throws Exception {
        testBusinessModel.setCategoryID(12);
        assertEquals(12, testBusinessModel.getCategoryID());
    }

    @Test
    public void getRating() throws Exception {
        testBusinessModel.setRating(5);
        assertEquals(5, testBusinessModel.getRating());
    }

    @Test
    public void getBusinessViews() throws Exception {
        testBusinessModel.setRating(100);
        assertEquals(100, testBusinessModel.getRating());
    }

    @Test
    public void getCategoryName() throws Exception {
        testBusinessModel.setCategoryName("food");
        assertEquals("food", testBusinessModel.getCategoryName());
    }

    @Test
    public void getFiles() throws Exception {

    }

    @Test
    public void setFiles() throws Exception {
    }

    @Test
    public void getFilesContentType() throws Exception {
    }

    @Test
    public void getFilesFileName() throws Exception {
    }


}