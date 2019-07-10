package NewcastleConnectionsPrototype.Group4.actions.deal;

import NewcastleConnectionsPrototype.Group4.interfaces.RequiresDBConnection;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.BusinessuserRecord;
import NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.records.DealsRecord;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.jooq.DSLContext;
import org.jooq.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.Map;

import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Deals.DEALS;
import static NewcastleConnectionsPrototype.Group4.models.db.prototype.tables.Businessuser.BUSINESSUSER;
import static com.opensymphony.xwork2.Action.SUCCESS;

public class ViewDealInformationAction extends ActionSupport implements RequiresDBConnection,SessionAware, RequestAware {

    private Result<DealsRecord> dealsRecordResult;
    private Result<BusinessuserRecord> businessuserRecordResult;
    private DSLContext db;
    private int dealID;
    private double savings;
    private double savingsPercentage;
    private Map<String, Object> session, request;

    public String execute(){

        //HttpServletRequest request = ServletActionContext.getRequest();
        //HttpSession session = request.getSession();

        // create insert query to save deal
        this.dealsRecordResult = db.select()
                .from(DEALS)
                .where(DEALS.DEALID.eq(dealID))
                .fetchInto(DEALS);

        this.businessuserRecordResult = db.select()
                .from(BUSINESSUSER)
                .where(BUSINESSUSER.BUSINESSID.eq(dealsRecordResult.get(0).getBusinessid()))
                .fetchInto(BUSINESSUSER);

        db.update(DEALS).set(DEALS.TOTALVIEWS, DEALS.TOTALVIEWS.add(1))
                .where(DEALS.DEALID.eq(dealsRecordResult.get(0).getDealid()))
                .execute();

        savings = dealsRecordResult.get(0).getOldprice() - dealsRecordResult.get(0).getPrice();
        savingsPercentage = 100* savings/dealsRecordResult.get(0).getOldprice();

        return SUCCESS;
    }

    @Override
    public void setDSLContext(DSLContext ctx) {
        this.db = ctx;
    }

    public Result<DealsRecord> getDealsRecordResult() {
        return dealsRecordResult;
    }

    public Result<BusinessuserRecord> getBusinessuserRecordResult() {
        return businessuserRecordResult;
    }

    public int getDealID() {
        return dealID;
    }

    public void setDealID(int dealID) {
        this.dealID = dealID;
    }

    public double getSavings() {
        return savings;
    }

    public void setSavings(double savings) {
        this.savings = savings;
    }

    public double getSavingsPercentage() {
        return savingsPercentage;
    }

    public void setSavingsPercentage(double savingsPercentage) {
        this.savingsPercentage = savingsPercentage;
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
