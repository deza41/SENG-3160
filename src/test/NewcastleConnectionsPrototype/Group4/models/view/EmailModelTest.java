package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmailModelTest {
    @Test
    public void getTo() throws Exception {
        EmailModel emailModel = new EmailModel();

        emailModel.setTo("Testing To");
        assertEquals("Testing To", emailModel.getTo());
    }

    @Test
    public void getSubject() throws Exception {
        EmailModel emailModel = new EmailModel();

        emailModel.setSubject("Testing Subject");
        assertEquals("Testing Subject", emailModel.getSubject());
    }

    @Test
    public void getBody() throws Exception {
        EmailModel emailModel = new EmailModel();

        emailModel.setBody("Testing Body");
        assertEquals("Testing Body", emailModel.getBody());
    }


}