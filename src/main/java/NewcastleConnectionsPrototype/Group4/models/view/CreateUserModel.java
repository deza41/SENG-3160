package NewcastleConnectionsPrototype.Group4.models.view;

import com.opensymphony.xwork2.validator.annotations.EmailValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;

import java.util.Date;

/**
 * Created by simon janmaat on 17/05/2017.
 */
public class CreateUserModel {
    private String username, firstName, lastName, phoneNumber, email, password, checkPassword, postCode;
    Date dateOfBirth;

    //Getters
    @RequiredStringValidator
    public String getUsername() {
        return username;
    }
    @RequiredStringValidator
    public String getFirstName() {
        return firstName;
    }
    @RequiredStringValidator
    public String getLastName() {
        return lastName;
    }
    @RequiredStringValidator
    public String getPhoneNumber() {
        return phoneNumber;
    }
    @RequiredStringValidator
    @EmailValidator
    public String getEmail() {
        return email;
    }
    @RequiredStringValidator
    public String getPassword() {
        return password;
    }

    public String getPostCode() {
        return postCode;
    }
    @RequiredStringValidator
    public String getCheckPassword() {
        return checkPassword;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    //setters
    public void setUsername(String username) {
        this.username = username;
    }

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

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}

