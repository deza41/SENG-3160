package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.CreateDealModel;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Tyrone on 11/10/2017.
 */
public class CreateDealActionTest extends StrutsTestCase{


    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    @Before//gets the database
    public void setUp() throws Exception{
        super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }




    @Test
    public void testExecute() throws Exception {//test with upload of file
        //set deal image null and not null
        File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "2017-06-11");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "http://www.whiteblueresturant.com/dealsImg");
        //request.setParameter("dealImageFile", testingFile); // idk how to set it


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();



        assertEquals("Result returned shows the form was created successfully ", "success", result);
        //assertEquals("Sat Jun 10 00:00:00 AEST 2017", action.getModel().getStartDate());// problem with the conversion of dates
        //assertEquals("http://www.whiteblueresturant.com/dealsImg", action.getModel().getDealImageURL());//checks if no url is given when a file is not uploaded

    }


    @Test
    public void testExecute2() throws Exception {//test with no upload
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "2017-06-11");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals("", action.getModel().getDealImageURL());//checks if no url is given when a file is not uploaded

    }


    @Test
    public void testExecute3() throws Exception {//test if no dealTitle is inputed
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "");
        request.setParameter("Description", "2017-06-11");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals("", action.getModel().getDealTitle());//checks if no url is given when a file is not uploaded

    }

    @Test
    public void testExecute4() throws Exception {//test if no description is inputed
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals("", action.getModel().getDescription());

    }

    @Test
    public void testExecute5() throws Exception {//test if duration is inputed
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "10");
        request.setParameter("validDuration", "");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals(0, action.getModel().getDuration());

    }


    @Test
    public void testExecute6() throws Exception {//test if no price is inputed
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "10");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals(0.0, action.getModel().getPrice());

    }

    @Test
    public void testExecute7() throws Exception {//test if no oldprice is inputed
        //set deal image null and not null
        //File testingFile = new File("c://SSUUpdater.log");


        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "ewe");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "");
        request.setParameter("dealImageURL", "");


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();




        assertEquals(0.0, action.getModel().getOldPrice());

    }



    @Test
    public void testNavToPage() throws Exception {
        //just returns success

        testBean.setID(1);

        request.setParameter("startDate", "2017-06-10");
        request.setParameter("endDate", "2017-06-11");

        request.setParameter("dealTitle", "2017-06-11");
        request.setParameter("Description", "2017-06-11");
        request.setParameter("validDuration", "10");
        request.setParameter("price", "50");
        request.setParameter("oldPrice", "40");
        request.setParameter("dealImageURL", "http://www.whiteblueresturant.com/dealsImg");
        //request.setParameter("dealImageFile", testingFile); // idk how to set it


        ActionProxy proxy = getActionProxy("/createDeal.action");
        proxy.getInvocation().getInvocationContext().setSession(sessionMap);
        CreateDealAction action = (CreateDealAction) proxy.getAction();
        String result = proxy.execute();



        assertEquals("success",action.navToPage());
    }

    @Test
    public void testGetModel() throws Exception {//idk if this is would count as a valid test

        CreateDealAction CreateDealActionTest = new CreateDealAction();
        assertEquals(CreateDealActionTest.getModel(), CreateDealActionTest.getModel());
    }

    @Test
    public void testGetDealImageFile() throws Exception {
        File testfile = new File("c://SSUUpdater.log");

        CreateDealAction CreateDealActionTest = new CreateDealAction();
        CreateDealActionTest.setDealImageFile(testfile);
        assertEquals(testfile, CreateDealActionTest.getDealImageFile()); //redirect to a file on your computer

    }

    @Test
    public void testGetMyFileContentType() throws Exception {

        CreateDealAction CreateDealActionTest = new CreateDealAction();
        CreateDealActionTest.setMyFileContentType("testcontext");
        assertEquals("testcontext", CreateDealActionTest.getMyFileContentType());

    }

   @Test
    public void testGetDealImageFileFileName() throws Exception {

       CreateDealAction CreateDealActionTest = new CreateDealAction();
       CreateDealActionTest.setDealImageFileFileName("swag");
       assertEquals("swag", CreateDealActionTest.getDealImageFileFileName());

    }

}