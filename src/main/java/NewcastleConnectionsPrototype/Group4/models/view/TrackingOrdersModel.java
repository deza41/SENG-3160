package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.*;
import org.jooq.Result;

import java.util.ArrayList;

/**
 * Created by simon janmaat on 29/08/2017.
 */
public class TrackingOrdersModel {

    private Result<PackageRecord> listOfPackages;
    private ArrayList<Result<DealbookingRecord>> ListOfListOfDealBookings  = new ArrayList<Result<DealbookingRecord>>();
    private ArrayList<Result<BusinessuserRecord>> ListOfListOfbusiness = new ArrayList<Result<BusinessuserRecord>>();
    private ArrayList<Result<EventsRecord>> ListOfListOfEvents  = new ArrayList<Result<EventsRecord>>();


    //getters
    public Result<PackageRecord> getListOfPackages() {
        return listOfPackages;
    }

    public ArrayList<Result<DealbookingRecord>> getListOfListOfDealBookings() {
        return ListOfListOfDealBookings;
    }

    public ArrayList<Result<EventsRecord>> getListOfListOfEvents() {
        return ListOfListOfEvents;
    }

    public ArrayList<Result<BusinessuserRecord>> getListOfListOfbusiness() {
        return ListOfListOfbusiness;
    }

    //setters

    public void setListOfPackages(Result<PackageRecord> listOfPackages) {
        this.listOfPackages = listOfPackages;
    }

    public void setListOfListOfDealBookings(Result<DealbookingRecord> ListOfDealBookings) {
        ListOfListOfDealBookings.add( ListOfDealBookings);
    }

    public void setListOfListOfEvents(Result<EventsRecord> ListOfEvents) {
        ListOfListOfEvents.add(ListOfEvents);
    }

    public void setListOfListOfbusiness(Result<BusinessuserRecord> ListOfbusiness) {
        ListOfListOfbusiness.add(ListOfbusiness);
    }
}
