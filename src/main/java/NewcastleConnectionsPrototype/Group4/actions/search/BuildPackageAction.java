package NewcastleConnectionsPrototype.Group4.actions.search;

import NewcastleConnectionsPrototype.Group4.models.beans.PackageBean;
import NewcastleConnectionsPrototype.Group4.models.view.SearchEventsModel;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by simon janmaat on 23/08/2017.
 */
public class BuildPackageAction extends ActionSupport implements SessionAware {

    private Map<String, Object> session;

    int numberOfAdults, numberOfChildren;
    //Date startDate, endDate;
    String startDate, endDate;
    Date date1, date2;

    public String execute() {

        PackageBean newPackage = (PackageBean) session.get("PackageBean");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);


        // try to fetch the date from date pickers, if it doesn't match exactly just set it to todays date and continue
        try{
            date1 = sdf.parse(startDate);
            date2 = sdf.parse(endDate);

            if(date1 != null) {
                newPackage.setStartDate(date1);
            }
            if(date2 != null) {
                newPackage.setEndDate(date2);
            }

        }catch(Exception e){
            date1 = new Date();
            date2 = new Date();
        }

        newPackage.setNumberOfAdults(numberOfAdults);
        newPackage.setNumberOfChildren(numberOfChildren);

        return SUCCESS;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    //getters

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    //setters
    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        this.session = map;
    }
}



