package NewcastleConnectionsPrototype.Group4.models.view;

import java.text.DecimalFormat;

/**
 * Created by simon janmaat on 16/08/2017.
 */

public class BookingDealModel {

    private int dealID;
    private int quantity;
    private int numberOfChildren;
    private int numberOfAdults;
    private String dealTitle;
    private String dealImage;
    private String dealDescription;
    private double price, oldPrice, savings;
    private BusinessModel business;
    private String savingsPercentage;
    private DecimalFormat df = new DecimalFormat("#0");


    public BookingDealModel(int dealid, BusinessModel business, String dealtitle, String dealImage,  double price, double oldprice, String dealDescription, int numberOfChildren, int numberOfAdults)
    {
        this.dealID = dealid;
        this.business = business;
        this.dealTitle = dealtitle;
        this.dealImage = dealImage;
        this.price = price;
        this.oldPrice = oldprice;
        this.dealDescription = dealDescription;
        this.savings = oldPrice - this.price;
        this.savingsPercentage = this.savings + " ("+ df.format(this.savings / (oldPrice/100)) + "%)";
        this.quantity = 1;
        this.numberOfAdults = numberOfAdults;
        this.numberOfChildren = numberOfChildren;
    }

    //getters
    public int getNumberOfChildren() {
        return numberOfChildren;
    }


    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public String getDealDescription() {
        return dealDescription;
    }


    public int getDealID() {
        return dealID;
    }

    public String getDealTitle() {
        return dealTitle;
    }

    public String getDealImage() {
        return dealImage;
    }

    public double getPrice() {
        return price;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public double getSavings() {
        return savings;
    }

    public String getSavingsPercentage() {
        return savingsPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public BusinessModel getBusiness() {
        return business;
    }

    //setters
    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public void setQuantity(int quantity) {
        this.quantity += quantity;
    }

}
