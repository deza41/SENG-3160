package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;

/**
 * Created by Tyrone on 26/08/2017.
 */

/**
 * Created by Tyrone on 22/08/2017.
 */
public class DisplayOneBusinessAccount extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<BusinessuserRecord>>, CheckLogin, SessionAware {

    private DSLContext db;
    private Result<BusinessuserRecord> model;

    private Map<String, Object> session;

    @Override
    public String execute(){
        UserBean user = (UserBean) session.get("userBean");

        this.model = db.select()
                .from(BUSINESSUSER)
                .fetchInto(BUSINESSUSER);
        this.model =db.select()
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.BUSINESSID.eq(user.getID()))
                .fetchInto(BUSINESSUSER);

        return "success";
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<BusinessuserRecord> getModel() {
        return model;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
