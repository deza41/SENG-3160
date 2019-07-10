package NewcastleConnectionsPrototype.Group4.models.view;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 11/10/2017.
 */
public class LikeModelTest {
    private LikeModel LikeModelTest = new LikeModel();
    @Test
    public void getUser() throws Exception {
        LikeModelTest.setUser(5);
        assertEquals(5, LikeModelTest.getUser());
    }

    @Test
    public void getBusinessUser() throws Exception {
        LikeModelTest.setBusinessUser(2);
        assertEquals(2, LikeModelTest.getBusinessUser());

    }

}