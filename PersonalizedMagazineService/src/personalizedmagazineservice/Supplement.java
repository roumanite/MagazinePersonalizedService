package personalizedmagazineservice;

/**
 * This class represents Supplement which stores the supplement name and weekly cost of a supplement.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : Supplement.java
 * Purpose  : Contains the Supplement class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class Supplement
{
    private String name;
    private double weeklyCost;
    
    /**
     * Constructor for Supplement class
     */
    public Supplement(String name, double weeklyCost)
    {
        setName(name);
        setWeeklyCost(weeklyCost);
    }
    
    /**
     * Setter method to set a supplement name
     * 
     * @param name - name of supplement
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Setter method to set a weekly cost 
     * 
     * @param weeklyCost - weekly cost of supplement
     */
    public void setWeeklyCost(double weeklyCost)
    {
        this.weeklyCost = weeklyCost;
    }
    
    /**
     * Getter method to get the supplement name
     * 
     * @return String type of name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Getter method to get the supplement's weekly cost 
     * 
     * @return 
     */
    public double getWeeklyCost()
    {
        return weeklyCost;
    }
    
    /**
     * equals to compare name and cost
     * 
     * @param otherObject
     * @return true if equal, false if not equal
     */
    public boolean equals(Object otherObject)
    {
        boolean isEqual = false;
        if((otherObject != null) && otherObject instanceof Supplement)
        {
          Supplement otherSupp = (Supplement)otherObject;
          isEqual = (this.name.equals(otherSupp.name)) && (Double.compare(this.weeklyCost, otherSupp.weeklyCost) == 0);
        }
        return isEqual;
    }
}
