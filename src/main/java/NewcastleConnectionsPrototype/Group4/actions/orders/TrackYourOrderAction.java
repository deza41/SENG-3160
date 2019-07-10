package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.*;
import NewcastleConnectionsPrototype.Group4.models.view.TrackingOrdersModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by Simon on 29/08/2017.
 */
public class TrackYourOrderAction extends ActionSupport implements RequiresDBConnection, ModelDriven<TrackingOrdersModel>, SessionAware {

    private DSLContext db;
    private String trackingID;
    private TrackingOrdersModel orders = new TrackingOrdersModel();
    private Map<String, Object> session, request;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute(){

        navigate();

        UserBean user = (UserBean) session.get("userBean");

        trackingID = user.getUsername();

        //if logged in.
        if(user.getRole().equals("user")) {
            try {
                //returns a list of packages from the newest one to the oldest one.
                Result<PackageRecord> packageResult = db.select(PACKAGE.fields())
                        .from(PACKAGE)
                        .where(PACKAGE.USERID.eq(user.getID()))
                        .orderBy(PACKAGE.PACKAGEID.desc())
                        .fetchInto(PACKAGE);
                orders.setListOfPackages(packageResult);

                if(user.getID() == -2)
                    throw new Exception();

                Iterator<PackageRecord> iter = packageResult.iterator();
                PackageRecord temp;
                while(iter.hasNext()) {
                    temp = iter.next();


                    //the result retrieves the list of deals associated with the package.
                    Result<DealbookingRecord> dealsBooked = db.select(DEALBOOKING.fields())
                            .from(DEALBOOKING)
                            .where(DEALBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .orderBy(DEALBOOKING.BOOKINGSTATUS.desc(), DEALBOOKING.DEALID.asc())
                            .fetchInto(DEALBOOKING);
                    orders.setListOfListOfDealBookings(dealsBooked);

                    Result<BusinessuserRecord> listOfBusiness = db.select(BUSINESSUSER.fields())
                            .from(DEALBOOKING)
                                .join(BUSINESSUSER)
                                .on(DEALBOOKING.BUSINESSID.eq(BUSINESSUSER.BUSINESSID))
                            .where(DEALBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .orderBy(DEALBOOKING.BOOKINGSTATUS.desc(), DEALBOOKING.DEALID.asc())
                            .fetchInto(BUSINESSUSER);


                    orders.setListOfListOfbusiness(listOfBusiness);

                    //the result retrieves the list of events associated with the package.
                    Result<EventsRecord> eventsBooked = db.select(EVENTS.fields())
                            .from(EVENTBOOKING)
                                .join(EVENTS)
                                .on(EVENTBOOKING.EVENTID.eq(EVENTS.EVENTID))
                            .where(EVENTBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .fetchInto(EVENTS);
                    //add these two results to the model.
                    orders.setListOfListOfEvents(eventsBooked);

                }
            } catch (Exception e) {

            }
        }else {
            try {
                //query for "tracking" ID.(is the username)
                Result<PackageRecord> packageResult = db.select(PACKAGE.fields())
                        .from(PACKAGE)
                        .join(USER)
                        .on(USER.USERNAME.eq(trackingID), USER.USERID.eq(PACKAGE.USERID))
                        .fetchInto(PACKAGE);
                orders.setListOfPackages(packageResult);

                if(user.getID() == -2)
                    throw new Exception();

                Iterator<PackageRecord> iter = packageResult.iterator();
                PackageRecord temp;
                while (iter.hasNext()) {
                    temp = iter.next();


                    //the result retrieves the list of deals associated with the package.
                    Result<DealbookingRecord> dealsBooked = db.select(DEALBOOKING.fields())
                            .from(DEALBOOKING)
                            .where(DEALBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .orderBy(DEALBOOKING.BOOKINGSTATUS.desc(), DEALBOOKING.DEALID.asc())
                            .fetchInto(DEALBOOKING);
                    orders.setListOfListOfDealBookings(dealsBooked);

                    Result<BusinessuserRecord> listOfBusiness = db.select(BUSINESSUSER.fields())
                            .from(DEALBOOKING)
                            .join(BUSINESSUSER)
                            .on(DEALBOOKING.BUSINESSID.eq(BUSINESSUSER.BUSINESSID))
                            .where(DEALBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .orderBy(DEALBOOKING.BOOKINGSTATUS.desc(), DEALBOOKING.DEALID.asc())
                            .fetchInto(BUSINESSUSER);


                    orders.setListOfListOfbusiness(listOfBusiness);

                    //the result retrieves the list of events associated with the package.
                    Result<EventsRecord> eventsBooked = db.select(EVENTS.fields())
                            .from(EVENTBOOKING)
                            .join(EVENTS)
                            .on(EVENTBOOKING.EVENTID.eq(EVENTS.EVENTID))
                            .where(EVENTBOOKING.PACKAGEID.eq(temp.getPackageid()))
                            .fetchInto(EVENTS);
                    //add these two results to the model.
                    orders.setListOfListOfEvents(eventsBooked);

                }
            } catch (Exception e) {

            }
        }
        return SUCCESS;
    }



    public String navigate(){

        UserBean user = (UserBean) session.get("userBean");



        if(trackingID != null){
            user.setUsername(trackingID);
            return "trackingID";
        }


        if(user.isLoggedIn()){
            return "loggedin";
        }

        return "success";
    }
    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db=ctx;
    }


    @Override
    public TrackingOrdersModel getModel() {
        return orders;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }
}


