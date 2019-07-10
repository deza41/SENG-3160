package NewcastleConnectionsPrototype.Group4.actions.orders;

import NewcastleConnectionsPrototype.Group4.models.beans.ItineraryItemBean;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;

/**
 * Created by Will on 06/08/2017.
 */
public class ItineraryAction extends ActionSupport {

    private List<ItineraryItemBean> itineraryItems;

    public String execute(){

        return SUCCESS;}

    public List<ItineraryItemBean> getItineraryItems() {
        return itineraryItems;
    }

    public void setSearchResults(List<ItineraryItemBean> searchResults) {
        this.itineraryItems = searchResults;
    }
}
