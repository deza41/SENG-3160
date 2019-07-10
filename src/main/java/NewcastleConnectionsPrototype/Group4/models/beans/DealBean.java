package NewcastleConnectionsPrototype.Group4.models.beans;

import java.io.Serializable;

/**
 * Created by blake on 22/05/2017.
 */
public class DealBean implements Serializable {
    private String dealID;

    public DealBean(String dealID) {
        this.dealID = dealID;
    }

    public DealBean() {
        this.dealID = "none";
    }

    public String getDealID() {
        return dealID;
    }

    public void setDealID(String dealID) {
        this.dealID = dealID;
    }
}
