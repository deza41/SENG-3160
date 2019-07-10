package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.UserRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;

/**
 * Created by Tyrone on 26/08/2017.
 */

/**
 * Created by Tyrone on 22/08/2017.
 */
public class DisplayOneUserAccount extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<UserRecord>>, CheckLogin, SessionAware {

    private Map<String, Object> session;

    private DSLContext db;
    private Result<UserRecord> model;

    @Override
    public String execute(){

        UserBean user = (UserBean) session.get("userBean");

        this.model =db.select()
                .from(USER)
                .where(USER.USERID.eq(user.getID()))
                .fetchInto(USER);

        return "success";
    }


    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }


    @Override
    public Result<UserRecord> getModel() {
        return model;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
