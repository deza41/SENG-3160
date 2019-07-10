package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by simon janmaat on 19/09/2017.
 */
public class UserModelTest {
    private UserModel testUserModel = new UserModel();

    @Test
    public void getUsername() throws Exception {
        testUserModel.setUsername("Testing Username");
        assertEquals("Testing Username", testUserModel.getUsername());
    }

    @Test
    public void getFirstName() throws Exception {
        testUserModel.setFirstName("Testing FirstName");
        assertEquals("Testing FirstName", testUserModel.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        testUserModel.setLastName("Testing lastName");
        assertEquals("Testing lastName", testUserModel.getLastName());
    }

    @Test
    public void getPhoneNumber() throws Exception {
        testUserModel.setPhoneNumber("Testing phoneNumber");
        assertEquals("Testing phoneNumber", testUserModel.getPhoneNumber());
    }

    @Test
    public void getEmail() throws Exception {
        testUserModel.setEmail("Testing email");
        assertEquals("Testing email", testUserModel.getEmail());
    }

    @Test
    public void getPostCode() throws Exception {
        testUserModel.setPostCode("Testing postcode");
        assertEquals("Testing postcode", testUserModel.getPostCode());
    }

    @Test
    public void getDateOfBirth() throws Exception {
        Date date = new Date();
        testUserModel.setDateOfBirth(date);
        assertEquals(date, testUserModel.getDateOfBirth());
    }

    @Test
    public void getProfileImageURL() throws Exception {
        testUserModel.setProfileImageURL("Testing ProfileImage");
        assertEquals("Testing ProfileImage", testUserModel.getProfileImageURL());
    }
}