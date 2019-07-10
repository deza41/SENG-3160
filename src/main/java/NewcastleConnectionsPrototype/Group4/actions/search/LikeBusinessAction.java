package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.LIKEDBUSINESSES;


/**
 * Created by Will on 06/08/2017.
 */
public class LikeBusinessAction extends ActionSupport  implements SessionAware, RequiresDBConnection {

    private DSLContext db;
    private int businessID;
    private int liked;
    private Map<String, Object> session;

    public String execute() throws Exception {

        UserBean user = (UserBean) session.get("userBean");

        if(user != null){

            Byte val = 0;

            if(liked == 1){
                val = 1;
            }
            int userid = user.getID();

            db.delete(LIKEDBUSINESSES)
                    .where(LIKEDBUSINESSES.BUSINESSID.eq(this.businessID))
                    .and(LIKEDBUSINESSES.USERID.eq(userid))
                    .execute();


            db.insertInto(LIKEDBUSINESSES)
                .columns(LIKEDBUSINESSES.BUSINESSID, LIKEDBUSINESSES.USERID, LIKEDBUSINESSES.LIKED)
                .values(this.businessID, userid, val)
                .execute();

        }

        return SUCCESS;
    }

    public void setBusinessID(int businessID)
    {
        this.businessID = businessID;
    }

    public void setLiked(int liked)
    {
        this.liked = liked;
    }

    public int getLiked(){
        return this.liked;
    }

    public int getBusinessID(){
        return this.businessID;
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