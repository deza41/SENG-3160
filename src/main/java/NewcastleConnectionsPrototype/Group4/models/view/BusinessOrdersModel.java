package NewcastleConnectionsPrototype.Group4.models.view;


import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealbookingRecord;
import org.jooq.Result;

import java.util.ArrayList;


/**
 * Created by simon janmaat on 28/08/2017.
 */
public class BusinessOrdersModel {

    private Result<DealbookingRecord> listOfPendingBookings;
    private Result<DealbookingRecord> listOfConfirmedBookings;
    private ArrayList<UserModel> listOfPendingUsers = new ArrayList<UserModel>();
    private ArrayList<UserModel> listOfConfirmedUsers= new ArrayList<UserModel>();

    //getters
    public Result<DealbookingRecord> getListOfPendingBookings() {
        return listOfPendingBookings;
    }

    public Result<DealbookingRecord> getListOfConfirmedBookings() {
        return listOfConfirmedBookings;
    }

    public ArrayList<UserModel> getListOfPendingUsers() {
        return listOfPendingUsers;
    }

    public ArrayList<UserModel> getListOfConfirmedUsers() {
        return listOfConfirmedUsers;
    }

    //setters
    public void setListOfPendingBookings(Result<DealbookingRecord> listOfPendingBookings) {
        this.listOfPendingBookings = listOfPendingBookings;
    }

    public void setListOfConfirmedBookings(Result<DealbookingRecord> listOfConfirmedBookings) {
        this.listOfConfirmedBookings = listOfConfirmedBookings;
    }

    public void setListOfPendingUsers(UserModel pendingUser) {
        this.listOfPendingUsers.add(pendingUser);
    }

    public void setListOfConfirmedUsers(UserModel confirmedUsers) {
        this.listOfConfirmedUsers.add(confirmedUsers);
    }
}
