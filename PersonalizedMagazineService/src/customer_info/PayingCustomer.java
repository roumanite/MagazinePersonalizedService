package customer_info;

import java.util.ArrayList;
import personalizedmagazineservice.Supplement;

/**
 * This class represents Paying Customer which stores the Payer object.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : PayingCustomer.java
 * Purpose  : Contains the Paying Customer class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class PayingCustomer extends Customer
{
    PaymentMethod payMethod;
    ArrayList<AssociativeCustomer> assocList = new ArrayList<>();
    
    /**
     * Constructor for PayingCustomer class
     */
    public PayingCustomer(String fName, String lName, String email, ArrayList<Supplement> list, PaymentMethod payMethod, ArrayList<AssociativeCustomer> list2)
    {
        super(fName, lName, email, list);
        setPayMethod(payMethod);
        setAssocList(list2);
    }
    
    /**
     * Setter method to set a payment method
     * 
     * @param payMethod - payment method object
     */
    public void setPayMethod(PaymentMethod payMethod) 
    {
        if(payMethod!=null)
            this.payMethod = new PaymentMethod(payMethod.getHolderName(), payMethod.getCardNum(), payMethod.getCardType(), payMethod.getExpiryDate(), payMethod.getCVV(), payMethod.getBillingAddr(), payMethod.getPostalCode());
        else
            this.payMethod = null;
    }
    
    /**
     * Setter method to set associative customer list
     * 
     * @param list - list of associative customer
     */
    private void setAssocList(ArrayList<AssociativeCustomer> list)
    {
        if(list!=null)
        {
            assocList = new ArrayList<>(list);
            for(AssociativeCustomer cstmr : assocList)
            {
                cstmr.setPayer(this);
            }
        }
    }
    
    /**
     * Setter method to add an associative customer and call setPayer
     * 
     * @param cstmr - AssociativeCustomer object to be added to the list
     */
    public void addAssoc(AssociativeCustomer cstmr)
    {
        if(cstmr instanceof AssociativeCustomer) // if not null
        {
            assocList.add(cstmr);
            cstmr.setPayer(this);
        }
    }
    
    /**
     * Setter method to remove an associative customer and call setPayer
     * 
     * @param cstmr - AssociativeCustomer object to be removed from the list
     */
    public void removeAssoc(AssociativeCustomer cstmr)
    {
        if(cstmr instanceof AssociativeCustomer) // if not null
        {
            assocList.remove(cstmr);
            cstmr.setPayer(null);
        }
    }
    
    /**
     * Getter method to get the copied payment method of the paying customer
     * (prevent privacy leak)
     * 
     * @return copied PaymentMethod object
     */
    public PaymentMethod getPayMethod()
    {
        if(payMethod!=null)
            return new PaymentMethod(payMethod.getHolderName(), payMethod.getCardNum(), payMethod.getCardType(), payMethod.getExpiryDate(), payMethod.getCVV(), payMethod.getBillingAddr(), payMethod.getPostalCode());
        else
            return null;
    }
    
    /**
     * Getter method to get the copied array list of associative customer
     * (prevent privacy leak)
     * 
     * @return copied array list of associative customer
     */
    public ArrayList<AssociativeCustomer> getAssocList()
    {
        return new ArrayList<>(assocList);
    }
}
