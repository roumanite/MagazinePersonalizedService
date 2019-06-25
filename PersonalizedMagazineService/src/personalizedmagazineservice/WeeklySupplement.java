package personalizedmagazineservice;
import java.time.LocalDate;

/**
 *This class represents a Weekly Supplement which is a section as a part of a weekly magazine
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : WeeklySupplement.java
 * Purpose  : Contains the WeeklySupplement class implementation as well as setter and getter methods.
 * 
 * @author madya
 */
public class WeeklySupplement
{
    private Supplement suppType;
    private String heading;
    private String content;
    private String author;
    private LocalDate datePublished;
    
    /**
     * Constructor with parameter for WeeklySupplement class
     * @param suppType Type of supplement which a weekly supplement belongs to
     * @param date Published date of weekly supplement
     * @param heading Heading of weekly supplement
     * @param content Content of weekly supplement
     * @param author Author of weekly supplement
     */
    public WeeklySupplement(Supplement suppType, LocalDate date, String heading, String content, String author)
    {
        setSuppType(suppType);
        setDate(date);
        setHeading(heading);
        setContent(content);
        setAuthor(author);
    }
    
    /**
     * Setter method to set supplement type of the weekly supplement
     * 
     * @param suppType 
     */
    public void setSuppType(Supplement suppType)
    {
        this.suppType = suppType;
    }
    
    /**
     * Setter method to set heading of the weekly supplement
     * 
     * @param heading 
     */
    public void setHeading(String heading)
    {
        this.heading = heading;
    }
    
    /**
     * Setter method to set published date of the weekly supplement
     * 
     * @param date 
     */
    public void setDate(LocalDate date)
    {
        this.datePublished = date;
    }
    
    /**
     * Setter method to set the content of the weekly supplement 
     * 
     * @param content 
     */
    public void setContent(String content)
    {
        this.content = content;
    }
    
    /**
     * Setter method to set the author of the weekly supplement
     * 
     * @param author 
     */
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    /**
     * Getter method to get copied object of supplement type
     * (prevent privacy leak)
     * 
     * @return Supplement type of supplement type
     */
    public Supplement getSuppType()
    {
        if(suppType != null)
            return new Supplement(suppType.getName(), suppType.getWeeklyCost());
        else
            return null;
    }
    
    /**
     * Getter method to get heading
     * 
     * @return String type of heading
     */
    public String getHeading()
    {
        return heading;
    }
    
    /**
     * Getter method to get content
     * 
     * @return String type of content
     */
    public String getContent()
    {
        return content;
    }
    
    /**
     * Getter method to get author of content
     * 
     * @return String type of author
     */
    public String getAuthor()
    {
        return author;
    }
    
    /**
     * Getter method to get date of content
     * 
     * @return LocalDate type of date published
     */
    public LocalDate getDate()
    {
        return datePublished;
    }
}
