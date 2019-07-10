package NewcastleConnectionsPrototype.Group4.models.view;

import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.ReviewRecord;
import org.jooq.Result;

import java.util.ArrayList;

/**
 * Created by simon janmaat on 7/08/2017.
 */
public class SearchDealsModel {

    private Result<BusinessuserRecord> listOfBusinesses;
    private ArrayList<Result<DealsRecord>> listOfListOfDeals = new ArrayList<>();
    private ArrayList<Result<ReviewRecord>> listOfListOfReviews = new ArrayList<>();
    private ArrayList<Boolean> listOfLikedBusiness = new ArrayList<>();


    //setters (adding one at a time)
    public void setListOfBusinesses(Result<BusinessuserRecord> listOfBusinesses) {
        this.listOfBusinesses = listOfBusinesses;
    }

    public void setListOfDeals(Result<DealsRecord> ListOfDeals) {
            this.listOfListOfDeals.add(ListOfDeals);
    }

    public void setListOfReviews(Result<ReviewRecord> listOfReviews) {
        this.listOfListOfReviews.add(listOfReviews);
    }




    //getters
    public Result<BusinessuserRecord> getListOfBusinesses() {
        return listOfBusinesses;
    }

    public ArrayList<Result<DealsRecord>> getListOfListOfDeals() {
        return listOfListOfDeals;
    }

    public ArrayList<Result<ReviewRecord>> getListOfListOfReviews() {
        return listOfListOfReviews;
    }

    public int getBusinessListSize(){
        return listOfBusinesses.size();
    }

    public int getDealsListsSize() {
        return listOfListOfDeals.size();
    }

    public int getReviewsListsSize() {
        return listOfListOfReviews.size();
    }

    public void addListOfLikedBusiness(boolean value) { listOfLikedBusiness.add(value); }

    public ArrayList<Boolean> getListOfLikedBusiness() { return listOfLikedBusiness; }

}
