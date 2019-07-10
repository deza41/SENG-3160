package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.view.SearchDealsModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Record;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.*;

/**
 * Created by simon janmaat on 14/08/2017.
 */
public class SearchDealsAction extends ActionSupport implements RequiresDBConnection, ModelDriven<SearchDealsModel>, SessionAware {

    String category;

    private Map<String, Object> session;

    private DSLContext db;
    SearchDealsModel newSearch = new SearchDealsModel();

    public String getCategory() {
        return category;
    }

    public String getPageHeading() {
        String returnString;

        if(category.equals("experience")){
            returnString = "Experience Newcastle";
        } else if(category.equals("food")){
            returnString = "Experience Cuisine";
        } else if(category.equals("accommodation")){
            returnString = "Stay in Newcastle";
        } else{
            returnString = "Travel around Newcastle";
        }

        return returnString;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String execute()throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        //this gets a list of businesses with the category given by the user when searching.
        this.newSearch.setListOfBusinesses(db.select(BUSINESSUSER.fields())
                .from(BUSINESSUSER)
                .join(CATEGORIES)
                .on(BUSINESSUSER.CATEGORYID.eq(CATEGORIES.CATEGORIESID))
                .where(CATEGORIES.CATEGORY.eq(this.category))
                .fetchInto(BUSINESSUSER));

        //this gets the list of deals for the business found above. (orders by the deals "popularity")
        //also gets the list of "reviews" for the business too.
        for (int j = 0; j < newSearch.getBusinessListSize(); j++) {
            boolean likedValue = false;

            this.newSearch.setListOfDeals(db.select(DEALS.fields())
                    .from(DEALS)
                    .where(DEALS.BUSINESSID.eq(newSearch.getListOfBusinesses().get(j).getBusinessid()))
                    .orderBy(DEALS.TOTALPURCHASED.desc())
                    .fetchInto(DEALS));

            this.newSearch.setListOfReviews(db.select(REVIEW.fields())
                    .from(REVIEW)
                    .where(REVIEW.BUSINESSID.eq(newSearch.getListOfBusinesses().get(j).getBusinessid()))
                    .orderBy(REVIEW.DATE.desc())
                    .fetchInto(REVIEW));


            if(user != null) {

                Record result = db.select(LIKEDBUSINESSES.LIKED)
                        .from(LIKEDBUSINESSES)
                        .where(LIKEDBUSINESSES.BUSINESSID.eq(newSearch.getListOfBusinesses().get(j).getBusinessid()))
                        .and(LIKEDBUSINESSES.USERID.eq(user.getID()))
                        .fetchOne();

                /*
                if (result != null){
                    if((byte) result.getValue(0, result.field(0)) == 1){
                        likedValue = true;
                    }
                }
                */
            }

            this.newSearch.addListOfLikedBusiness(likedValue);

        }

        return "success";
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public SearchDealsModel getModel() {
        return newSearch;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}