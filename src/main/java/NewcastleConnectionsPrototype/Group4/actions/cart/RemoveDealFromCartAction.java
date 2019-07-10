package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by simon janmaat on 24/08/2017.
 */
public class RemoveDealFromCartAction extends ActionSupport implements SessionAware {

    private int dealID, removeQ;
    private Map<String, Object> session, request;

    public String execute(){

        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        newPackage.removeDeal(dealID, removeQ);

        return SUCCESS; }

    //getters
    public int getDealID() {
        return dealID;
    }

    public int getRemoveQ() {
        return removeQ;
    }

    //setters
    public void setDealID(int dealID) {
        this.dealID = dealID;
    }

    public void setRemoveQ(int removeQ) {
        this.removeQ = removeQ;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}


