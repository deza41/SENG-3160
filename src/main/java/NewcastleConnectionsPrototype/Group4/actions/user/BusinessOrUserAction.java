
package NewcastleConnectionsPrototype.Group4.actions.user;

        import NewcastleConnectionsPrototype.Group4.interfaces.CheckLogin;
        import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
        import NewcastleConnectionsPrototype.Group4.models.view.CreateUserModel;
        import com.opensymphony.xwork2.ActionSupport;
        import com.opensymphony.xwork2.ModelDriven;
        import org.jooq.DSLContext;
        import org.jooq.impl.DSL;

        import java.sql.Date;

        import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;
        import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.BUSINESSUSER;
/**
 * Created by Tyrone on 22/08/2017.
 */
public class BusinessOrUserAction extends ActionSupport implements RequiresDBConnection, ModelDriven, CheckLogin{

    private DSLContext db;
    public CreateUserModel newUser = new CreateUserModel();
    private String access_granted;
    @Override
    public String execute() throws Exception {

        //Check if the user is logged in


        validation();

        return access_granted;
    }

    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }


    @Override
    public Object getModel() {
        return newUser;
    }

    public String validation()
    {
        access_granted = "success";
        return  access_granted;
    }







}
