package NewcastleConnectionsPrototype.Group4.actions.notifications;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.NotificationBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.NotificationsRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 29/08/2017.
 */
public class NotificationsAction extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<NotificationsRecord>>, SessionAware {

    private DSLContext db;
    private Map<String, Object> session;
    private Result<NotificationsRecord> listOfNotifications;

    private UserBean user;
    private NotificationBean notifications;

    public String execute(){

        user = (UserBean) session.get("userBean");
        notifications = (NotificationBean) session.get("notificationBean");
        if(user.getRole().equals("user")) {

            setUserNotifications();

        }else if(user.getRole().equals("business")){
            setBusinessUserNotifications();
        }

        notifications.setResult(listOfNotifications);

        return SUCCESS;
    }


    public String navNotifications() {
        user = (UserBean) session.get("userBean");
        notifications = (NotificationBean) session.get("notificationBean");
        if(user.getRole().equals("user")) {

            setUserNotifications();

        }else if(user.getRole().equals("business")){
            setBusinessUserNotifications();
        }

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db=ctx;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }


    @Override
    public Result<NotificationsRecord> getModel() {
        return listOfNotifications;
    }

    private void setUserNotifications(){
        try {
            //selects all notifications.
            listOfNotifications = db.select(NOTIFICATIONS.fields())
                    .from(RELATIONUSERNOTIFICATION)
                        .join(NOTIFICATIONS)
                        .on(RELATIONUSERNOTIFICATION.NOTIFICATIONID.eq(NOTIFICATIONS.NOTIFICATIONID))
                    .where(RELATIONUSERNOTIFICATION.USERID.eq(user.getID()))
                    .orderBy(RELATIONUSERNOTIFICATION.NOTIFICATIONID.desc())
                    .fetchInto(NOTIFICATIONS);

        } catch (Exception e) {
            System.out.println(e);
        }

    }


    private void setBusinessUserNotifications(){
        try {
            //selects all notifications.
            listOfNotifications = db.select(NOTIFICATIONS.fields())
                    .from(RELATIONBUSINESSUSERNOTIFICATION)
                        .join(NOTIFICATIONS)
                        .on(RELATIONBUSINESSUSERNOTIFICATION.NOTIFICATIONID.eq(NOTIFICATIONS.NOTIFICATIONID))
                    .where(RELATIONBUSINESSUSERNOTIFICATION.BUSINESSID.eq(user.getID()))
                    .orderBy(RELATIONBUSINESSUSERNOTIFICATION.NOTIFICATIONID.desc())
                    .fetchInto(NOTIFICATIONS);

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
