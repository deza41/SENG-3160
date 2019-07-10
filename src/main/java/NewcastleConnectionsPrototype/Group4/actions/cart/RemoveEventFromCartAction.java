package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

/**
 * Created by simon janmaat on 24/08/2017.
 */
public class RemoveEventFromCartAction extends ActionSupport implements SessionAware {
    private int eventID, removeQ;
    private Map<String, Object> session, request;

    public String execute(){
        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        newPackage.removeEvent(eventID, removeQ);

        return SUCCESS;
    }

    //getters
    public int getEventID() {
        return eventID;
    }

    public int getRemoveQ() {
        return removeQ;
    }

    //setters
    public void setEventID(int dealID) {
        this.eventID = dealID;
    }

    public void setRemoveQ(int removeQ) {
        this.removeQ = removeQ;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
