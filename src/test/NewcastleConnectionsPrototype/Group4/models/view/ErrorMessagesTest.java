package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 11/10/2017.
 */
public class ErrorMessagesTest {
    private ErrorMessages ErrorMessagesTest = new ErrorMessages();

    @Test
    public void getUsername_Error_Message() throws Exception {
        ErrorMessagesTest.setUsername_Error_Message("rip");
        assertEquals("rip", ErrorMessagesTest.getUsername_Error_Message());
    }

    @Test
    public void getPassword_Error() throws Exception {
        ErrorMessagesTest.setPassword_Error("wrong");
        assertEquals("wrong", ErrorMessagesTest.getPassword_Error());
    }

}