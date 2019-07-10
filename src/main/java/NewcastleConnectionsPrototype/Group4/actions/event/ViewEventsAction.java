package NewcastleConnectionsPrototype.Group4.actions.event;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.EventsRecord;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.EVENTS;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class  ViewEventsAction implements RequiresDBConnection, ModelDriven<Result<EventsRecord>>, SessionAware {

    private Result<EventsRecord> model;
    private DSLContext db;
    private Map<String, Object> session;

    public String execute(){

        UserBean user = (UserBean) session.get("userBean");

        // create insert query to save deal
        this.model = db.select()
                .from(EVENTS)
                .where(EVENTS.BUSINESSID.eq(user.getID()))
                .fetchInto(EVENTS);
        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public Result<EventsRecord> getModel() {
        return model;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
