package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Tyrone on 24/10/2017.
 */
public class DealModifiedActionTest extends StrutsTestCase {

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();


    @Before//gets the database
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }


    @Test
    public void testExecute() throws Exception {
        File testingFile = new File("c://SSUUpdater.log");
        testBean.setID(1);
        request.setParameter("oldDealID", "1");
        request.setParameter("dealTitle", "swwaagg");
        request.setParameter("Description", "its lit boi");
        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");
        //request.setParameter("dealsResults.get(0).setDealimageurl()", "http://www.whiteblueresturant.com/dealsImg");
        //request.setParameter("dealImageFile", testingFile);


        ActionProxy proxy = getActionProxy("/modifyDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        DealModifiedAction action = (DealModifiedAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

    @Test
    public void testExecute2() throws Exception {
        File testingFile = new File("c://SSUUpdater.log");
        testBean.setID(1);
        request.setParameter("oldDealID", "1");
        request.setParameter("dealTitle", "");
        request.setParameter("Description", "its lit boi");
        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");
        //request.setParameter("dealsResults.get(0).setDealimageurl()", "http://www.whiteblueresturant.com/dealsImg");
        //request.setParameter("dealImageFile", testingFile);


        ActionProxy proxy = getActionProxy("/modifyDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        DealModifiedAction action = (DealModifiedAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }

    @Test
    public void testExecute3() throws Exception {
        File testingFile = new File("c://SSUUpdater.log");
        testBean.setID(1);
        request.setParameter("oldDealID", "1");
        request.setParameter("dealTitle", "swwaagg");
        request.setParameter("Description", "");
        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");
        //request.setParameter("dealsResults.get(0).setDealimageurl()", "http://www.whiteblueresturant.com/dealsImg");
        //request.setParameter("dealImageFile", testingFile);


        ActionProxy proxy = getActionProxy("/modifyDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        DealModifiedAction action = (DealModifiedAction) proxy.getAction();
        String result = proxy.execute();

        assertEquals("Result returned shows the form was created successfully ", "success", result);
    }



    @Test
    public void testGetOldDealID() throws Exception {
        DealModifiedAction testDealMod = new DealModifiedAction();
        testDealMod.setOldDealID(1);
        int result = testDealMod.getOldDealID();
        assertEquals("checks if the setter and getter for oldDealId works ", 1, result);

    }

    @Test
    public void testGetModel() throws Exception {
        DealModifiedAction DealModifiedAction = new DealModifiedAction();
        assertEquals(DealModifiedAction.getModel(), DealModifiedAction.getModel());
    }

    @Test
    public void testGetDealImageFile() throws Exception {
        File testfile = new File("c://SSUUpdater.log");

        DealModifiedAction DealModifiedAction = new DealModifiedAction();
        DealModifiedAction.setDealImageFile(testfile);
        assertEquals(testfile, DealModifiedAction.getDealImageFile()); //redirect to a file on your computer
    }

    @Test
    public void testGetMyFileContentType() throws Exception {
        DealModifiedAction DealModifiedAction = new DealModifiedAction();
        DealModifiedAction.setMyFileContentType("testcontext");
        assertEquals("testcontext", DealModifiedAction.getMyFileContentType());
    }

    @Test
    public void testGetDealImageFileFileName() throws Exception {
        DealModifiedAction DealModifiedAction = new DealModifiedAction();
        DealModifiedAction.setDealImageFileFileName("swag");
        assertEquals("swag", DealModifiedAction.getDealImageFileFileName());

    }




}