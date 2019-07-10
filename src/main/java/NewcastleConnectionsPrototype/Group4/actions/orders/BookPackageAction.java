package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.NotificationsRecord;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingEventModel;
import NewcastleConnectionsPrototype.Group4.models.view.BookingModel;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 28/08/2017.
 */
public class BookPackageAction extends ActionSupport implements RequiresDBConnection, SessionAware {

    private DSLContext db;
    private int packageID;
    private Map<String, Object> session, request;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() {


        PackageBean packageBean = (PackageBean) session.get("PackageBean");
        UserBean user = (UserBean) session.get("userBean");



        if(user.getID()!=-1) {
            try {

                Result result =  db.insertInto(PACKAGE, PACKAGE.USERID)
                        .values(user.getID())
                        .returning(PACKAGE.PACKAGEID)
                        .fetch();


                //will always be getting an int.
                packageID = (int) result.getValue(0, result.field(0));



                if(packageBean.getDealCart().size() != 0){
                    //insert into db.
                    Iterator<BookingDealModel> dealIter = packageBean.getDealCart().iterator();
                    BookingDealModel temp;

                    //no time is given (gift or just the voucher purchase)
                    while(dealIter.hasNext()) {
                        temp = dealIter.next();

                        for(int j = 0; j < temp.getQuantity(); j++) {

                            db.insertInto(DEALBOOKING, DEALBOOKING.PACKAGEID, DEALBOOKING.DEALID, DEALBOOKING.BUSINESSID, DEALBOOKING.BOOKINGSTATUS)
                                    .values(packageID, temp.getDealID(), temp.getBusiness().getBusinessID(), -2)
                                    .execute();

                        }
                    }

                }

                if(packageBean.getBookings().size()!= 0)
                {
                    Iterator<BookingModel> bookingIter = packageBean.getBookings().iterator();
                    BookingModel temp2;

                    //booking has been made.
                    while(bookingIter.hasNext()) {
                     temp2 = bookingIter.next();
                     int notificationID;

                     Date daysDate = new Date(temp2.getDaysDate().getTime());

                        //insert the bookings
                        db.insertInto(DEALBOOKING, DEALBOOKING.PACKAGEID, DEALBOOKING.DEALID, DEALBOOKING.BUSINESSID, DEALBOOKING.STARTTIME, DEALBOOKING.DATE, DEALBOOKING.ENDTIME,DEALBOOKING.NUMBEROFADULTS, DEALBOOKING.NUMBEROFCHILDREN)
                                .values(packageID, temp2.getDeal().getDealID(), temp2.getDeal().getBusiness().getBusinessID(),temp2.getStartTime(), daysDate,temp2.getEndTime(),temp2.getNumberOfAdults(), temp2.getNumberOfChildren())
                                .execute();

                        //looking for a record with the user's information to link notifications in future. 1
                        Result<NotificationsRecord> tempNotificationRecord = db.select(NOTIFICATIONS.fields())
                                .from(NOTIFICATIONS)
                                .where(NOTIFICATIONS.DESCRIPTION.equal("You have an unconfirmed booking by "+user.getFirstName()+" "+user.getLastName()+"."))
                                .fetchInto(NOTIFICATIONS);

                        //notification doesn't exist inside the db
                        if(tempNotificationRecord.size()==0){
                            Result tempResult = db.insertInto(NOTIFICATIONS,NOTIFICATIONS.DESCRIPTION, NOTIFICATIONS.TITLE, NOTIFICATIONS.LINK)
                                    .values("You have an unconfirmed booking by "+user.getFirstName()+" "+user.getLastName()+".", "Unconfirmed Booking", "")
                                    .returning(NOTIFICATIONS.NOTIFICATIONID)
                                    .fetch();


                            //will always be getting an int.
                            notificationID = (int) tempResult.getValue(0, tempResult.field(0));
                            //System.out.println(notificationID);

                        }else{
                            notificationID =tempNotificationRecord.get(0).getNotificationid();
                            //System.out.println(notificationID);
                        }
                        //inserts a new relation to the notification created/found above.
                        db.insertInto(RELATIONBUSINESSUSERNOTIFICATION, RELATIONBUSINESSUSERNOTIFICATION.BUSINESSID, RELATIONBUSINESSUSERNOTIFICATION.NOTIFICATIONID, RELATIONBUSINESSUSERNOTIFICATION.TYPE)
                               .values(temp2.getDeal().getBusiness().getBusinessID(), notificationID, 1)
                               .execute();

                        //send email
                    }
                }

                if(packageBean.getEventsCart().size() != 0){
                    Iterator<BookingEventModel> eventIter = packageBean.getEventsCart().iterator();
                    BookingEventModel temp3;

                    while(eventIter.hasNext()) {
                        temp3 = eventIter.next();


                        for(int i=0; i<temp3.getQuantity(); i++) {
                            //add event to the package.
                            db.insertInto(EVENTBOOKING, EVENTBOOKING.PACKAGEID, EVENTBOOKING.BUSINESSID, EVENTBOOKING.EVENTID)
                                    .values(packageID, temp3.getBusinessId(), temp3.getEventID())
                                    .execute();
                        }
                    }
                }



            } catch (Exception e) {
                System.out.println(e);
            }
            //deletes the cart as its been added to db.
            session.remove("PackageBean");
        }

            return SUCCESS;


    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db=ctx;
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
    }
}
