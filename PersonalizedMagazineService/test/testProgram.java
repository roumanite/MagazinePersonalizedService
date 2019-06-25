
import customer_info.*;
import java.time.LocalDate;
import java.util.ArrayList;
import personalizedmagazineservice.Supplement;

/**
 * testProgram to test PayingCustomer, AssociativeCustomer
 * @author madya
 */
public class testProgram
{
    public static void main(String[] args) 
    {
        Supplement sp1 = new Supplement("Travelling", 3.5);
        PaymentMethod p1 = new PaymentMethod("John Smith", "1234567890123456", "VISA", LocalDate.of(2019,9,9), 678, "25 Beo Crescent", "500600");
        ArrayList<Supplement> spList = new ArrayList<>();
        spList.add(sp1);
        
        System.out.println("Creating a paying customer with email johnsmith@gmail.com and one associative customer.");
        AssociativeCustomer ac = new AssociativeCustomer("Magdalena", "", "magda@hotmail.com", spList);
        ArrayList<AssociativeCustomer> aList = new ArrayList<>();
        aList.add(ac);
        PayingCustomer pc = new PayingCustomer("John", "Smith", "johnsmith@gmail.com", spList, p1, aList);
        
        System.out.println("Listing associative customer of johnsmith@gmail.com before adding a new customer");
        ArrayList<AssociativeCustomer> acList = pc.getAssocList();
        for(int i=0; i<acList.size(); i++)
        {
            System.out.println(" - "+acList.get(i).getEmailAddr());
        }
        System.out.println("end of list\n");
        
        System.out.println("Testing adding an associative customer by calling addAssoc");
        AssociativeCustomer ac2 = new AssociativeCustomer("Mancy", "", "mancy@hotmail.com", spList);
        pc.addAssoc(ac2);
        System.out.println("Listing associative customer of johnsmith@gmail.com after changes");
        acList = pc.getAssocList();
        for(int i=0; i<acList.size(); i++)
        {
            System.out.println(" - "+acList.get(i).getEmailAddr());
        }
        
        System.out.println("Testing getPayer method");
        if(ac.getPayer() != null)
        {
            String pc_return = ac.getPayer().getEmailAddr();
            System.out.println("Payer of "+ac.getEmailAddr()+" is "+pc_return);
        }
            
        if(ac2.getPayer() != null)
        {
            String pc_return2 = ac2.getPayer().getEmailAddr();
            System.out.println("Payer of "+ac2.getEmailAddr()+" is "+pc_return2);
        }
        
        System.out.println("\nTesting of removing an associative customer by calling removeAssoc");
        System.out.println("removing magda@hotmail.com from johnsmith@gmail.com's associative customers");
        pc.removeAssoc(ac);
        System.out.println("Listing associative customer of johnsmith@gmail.com after changes");
        acList = pc.getAssocList();
        for(int i=0; i<acList.size(); i++)
        {
            System.out.println(" - "+acList.get(i).getEmailAddr());
        }
        System.out.println("Testing getPayer method after changes");
       
        if(ac.getPayer() != null)
        {
            String pc_return = ac.getPayer().getEmailAddr();
            System.out.println("Payer of "+ac.getEmailAddr()+" is "+pc_return);
        }
        else
        {
            System.out.println(ac.getEmailAddr()+" has no payer.");
        }
            
        if(ac2.getPayer() != null)
        {
            String pc_return2 = ac2.getPayer().getEmailAddr();
            System.out.println("Payer of "+ac2.getEmailAddr()+" is "+pc_return2);
        }
        else
        {
            System.out.println(ac2.getEmailAddr()+" has no payer.");
        }
    }
}

