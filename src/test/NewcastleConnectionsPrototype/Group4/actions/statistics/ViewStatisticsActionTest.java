package NewcastleConnectionsPrototype.Group4.actions.statistics;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

/**
 * Created by simon janmaat on 28/10/2017.
 */
public class ViewStatisticsActionTest extends StrutsTestCase{


    @Before
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
    }

    @Test
    public void testExecute() throws Exception {

        ActionProxy proxy = getActionProxy("/viewStatistics.action");

        ViewStatisticsAction test = (ViewStatisticsAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned from executing the action was not success but it should have been.", "success", result);
        assertEquals(3, test.getMostViewedDeals().size());
        assertEquals(3, test.getMostPurchasedDeals().size());
        //assertEquals(3, test.getMostLikedBusiness().size());
        assertEquals(1, test.getMostLikedBusinessNames().size());
        assertEquals(1, test.getMostLikedBusinessPairModelList().size());

    }

}