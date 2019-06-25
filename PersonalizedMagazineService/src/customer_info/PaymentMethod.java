package customer_info;
import java.time.LocalDate;

/**
 * This class represents PaymentMethod which stores the Payer object.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : PaymentMethod.java
 * Purpose  : Contains the PaymentMethod class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class PaymentMethod 
{
    private String holderName;
    private String cardNum;
    private String cardType;
    private LocalDate expiryDate;
    private int CVV; // A 3-digit number
    private String billingAddr;
    private String postalCode;
    
    /**
     * Constructor with parameter for PaymentMethod class
     * @param holderName - holder name written on card
     * @param cardNum - card number
     * @param cardType - card type
     * @param expiryDate - expiry date of card
     * @param CVV - card verification value
     * @param billingAddr - billing address
     * @param postalCode - postal code of billing address 
     */
    public PaymentMethod(String holderName, String cardNum, String cardType, LocalDate expiryDate, int CVV, String billingAddr, String postalCode)
    {
        setHolderName(holderName);
        setCardNum(cardNum);
        setCardType(cardType);
        setCVV(CVV);
        setExpiryDate(expiryDate);
        setBillingAddr(billingAddr);
        setPostalCode(postalCode);
    }
    
    /**
     * Setter method to set a holder name
     * 
     * @param holderName - holder name
     */
    public void setHolderName(String holderName)
    {
        this.holderName = holderName;
    }
    
    /**
     * Setter method to set a card number
     * 
     * @param cardNum - card number
     */
    public void setCardNum(String cardNum)
    {
        this.cardNum = cardNum;
    }
    
    /**
     * Setter method to set a card type
     * 
     * @param cardType - card type 
     */
    public void setCardType(String cardType)
    {
        this.cardType = cardType;
    }
    
    /**
     * Setter method to set an expiry date
     * 
     * @param expiryDate - expiry date of card
     */
    public void setExpiryDate(LocalDate expiryDate)
    {
        this.expiryDate = expiryDate;
    }
    
    /**
     * Setter method to set a card verification value
     * 
     * @param CVV - card verification value number
     */
    public void setCVV(int CVV)
    {
        this.CVV = CVV;
    }
    
    /**
     * Setter method to set a billing address
     * 
     * @param billingAddr - billing address
     */
    public void setBillingAddr(String billingAddr)
    {
        this.billingAddr = billingAddr;
    }
    
    /**
     * Setter method to set a postal code of billing address
     * 
     * @param postalCode - postal code of billing address
     */
    public void setPostalCode(String postalCode)
    {
        this.postalCode = postalCode;
    }
    
    /**
     * Getter method to get holder name of the payment method
     * 
     * @return String type of holder name
     */
    public String getHolderName()
    {
        return holderName;
    }
    
    /**
     * Getter method to get card number of the payment method
     * 
     * @return String type of card number
     */
    public String getCardNum()
    {
        return cardNum;
    }
    
    /**
     * Getter method to get a card type of the payment method
     * 
     * @return String type of card type
     */
    public String getCardType()
    {
        return cardType;
    }
    
    /**
     * Getter method to get expiry date of card
     * 
     * @return LocalDate object of expiry date
     */
    public LocalDate getExpiryDate()
    {
        return expiryDate;
    }
    
    /**
     * Getter method to get card verification value
     * 
     * @return int type of CVV
     */
    public int getCVV()
    {
        return CVV;
    }
    
    /**
     * Getter method to get billing address 
     * 
     * @return String type of billing address
     */
    public String getBillingAddr()
    {
        return billingAddr;
    }
    
    /**
     * Getter method to get postal code of billing address
     * 
     * @return String type of postal code
     */
    public String getPostalCode()
    {
        return postalCode;
    }
}
