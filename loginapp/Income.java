package loginapp;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A class to represent income information.
 *
 */
public class Income implements Serializable {
    private int id;
    private String Userid;
    private LocalDate date;
    private float incomePrice;
    private String incomeSubject;
    private String typeCost;
    private float priceCost;
    private String costSubject;
    private float reminder;
    public int count = 1;
    Income() {
    }
    /**
     * Constructor that takes all of the income information as parameters.
     *
     * @param Userid The identifier of the user to whom the income belongs.
     * @param date The date on which the income was received.
     * @param incomePrice The amount of the income.
     * @param incomeSubject A description of the income.
     * @param typeCost A category for the cost associated with the income.
     * @param priceCost The amount of the cost associated with the income.
     * @param costSubject A description of the cost associated with the income.
     */
    public Income(String Userid ,LocalDate date, float incomePrice, String incomeSubject, String typeCost, float priceCost, String costSubject) {
        id =count;
        count++;
        this.Userid= Userid;
        this.date = date;
        this.incomePrice = incomePrice;
        this.incomeSubject = incomeSubject;
        this.typeCost = typeCost;
        this.priceCost = priceCost;
        this.costSubject = costSubject;

    }
    public int getId() {
       
        return id;
    }
    public void setId(int id)  {
            this.id =id;
    }
    
     public String getUserid() {
        return Userid;
    }
    public void setUserid(String Userid)  {
            this.Userid =Userid;
    }
    
    
    public LocalDate getDate() {
        return date;
    }

    public float getIncomePrice() {
        return incomePrice;
    }

    public String getIncomSubject() {
        return incomeSubject;
    }

    public String getTypeCost() {
        return typeCost;
    }

    public float getPriceCost() {
        return priceCost;
    }

    public String getCostSubject() {
        return costSubject;
    }
    public float getreminder() {
        return reminder;
    }
    

}
