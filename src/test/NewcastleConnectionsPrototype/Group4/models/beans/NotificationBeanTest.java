package NewcastleConnectionsPrototype.Group4.models.beans;

import NewcastleConnectionsPrototype.Group4.TestDataSource;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.NotificationsRecord;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;

import java.util.HashMap;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.NOTIFICATIONS;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.RELATIONUSERNOTIFICATION;
import static org.junit.Assert.*;

/**
 * Created by Tyrone on 28/10/2017.
 */
public class NotificationBeanTest {


    private DSLContext db;

    Map<String, Object> sessionMap = new HashMap<String, Object>();
    UserBean testBean = new UserBean();

    @Before//gets the database
    public void setUp() throws Exception{
    //    super.setUp();
        SimpleNamingContextBuilder.emptyActivatedContextBuilder().bind("java:/comp/env/jdbc/db", new TestDataSource());
        sessionMap.put("userBean", testBean);
    }

    @Test
    public void getResult() throws Exception {
        Result<NotificationsRecord> result;

        try {
            //selects all notifications.
            result = db.select(NOTIFICATIONS.fields())
                    .from(RELATIONUSERNOTIFICATION)
                    .join(NOTIFICATIONS)
                    .on(RELATIONUSERNOTIFICATION.NOTIFICATIONID.eq(NOTIFICATIONS.NOTIFICATIONID))
                    .where(RELATIONUSERNOTIFICATION.USERID.eq(testBean.getID()))
                    .orderBy(RELATIONUSERNOTIFICATION.NOTIFICATIONID.desc())
                    .fetchInto(NOTIFICATIONS);

        } catch (Exception e) {
            System.out.println(e);
        }

        NotificationBean NotificationBean = new NotificationBean();
       // NotificationBean.setResult(result);
    }




}