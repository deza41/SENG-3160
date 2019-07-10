package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealbookingRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.UserRecord;
import com.opensymphony.xwork2.ActionSupport;
import org.jooq.DSLContext;
import org.jooq.Result;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 28/08/2017.
 */
public class ManageBookingsAction extends ActionSupport implements RequiresDBConnection{

    private int bookingID;
    private DSLContext db;

    public String decline(){
        try{
            db.update(DEALBOOKING)
                    .set(DEALBOOKING.BOOKINGSTATUS, -1)
                    .where(DEALBOOKING.BOOKINGID.eq(bookingID))
                    .execute();

            if(bookingID == -2)
                throw new Exception();

            //notify admin.

        }catch(Exception e){

        }
        return SUCCESS;
    }

    public String accept(){

        try{
            if(bookingID == -2)
                throw new Exception();

            db.update(DEALBOOKING)
                    .set(DEALBOOKING.BOOKINGSTATUS, 1)
                    .where(DEALBOOKING.BOOKINGID.eq(bookingID))
                    .execute();


            //if gets the package id for later use. (will always get something)
            Result<DealbookingRecord> result3 = db.select(DEALBOOKING.fields())
                    .from(DEALBOOKING)
                    .join(PACKAGE)
                    .on(PACKAGE.PACKAGEID.eq(DEALBOOKING.PACKAGEID))
                    .where(DEALBOOKING.BOOKINGID.eq(bookingID))
                    .fetchInto(DEALBOOKING);

                //if this is the last item in a package notify user. (will return 0 if no other have a pending int
                 Result<DealbookingRecord> result = db.select(DEALBOOKING.fields())
                                            .from(DEALBOOKING)
                                                .join(PACKAGE)
                                                .on(PACKAGE.PACKAGEID.eq(DEALBOOKING.PACKAGEID))
                                            .where(DEALBOOKING.BOOKINGSTATUS.eq(0), DEALBOOKING.PACKAGEID.eq(result3.get(0).getPackageid()))
                                            .fetchInto(DEALBOOKING);



                int numberRemaining = result.size();
                if(numberRemaining == 0){
                    //give notification to user.
                    Result<UserRecord> result2 = db.select(USER.fields())
                            .from(DEALBOOKING)
                            .join(PACKAGE)
                            .on(PACKAGE.PACKAGEID.eq(DEALBOOKING.PACKAGEID))
                            .join(USER)
                            .on(PACKAGE.USERID.eq(USER.USERID))
                            .where(DEALBOOKING.BOOKINGID.eq(result3.get(0).getBookingid()))
                            .fetchInto(USER);

                    db.insertInto(RELATIONUSERNOTIFICATION, RELATIONUSERNOTIFICATION.USERID, RELATIONUSERNOTIFICATION.NOTIFICATIONID, RELATIONUSERNOTIFICATION.TYPE)
                            .values(result2.get(0).getUserid(), 2, 1)
                            .execute();
                    //email user.




                }

        }catch(Exception e){
            System.out.println(e);
        }
        return SUCCESS;
    }


    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db=ctx;
    }
}
