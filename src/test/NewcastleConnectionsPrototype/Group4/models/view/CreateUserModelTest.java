package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 3/10/2017.
 */
public class CreateUserModelTest {
    private CreateUserModel testCreateUserModel = new CreateUserModel();

    @Test
    public void getUsername() throws Exception {
        testCreateUserModel.setUsername("Testing Username");
        assertEquals("Testing Username", testCreateUserModel.getUsername());
    }

    @Test
    public void getFirstName() throws Exception {
        testCreateUserModel.setFirstName("Testing FirstName");
        assertEquals("Testing FirstName", testCreateUserModel.getFirstName());
    }

    @Test
    public void getLastName() throws Exception {
        testCreateUserModel.setLastName("Testing lastName");
        assertEquals("Testing lastName", testCreateUserModel.getLastName());
    }

    @Test
    public void getPhoneNumber() throws Exception {
        testCreateUserModel.setPhoneNumber("Testing phoneNumber");
        assertEquals("Testing phoneNumber", testCreateUserModel.getPhoneNumber());
    }

    @Test
    public void getEmail() throws Exception {
        testCreateUserModel.setEmail("Testing email");
        assertEquals("Testing email", testCreateUserModel.getEmail());
    }

    @Test
    public void getPassword() throws Exception {
        testCreateUserModel.setPassword("Testing password");
        assertEquals("Testing password", testCreateUserModel.getPassword());

    }

    @Test
    public void getPostCode() throws Exception {
        testCreateUserModel.setPostCode("Testing postcode");
        assertEquals("Testing postcode", testCreateUserModel.getPostCode());
    }

    @Test
    public void getCheckPassword() throws Exception {
        testCreateUserModel.setCheckPassword("Testing checkpassword");
        assertEquals("Testing checkpassword", testCreateUserModel.getCheckPassword());
    }

    @Test
    public void getDateOfBirth() throws Exception {
        Date date = new Date();
        testCreateUserModel.setDateOfBirth(date);
        assertEquals(date, testCreateUserModel.getDateOfBirth());
    }

    @Test
    public void setUsername() throws Exception {
        testCreateUserModel.setUsername("Testing Username");
        assertEquals("Testing Username", testCreateUserModel.getUsername());
    }

    @Test
    public void setFirstName() throws Exception {
        testCreateUserModel.setFirstName("Testing FirstName");
        assertEquals("Testing FirstName", testCreateUserModel.getFirstName());
    }

    @Test
    public void setLastName() throws Exception {
        testCreateUserModel.setLastName("Testing lastName");
        assertEquals("Testing lastName", testCreateUserModel.getLastName());
    }

    @Test
    public void setPhoneNumber() throws Exception {
        testCreateUserModel.setPhoneNumber("Testing phoneNumber");
        assertEquals("Testing phoneNumber", testCreateUserModel.getPhoneNumber());
    }

    @Test
    public void setEmail() throws Exception {
        testCreateUserModel.setEmail("Testing email");
        assertEquals("Testing email", testCreateUserModel.getEmail());
    }

    @Test
    public void setPassword() throws Exception {
        testCreateUserModel.setPassword("Testing password");
        assertEquals("Testing password", testCreateUserModel.getPassword());
    }

    @Test
    public void setPostCode() throws Exception {
        testCreateUserModel.setPostCode("Testing postcode");
        assertEquals("Testing postcode", testCreateUserModel.getPostCode());
    }

    @Test
    public void setCheckPassword() throws Exception {
        testCreateUserModel.setCheckPassword("Testing checkpassword");
        assertEquals("Testing checkpassword", testCreateUserModel.getCheckPassword());
    }

    @Test
    public void setDateOfBirth() throws Exception {
        Date date = new Date();
        testCreateUserModel.setDateOfBirth(date);
        assertEquals(date, testCreateUserModel.getDateOfBirth());
    }

}