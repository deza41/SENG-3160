package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by simon janmaat on 29/08/2017.
 */
public class RemoveBookingFromCartAction extends ActionSupport implements SessionAware {

    private int localBookingID;
    private Map<String, Object> session, request;


    public String execute(){

        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        newPackage.removeBookingFrmPackage(localBookingID);

        return SUCCESS;
    }

    public int getLocalBookingID() {
        return localBookingID;
    }

    public void setLocalBookingID(int localBookingID) {
        this.localBookingID = localBookingID;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}


