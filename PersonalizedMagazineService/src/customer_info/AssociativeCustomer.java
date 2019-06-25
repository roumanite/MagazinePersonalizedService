package customer_info;
import java.util.*;
import personalizedmagazineservice.Supplement;

/**
 * This class represents Associative Customer which stores the Payer object.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : AssociativeCustomer.java
 * Purpose  : Contains the AssociativeCustomer class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class AssociativeCustomer extends Customer
{
    private PayingCustomer payer;
    
    /**
     * Constructor for AssociativeCustomer class
     */
    public AssociativeCustomer(String fName, String lName, String email_in, ArrayList<Supplement> list)
    {
        super(fName, lName, email_in, list);
    }
    
    /**
     * Setter method to set payer object
     * 
     * @param cstmr - payer object
     */
    void setPayer(PayingCustomer cstmr)
    {
        payer = cstmr;
    }
    
    /**
     * Getter method to get a copy of payer object (prevent privacy leak)
     * 
     * @return PayingCustomer copied object of payer
     */
    public PayingCustomer getPayer()
    {
        if(payer!= null)
            return new PayingCustomer(payer.getFName(), payer.getLName(), payer.getEmailAddr(), payer.getSuppList(), payer.getPayMethod(), payer.getAssocList());
        else
            return null;
    }
}
