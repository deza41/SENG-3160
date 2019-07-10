package NewcastleConnectionsPrototype.Group4.actions.cart;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.Tables.USER;

/**
 * Created by blake on 15/05/2017.
 */
public class ShoppingCartAction extends ActionSupport implements RequiresDBConnection, SessionAware{

    private String firstName, lastName, phoneNumber, email, postCode;
    private String dateOfBirth;
    private Map<String, Object> session;

    private DSLContext db;

    public String execute(){
        PackageBean newPackage = (PackageBean) session.get("PackageBean");
        newPackage.setTotalCost();
        return SUCCESS;
    }

    public String checkOutNav(){
        UserBean user = (UserBean) session.get("userBean");

        if(user.isLoggedIn()){
            return "loggedin";
        }

        //guest has details sent here.
        if(firstName !=null && email != null && phoneNumber != null && lastName != null){
           String username = RandomStringUtils.random(8, true, true);
            try{
                    boolean notUnique = true;
                while(notUnique){
                    username = RandomStringUtils.random(8, true, true);

                    Record usernameResult = db.select(USER.fields())
                                                .from(USER)
                                                .where(USER.USERNAME.eq(username))
                                                .fetchOne();
                    if(usernameResult == null ){
                        notUnique = false;
                    }

                }

                //enter in dob as well please.
                Result result =  db.insertInto(USER, USER.USERNAME, USER.PASSWORD, USER.PROFILEIMAGEURL , USER.FIRSTNAME, USER.LASTNAME, USER.PHONENUMBER, USER.EMAIL, USER.POSTCODE)
                        .values( username ,"THIS IS A REALLY ODD PASSWORD", "NO IMAGE" , firstName, lastName, phoneNumber, email,postCode)
                        .returning(USER.USERID)
                        .fetch();

                //will always be getting an int.
                int userID = (int) result.getValue(0, result.field(0));

                user.setID(userID);
                user.setUsername(username);
                user.setRole("GUEST");

                return "guestformvalid";

            }catch(Exception e){
                System.out.println(e);
            }
        }

        return SUCCESS;
    }

    //getters
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getDateOfBirth() {return dateOfBirth;}

    //setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setDateOfBirth(String dateOfBirth){this.dateOfBirth = dateOfBirth;}

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}
