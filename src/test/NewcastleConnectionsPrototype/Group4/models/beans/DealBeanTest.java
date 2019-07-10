package NewcastleConnectionsPrototype.Group4.models.beans;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 28/10/2017.
 */
public class DealBeanTest {
    @Test
    public void TestDealBean() throws Exception {
        DealBean DealBean = new DealBean("1");

    }

    @Test
    public void TestGetDealID() throws Exception {
        DealBean DealBean = new DealBean();
        DealBean.setDealID("1");
        assertEquals("1",  DealBean.getDealID());
    }

}