package NewcastleConnectionsPrototype.Group4.actions.review;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.ReviewRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.UserRecord;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.REVIEW;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;
/**
 * Created by simon janmaat on 18/08/2017.
 */
public class ViewReviewsAction extends ActionSupport implements RequiresDBConnection, SessionAware {

    private DSLContext db;
    private Result<ReviewRecord> reviews;
    private Result<UserRecord> listOfUsers;

    private Map<String, Object> session;

    public String execute(){

        UserBean user = (UserBean) session.get("userBean");

         reviews = db.select(REVIEW.fields())
                                        .from(REVIEW)
                                        .where(REVIEW.BUSINESSID.eq(user.getID()))
                                        .orderBy(REVIEW.DATE.desc())
                                        .fetchInto(REVIEW);

        listOfUsers = db.select(USER.fields())
                                .from(REVIEW)
                                    .join(USER)
                                    .on(USER.USERID.eq(REVIEW.USERID))
                                .where(REVIEW.BUSINESSID.eq(user.getID()))
                                .orderBy(REVIEW.DATE.desc())
                                .fetchInto(USER);

        return SUCCESS;
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    public Result<ReviewRecord> getReviews() {
        return reviews;
    }

    public void setReviews(Result<ReviewRecord> reviews) {
        this.reviews = reviews;
    }

    public Result<UserRecord> getListOfUsers() {
        return listOfUsers;
    }

    public void setListOfUsers(Result<UserRecord> listOfUsers) {
        this.listOfUsers = listOfUsers;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
