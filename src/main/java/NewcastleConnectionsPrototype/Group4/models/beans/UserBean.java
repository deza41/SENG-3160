package NewcastleConnectionsPrototype.Group4.models.beans;

import java.io.Serializable;

/**
 * Created by blake on 13/05/2017.
 */
public class UserBean implements Serializable {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String contactNo;
    private String role;
    private int ID; //userID and businessID stored here
    private boolean loggedIn;

    public UserBean()
    {
        username = "";
        firstName = "";
        lastName = "";
        email = "";
        contactNo = "";
        role = "";
        ID = -1;
        loggedIn = false;
    }

    public UserBean(String username,
                    String firstName,
                    String lastName,
                    String email,
                    String contactNo,
                    String role,
                    int userID,
                    boolean loggedIn) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNo = contactNo;
        this.role = role;
        this.ID = userID;
        this.loggedIn = loggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
}
