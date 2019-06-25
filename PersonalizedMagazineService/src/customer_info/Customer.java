package customer_info;

import java.util.ArrayList;
import personalizedmagazineservice.Supplement;

/**
 * This class represents Customer which stores the information of a customer
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : Customer.java
 * Purpose  : Contains the Customer class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class Customer
{
    private String fName;
    private String lName;
    private String emailAddr;
    private ArrayList<Supplement> suppList = new ArrayList<>();
    
    /**
     * Constructor for Customer class
     */
    public Customer(String fName, String lName, String email, ArrayList<Supplement> list)
    {
        setFName(fName);
        setLName(lName);
        setEmailAddr(email);
        setSuppList(list);
    }
    
    /**
     * Setter method to set first name
     * 
     * @param fName - String type of first name
     */
    public void setFName(String fName)
    {
        this.fName = fName;
    }
    
    /**
     * Setter method to set last name
     * 
     * @param lName - String type of last name
     */
    public void setLName(String lName)
    {
        this.lName = lName;
    }
    
    /**
     * Setter method to set email address
     * 
     * @param emailAddr - String type of email address
     */
    public void setEmailAddr(String emailAddr)
    {
        this.emailAddr = emailAddr;
    }
    
    /**
     * Setter method to set customer's supplement list by copying the list in parameter
     * (prevent privacy leak)
     * If the parameter is null, the array will be an empty array as default.
     * 
     * @param list - ArrayList<Supplement> type of supplement list
     */
    public void setSuppList(ArrayList<Supplement> list)
    {
        if(list != null)
            suppList = new ArrayList<>(list);
    }
    
    /**
     * Setter method to add a supplement 
     * 
     * @param item - Supplement type of additional supplement
     */
    public void addSupp(Supplement item)
    {
        suppList.add(item);
    }
    
    /**
     * Setter method to remove a supplement
     * 
     * @param item - Supplement type of supplement to be removed from the list 
     */
    public void removeSupp(Supplement item)
    {
        suppList.remove(item);
    }
    
    /**
     * Getter method to get a first name
     * 
     * @return String type of first name
     */
    public String getFName()
    {
        return fName;
    }
    
    /**
     * Getter method to get a last name
     * 
     * @return String type of last name
     */
    public String getLName()
    {
        return lName;
    }
    
    /**
     * Getter method to get an email address
     * 
     * @return String type of email address
     */
    public String getEmailAddr()
    {
        return emailAddr;
    }
    
    /**
     * Getter method to get a copied version of array list of supplement
     * 
     * @return ArrayList<Supplement> type of copied array list
     */
    public ArrayList<Supplement> getSuppList()
    {
        return new ArrayList<>(suppList);
    }
}
