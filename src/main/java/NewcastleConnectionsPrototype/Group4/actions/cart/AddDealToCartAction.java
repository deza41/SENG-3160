package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessimagecontentRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import NewcastleConnectionsPrototype.Group4.models.view.BookingDealModel;
import NewcastleConnectionsPrototype.Group4.models.view.BusinessModel;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;

/**
 * Created by simon janmaat on 15/08/2017.
 */
public class AddDealToCartAction extends ActionSupport implements RequiresDBConnection, SessionAware {

    private Result<DealsRecord> addNewDeal;
    private Result<BusinessuserRecord> businessInfo;
    private Result<BusinessimagecontentRecord> businessProfilePicture;
    private int dealID;
    private boolean added = false;
    private BookingDealModel newDeal;
    private Map<String, Object> session, request;
    private DSLContext db;

    public int getDealID() {
        return dealID;
    }

    public void setDealID(int dealID) {
        this.dealID = dealID;
    }

    public String execute(){

        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        // when adding deal, check it's not already in cart. If it is just increment the quantity.
        // otherwise insert it
        for(int i =0; i<newPackage.getDealCart().size(); i++) {
            if (newPackage.getDealCart().get(i).getDealID() == dealID) {
                newPackage.getDealCart().get(i).setQuantity(1);
                added = true;
                break;
            }
        }
        if(!added) {
            try {
                addNewDeal = db.select(DEALS.DEALID, DEALS.BUSINESSID, DEALS.DEALTITLE, DEALS.DEALIMAGEURL, DEALS.OLDPRICE, DEALS.PRICE, DEALS.DEALDESCRIPTION)
                        .from(DEALS)
                        .where(DEALS.DEALID.eq(dealID))
                        .fetchInto(DEALS);

                //gets the business details
                businessInfo = db.select(BUSINESSUSER.fields())
                        .from(BUSINESSUSER)
                        .where(BUSINESSUSER.BUSINESSID.eq(addNewDeal.get(0).getBusinessid()))
                        .fetchInto(BUSINESSUSER);


                BusinessModel temp = new BusinessModel(businessInfo.get(0).getBusinessid(), businessInfo.get(0).getEmail(), businessInfo.get(0).getBusinessopen(), businessInfo.get(0).getBusinessclose(), businessInfo.get(0).getPhonenumber(), businessInfo.get(0).getProfileimageurl(), businessInfo.get(0).getBusinessname());

                //puts in only the necessary data to the Model for display later..
                newDeal = new BookingDealModel(addNewDeal.get(0).getDealid(), temp, addNewDeal.get(0).getDealtitle(), addNewDeal.get(0).getDealimageurl(), addNewDeal.get(0).getPrice(), addNewDeal.get(0).getOldprice(), addNewDeal.get(0).getDealdescription(), newPackage.getNumberOfChildren(), newPackage.getNumberOfAdults());

                //add in the package bean to save to session.
                newPackage.addDealToPackage(newDeal);

            } catch (Exception e) {
                System.out.println(e);
            }

        }
        return SUCCESS;
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
