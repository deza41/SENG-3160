package NewcastleConnectionsPrototype.Group4.interceptors;
import NewcastleConnectionsPrototype.Group4.models.beans.UserBean;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by simon janmaat on 19/07/2017.
 */
public class CheckLogedInIntercepter extends AbstractInterceptor{

    private List<String> allowedRoles = Collections.emptyList();
    private List<String> disallowedRoles = Collections.emptyList();

    public void setAllowedRoles(String roles)
    {
        allowedRoles = stringToList(roles);
    }

    public void setDisallowedRoles(String roles)
    {
        disallowedRoles = stringToList(roles);
    }

    public List<String> stringToList(String data)
    {
        if(data != null){
            String []list = data.split("[ ]*,[ ]*");
            return Arrays.asList(list);
        }else{
            return Collections.emptyList();
        }
    }

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {

        // Get a reference to the executing action
        Object action = invocation.getAction();

        Map<String, Object> session = invocation.getInvocationContext().getSession(); //get sessionMap
        HttpServletResponse response = ServletActionContext.getResponse();
            if (!isAllowed(session, invocation.getAction())) {
                return handleRejection(invocation);
            } else {
                return invocation.invoke();
            }
    }

    public boolean isAllowed(Map<String, Object> session, Object action)
    {
        UserBean user = (UserBean) session.get("userBean");

        for(String role : disallowedRoles) {
            if (user.getRole().equals(role)) {
                return false; //user is not in correct state
            }
        }

        if(allowedRoles.isEmpty()) {
            return true; //anyone can view page
        }

        for(String role: allowedRoles){
            if(user.getRole().equals(role)){
                return true; //user is in correct role
            }
        }

        return false;
    }

    public String handleRejection(ActionInvocation invocation) throws Exception {
        return "error";
    }

}
