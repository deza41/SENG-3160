package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.DEALS;

public class ViewDealsAction extends ActionSupport implements RequiresDBConnection, ModelDriven<Result<DealsRecord>>,SessionAware, RequestAware {

    private Result<DealsRecord> model;
    private DSLContext db;
    private Map<String, Object> session, request;

    public String execute(){

       // HttpServletRequest request = ServletActionContext.getRequest();
       // HttpSession session = request.getSession();
        UserBean user = (UserBean) session.get("userBean");

        this.model = db.select()
                .from(DEALS)
                .where(DEALS.BUSINESSID.eq(user.getID()))
                .fetchInto(DEALS);

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<DealsRecord> getModel() {
        return model;
    }


    @Override
    public void setRequest(Map<String, Object> map) {
        this.request = map;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
