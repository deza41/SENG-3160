package NewcastleConnectionsPrototype.Group4.models.view;

import java.util.Date;

/**
 * Created by simon janmaat on 8/08/2017.
 */
public class UserModel {
    private String username, firstName, lastName, phoneNumber, email, postCode, profileImageURL;
    private Date dateOfBirth;

    //Getters
    public String getUsername() {
        return username;
    }

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getProfileImageURL() {
        return profileImageURL;
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

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }
}