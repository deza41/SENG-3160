package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealbookingRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.UserRecord;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessOrdersModel;
import NewcastleConnectionsPrototype.Group4.models.view.UserModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 28/08/2017.
 */
public class ManageOrdersAction extends ActionSupport implements RequiresDBConnection, ModelDriven<BusinessOrdersModel>, SessionAware {

    private DSLContext db;
    private BusinessOrdersModel model = new BusinessOrdersModel();
    private ArrayList<UserModel> pendingUsersList = new ArrayList<UserModel>();
    private ArrayList<UserModel> confirmedUsersList = new ArrayList<UserModel>();
    private Map<String, Object> session, request;

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }

    public String execute() {

        UserBean user = (UserBean) session.get("userBean");


        //makes sure it is a business user.
        if(user.getRole().equals("business")) {
            try {

                if(user.getID() == -2)
                    throw new Exception();

                model.setListOfPendingBookings(db.select(DEALBOOKING.fields())
                        .from(DEALBOOKING)
                        .where(DEALBOOKING.BUSINESSID.eq(user.getID()), DEALBOOKING.BOOKINGSTATUS.eq(0))
                        .fetchInto(DEALBOOKING));



                Iterator<DealbookingRecord> pendingUsers = model.getListOfPendingBookings().iterator();
                int tempBookingID;

                while (pendingUsers.hasNext()) {
                    UserModel tempUser = new UserModel();
                    tempBookingID = pendingUsers.next().getBookingid();
                    Result<UserRecord> tempRecord =  db.select(USER.fields())
                            .from(DEALBOOKING)
                                .join(PACKAGE)
                                .on(PACKAGE.PACKAGEID.eq(DEALBOOKING.PACKAGEID))
                                .join(USER)
                                .on(USER.USERID.eq(PACKAGE.USERID))
                            .where(DEALBOOKING.BUSINESSID.eq(user.getID()), DEALBOOKING.BOOKINGSTATUS.eq(0), DEALBOOKING.BOOKINGID.eq(tempBookingID))
                            .fetchInto(USER);
                    //makes the temp user model
                    tempUser.setFirstName(tempRecord.get(0).getFirstname());
                    tempUser.setLastName(tempRecord.get(0).getLastname());
                    tempUser.setEmail(tempRecord.get(0).getEmail());
                    tempUser.setDateOfBirth(tempRecord.get(0).getDateofbirth());
                    tempUser.setPhoneNumber(tempRecord.get(0).getPhonenumber());



                    //sets the pending user list.
                    model.setListOfPendingUsers(tempUser);


                }


                //gets a list of confirmed orders.
                model.setListOfConfirmedBookings(db.select(DEALBOOKING.fields())
                        .from(DEALBOOKING)
                        .where(DEALBOOKING.BUSINESSID.eq(user.getID()), DEALBOOKING.BOOKINGSTATUS.eq(1))
                        .fetchInto(DEALBOOKING));

                Iterator<DealbookingRecord> confirmedUsers = model.getListOfConfirmedBookings().iterator();
                int tempBookingID2;


                while (confirmedUsers.hasNext()) {
                    tempBookingID2 = confirmedUsers.next().getBookingid();
                    UserModel tempUser2 = new UserModel();
                    Result<UserRecord> tempconfirmedRecord = db.select(USER.fields())
                            .from(DEALBOOKING)
                                .join(PACKAGE)
                                .on(PACKAGE.PACKAGEID.eq(DEALBOOKING.PACKAGEID))
                                    .join(USER)
                                    .on(USER.USERID.eq(PACKAGE.USERID))
                            .where(DEALBOOKING.BUSINESSID.eq(user.getID()), DEALBOOKING.BOOKINGSTATUS.eq(1), DEALBOOKING.BOOKINGID.eq(tempBookingID2))
                            .fetchInto(USER);
                    //makes temp user model to add.
                    tempUser2.setFirstName(tempconfirmedRecord.get(0).getFirstname());
                    tempUser2.setLastName(tempconfirmedRecord.get(0).getLastname());
                    tempUser2.setEmail(tempconfirmedRecord.get(0).getEmail());
                    tempUser2.setDateOfBirth(tempconfirmedRecord.get(0).getDateofbirth());
                    tempUser2.setPhoneNumber(tempconfirmedRecord.get(0).getPhonenumber());
                    //sets the users that have been confirmed.
                    model.setListOfConfirmedUsers(tempUser2);
                }

                } catch(Exception e){
                    System.out.println(e);
                }

            }

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db=ctx;
    }

    @Override
    public BusinessOrdersModel getModel() {
        return model;
    }
}
