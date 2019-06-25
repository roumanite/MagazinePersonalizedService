package personalizedmagazineservice;
import java.time.LocalDate;
import java.util.ArrayList;
        
/**
 * This class represents Magazine which stores the information of a weekly magazine.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : Magazine.java
 * Purpose  : Contains the Magazine class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class Magazine 
{
    private String heading;
    private String mainPart;
    private LocalDate datePublished;
    private int weekNum = 0;
    private static double weeklyCost = 0;
    private ArrayList<WeeklySupplement> weekSuppList = new ArrayList<>();
    
    /**
     * Constructor with parameter for Magazine class
     * @param datePublished - magazine's published date
     * @param weekNum - week number (1-4) 
     * @param heading - heading of main part
     * @param mainPart - content of main part
     * @param weekSuppList - list of weekly supplements
     */
    public Magazine(LocalDate datePublished, int weekNum, String heading, String mainPart, ArrayList<WeeklySupplement> weekSuppList)
    {
        setDate(datePublished);
        setWeekNum(weekNum);
        setHeading(heading);
        setMainPart(mainPart);
        setWeekSuppList(weekSuppList);
    }
    
    /**
     * Setter method to set date published 
     * 
     * @param datePublished - magazine's published date
     */
    public void setDate(LocalDate datePublished)
    {
        this.datePublished = datePublished;
    }
    
    /**
     * Setter method to set week number
     * 
     * @param weekNum - week number (1-4)
     */
    public void setWeekNum(int weekNum)
    {
        if (weekNum > 0)
            this.weekNum = weekNum;
    }
    
    /**
     * Setter method for heading of main part
     * 
     * @param heading - heading of main part
     */
    public void setHeading(String heading)
    {
        this.heading = heading;
    }
    
    /**
     * Setter method for main part of magazine
     * 
     * @param mainPart - main part of magazine
     */
    public void setMainPart(String mainPart)
    {
        this.mainPart = mainPart;
    }
    
    /**
     * Setter method for weekly supplement list
     * 
     * @param weekSuppList - list of weekly supplements
     */
    public void setWeekSuppList(ArrayList<WeeklySupplement> weekSuppList)
    {
        if(weekSuppList!=null)
            this.weekSuppList = new ArrayList<>(weekSuppList);
    }
    
    /**
     * Setter method to add a weekly supplement to the list
     * 
     * @param ws - weekly supplement object to be added to the list
     */
    public void addWeekSupp(WeeklySupplement ws)
    {
        if(ws instanceof WeeklySupplement) // if not null
            weekSuppList.add(ws);
    }
 
    /**
     * Setter method to remove a weekly supplement from the list
     * 
     * @param ws - weekly supplement object to be removed from the list
     */
    public void removeWeekSupp(WeeklySupplement ws)
    {
        if(ws instanceof WeeklySupplement) // if not null
            weekSuppList.remove(ws);
    }
    
    /**
     * Setter method to set a weekly cost for all magazines
     * 
     * @param wCost - weekly cost
     */
    public static void setWeeklyCost(double wCost)
    {
        if(wCost > 0)
            weeklyCost = wCost;
    }
    
    /**
     * Getter method to return published date of the magazine
     * 
     * @return published date of the magazine
     */
    public LocalDate getDate()
    {
        return datePublished;
    }
    
    /**
     * Getter method to get week number of magazine
     * 
     * @return int type of week number
     */
    public int getWeekNum()
    {
        return weekNum;
    }
    
    /**
     * Getter method to get heading of main part
     * 
     * @return 
     */
    public String getHeading()
    {
        return heading;
    }
    
    /**
     * Getter method to get main part of magazine
     * 
     * @return 
     */
    public String getMainPart()
    {
        return mainPart;
    }
    
    /**
     * Getter method to get the list of weekly supplements
     * 
     * @return ArrayList<WeeklySupplement> type of copied list of weekly supplements 
     */
    public ArrayList<WeeklySupplement> getWeekSuppList()
    {
        return new ArrayList<>(weekSuppList);
    }
    
    /**
     * Getter method to get weekly cost of all magazines
     * 
     * @return double type of weekly cost
     */
    public static double getWeeklyCost()
    {
        return weeklyCost;
    }
}
