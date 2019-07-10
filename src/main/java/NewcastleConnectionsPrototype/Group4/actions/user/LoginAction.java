package NewcastleConnectionsPrototype.Group4.actions.user;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.UserRecord;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import java.util.HashMap;
import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;

/**
 * Created by blake on 13/05/2017.
 */
public class LoginAction extends ActionSupport implements SessionAware, RequiresDBConnection{

    private Map<String,Object> session;
    private DSLContext db;

    private String username;
    private String password;

    public String execute()
    {


        boolean userAccount = db.fetchExists //search for user account
                (db.selectOne()
                        .from(USER)
                        .where(USER.USERNAME.eq(username)
                                .and(USER.PASSWORD.eq(password))));

        boolean businessAccount = db.fetchExists //search for business user account
                (db.selectOne()
                        .from(BUSINESSUSER)
                        .where(BUSINESSUSER.USERNAME.eq(username)
                                .and(BUSINESSUSER.PASSWORD.eq(password))));
        if(userAccount) {
            Result<UserRecord> model = db.select()
                    .from(USER)
                    .where(USER.USERNAME.eq(username)
                            .and(USER.PASSWORD.eq(password)))
                    .fetchInto(USER);

            UserBean user = (UserBean) session.get("userBean");
            int admin = (Integer.parseInt(model.getValue(0, "admin").toString()));
            user.setFirstName(model.getValue(0, "firstName").toString());
            user.setLastName(model.getValue(0, "lastName").toString());
            user.setContactNo(model.getValue(0, "phoneNumber").toString());
            user.setEmail(model.getValue(0, "email").toString());
            user.setID(Integer.parseInt(model.getValue(0, "userID").toString()));
            user.setLoggedIn(true);
            if(admin == 1){
                user.setRole("admin");
            }else {
                user.setRole("user");
            }
            return "success"; //login success as user or admin
        } else if(businessAccount){
            Result<BusinessuserRecord> model = db.select()
                    .from(BUSINESSUSER)
                    .where(BUSINESSUSER.USERNAME.eq(username)
                            .and(BUSINESSUSER.PASSWORD.eq(password)))
                    .fetchInto(BUSINESSUSER);

            UserBean user = (UserBean) session.get("userBean");
            user.setFirstName(model.getValue(0, "firstName").toString());
            user.setLastName(model.getValue(0, "lastName").toString());
            user.setContactNo(model.getValue(0, "phoneNumber").toString());
            user.setEmail(model.getValue(0, "email").toString());
            user.setID(Integer.parseInt(model.getValue(0, "businessID").toString()));
            user.setLoggedIn(true);
            user.setRole("business");

            return "business"; //login success as business acc
        }else{
            return "error"; //login failure
        }
    }

    public String logout()
    {
        if(session != null){
            session = null;
            session = new HashMap<String, Object>();
        }
        return "success";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void setSession(Map<String, Object> map)
    {
        this.session = map;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }
}
