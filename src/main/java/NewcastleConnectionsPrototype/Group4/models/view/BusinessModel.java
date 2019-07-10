package NewcastleConnectionsPrototype.Group4.models.view;

import java.io.File;
import java.util.Date;
import java.util.List;


/**
 * Created by simon janmaat on 8/08/2017.
 */
public class BusinessModel {

    private String userName, firstName, lastName, phoneNumber, email, postCode, password, profileImageURL, businessName, street, unit, suburb, businessDescription, latitude, longitude, categoryName, number;
    private int businessID, categoryID, rating, businessViews;
    private Date businessOpen, businessClose;

    private List<File> files;
    private List<String> filesContentType;
    private List<String> filesFileName;

    public BusinessModel(){

    }


    public BusinessModel(int businessID, String email, Date businessOpen, Date businessClose, String phoneNumber, String profileImageURL, String businessName){
            this.businessID = businessID;
            this.email = email;
            this.businessOpen = businessOpen;
            this.businessClose = businessClose;
            this.phoneNumber = phoneNumber;
            this.profileImageURL = profileImageURL;
            this.businessName = businessName;
    }


    //getters
    public int getBusinessID() {
        return businessID;
    }

    public String getUserName() {
        return userName;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
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

    public String getBusinessName() {
        return businessName;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getUnit() {
        return unit;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public Date getBusinessOpen() {
        return businessOpen;
    }

    public Date getBusinessClose() {
        return businessClose;
    }

    public String getBusinessDescription() {
        return businessDescription;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public int getRating() {
        return rating;
    }

    public int getBusinessViews() {
        return businessViews;
    }

    public String getCategoryName() {
        return categoryName;
    }

    //setters
    public void setBusinessID(int businessID) {
        this.businessID = businessID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public void setBusinessOpen(Date businessOpen) {
        this.businessOpen = businessOpen;
    }

    public void setBusinessClose(Date businessClose) {
        this.businessClose = businessClose;
    }

    public void setBusinessDescription(String businessDescription) {
        this.businessDescription = businessDescription;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setBusinessViews(int businessViews) {
        this.businessViews = businessViews;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<String> getFilesContentType() {
        return filesContentType;
    }

    public List<String> getFilesFileName() {
        return filesFileName;
    }

    public void setFilesContentType(List<String> filesContentType) {
        this.filesContentType = filesContentType;
    }

    public void setFilesFileName(List<String> filesFileName) {
        this.filesFileName = filesFileName;
    }

}

