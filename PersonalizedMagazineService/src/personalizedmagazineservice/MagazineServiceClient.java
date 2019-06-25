package personalizedmagazineservice;
import customer_info.PaymentMethod;
import customer_info.PayingCustomer;
import customer_info.Customer;
import customer_info.AssociativeCustomer;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * This class contains a client program for a personalised magazine service.
 * <p>
 * Title    : ICT373 Assignment 1, Question 2 - Personalised Magazine Service
 * Author   : Madyarini Grace Ariel
 * Date     : 12/6/2019
 * Filename : MagazineServiceClient.java
 * Purpose  : A client program for a personalised magazine service
 * 
 * @author madya
 */
public class MagazineServiceClient 
{
    private static final Scanner kb = new Scanner(System.in);
    private static final String[] mList = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static ArrayList<Magazine> magList = new ArrayList<>();
    private static ArrayList<Supplement> suppList = new ArrayList<>();
    private static ArrayList<WeeklySupplement> wSuppList = new ArrayList<>();
    private static ArrayList<PayingCustomer> payList = new ArrayList<>();
    private static ArrayList<AssociativeCustomer> assocList = new ArrayList<>();
    private static final double defCost = 6.5;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        displayStudentDetails();
        startProgram();
        displayMenu();
        
        boolean notFinished = true;
        while(notFinished)
        {
            try
            {
                System.out.print(">> Type an option from the menu above (0-7) : ");
                int option = kb.nextInt();
                kb.nextLine();
                switch(option)
                {
                    case 0: // Exit the program
                        System.out.println(">> Exiting the program...");
                        notFinished = false;
                        break;
                    case 1: // Add a new magazine
                        exec1(); 
                        break;
                    case 2: // Add a new supplement
                        exec2();
                        break;
                    case 3: // Add a new weekly supplement
                        exec3();
                        break;
                    case 4: // Display email text containing the magazine for the customer(s)
                        exec4();
                        break;
                    case 5: // Display end of month email text for paying customer(s)
                        exec5();
                        break;
                    case 6: // Add a new customer
                        exec6();
                        break;
                    case 7: // Remove an existing customer
                        exec7();
                        break;
                    default:
                        System.out.println("!! Invalid option.");
                }
            }
            catch(InputMismatchException e)
            {
                kb.nextLine();
                System.out.println("!! Numeric value expected.");
            }
        }
    }
    
    /**
     * inputWk prompts user for week number and repeats the loop if it is not in the range 1-4. 
     * @param prompt String type prompt for the prompt message
     * @return int type week number
     */
    public static int inputWk(String prompt)
    {
        while(true)
        {
            System.out.print(">> "+prompt+" : ");
            int weekNum = kb.nextInt();
            kb.nextLine();
            if(weekNum < 1 || weekNum > 4)
                System.out.println("!! Value permitted is a number between 1-4.");
            else
                return weekNum;
        }
    }
    
    /**
     * displaySupp displays existing supplement types.
     */
    public static void displaySupp()
    {
        System.out.println(">> Existing supplement types : ");
        int n, diff, i;
        for(n = 1; n <= suppList.size(); n++)
        {
            String nm = suppList.get(n-1).getName() + "(USD "+suppList.get(n-1).getWeeklyCost()+")";
            diff = 25 - nm.length();
            System.out.print(" + "+nm);
            for(i = diff; i >= 0; i--)
            {
                System.out.print(" ");
            }
            if(n%2 == 0)
                System.out.println("");
        }
        if( (n-1)%2 != 0)
            System.out.println("");
    }
    
    /**
     * input1Supp prompts for supplement type and loops if the supplement is not found.
     * @return Supplement type object of the desired supplement
     */
    public static Supplement input1Supp()
    {
        Supplement sp_in = null;
        while(sp_in == null)
        {
            System.out.print(">> Supplement type : ");
            String suppName = kb.nextLine();
            if(suppName.isEmpty() || suppName.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            sp_in = findSupp(suppName);
            if(sp_in == null)
                System.out.println("!! Supplement not found.");
        }
        return sp_in;
    }
    
    /**
     * findSupp finds a supplement with the given name and returns null if not found
     * @param suppName String type supplement name
     * @return Supplement object if found, null if not found
     */
    public static Supplement findSupp(String suppName)
    {
        for(Supplement sp : suppList)
        {
            if(suppName.equals(sp.getName()))
                return sp;
        }
        return null;
    }
    
    /**
    * inputOpt prompts user to enter the option from permitted values and loops if the input
    * is not valid
    * @param prompt String type prompt for prompt message
    * @param options char[] type for possible options
    * @return char type of valid input
    */
    public static char inputOpt(String prompt, char[] options)
    {
        char ans = '0';
        while(true)
        {
            System.out.print(">> "+prompt+" : ");
            ans = kb.next().charAt(0);
            kb.nextLine();
            for(int n = 0; n < options.length; n++)
            {
                if(options[n] == ans)
                    return ans;
            }
            System.out.println("!! Invalid option.");
        }
    }
    
    /**
     * inputMonth prompts user for month and loops if the input is invalid
     * @param prompt String type of prompt message
     * @return int type of valid month
     */
    public static int inputMonth(String prompt)
    {
        boolean found = false;
        int month = 0;
        
        while(!found)
        {
            System.out.print(">> "+prompt+" : ");
            month = kb.nextInt();
            if(month < 1 || month > 12)
            {
                System.out.println("!! Invalid input. Value permitted is a number between 1-12.");
                continue;
            }
            found = true;
        }
        return month;
    }
    
    /**
     * displayMagEmail displays email text for specified customer, month, and week number
     * @param cstmr
     * @param month
     * @param weekNum 
     */
    public static void displayMagEmail(Customer cstmr, int year, int month, int weekNum)
    {
        boolean exist = false;
        boolean ws_exist;
        String monthWeek = "Magazine ("+mList[month-1] + " Week " + weekNum+", "+year+")";
        displayHeader(cstmr.getEmailAddr(), monthWeek);
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("Your magazine is ready to look at!");
        System.out.print("You are currently suscribed to these supplements : "); 
        ArrayList<Supplement> list = cstmr.getSuppList();
        if(list.size() == 0)
            System.out.println("-");
        for(int n=0; n < list.size(); n++)
        {
            System.out.print(list.get(n).getName());
            if(n == list.size()-1)
                System.out.println("");
            else
                System.out.print(", ");
        }
        for(Magazine mg : magList)
        {
            if(mg.getDate().getMonth().getValue() == month && mg.getWeekNum() == weekNum && mg.getDate().getYear() == year)
            {
                displayMagazine(mg);
                for(Supplement supp : cstmr.getSuppList())
                {
                    ws_exist = false;
                    for(WeeklySupplement ws : mg.getWeekSuppList())
                    {
                        if(supp.equals(ws.getSuppType()))
                        {
                            displayWSupp(ws);
                            ws_exist = true;
                        }
                    }
                    if(!ws_exist)
                        System.out.println(supp.getName()+" weekly supplement has not been added for this week.");
                }
                exist = true;
                break;
            }
        }
        if(!exist)
        {
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println("The magazine for Week "+weekNum+" has not been added.");
            System.out.println("---------------------------------------------------------------------------------------------------------------");
        }
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");
    }
    
    /**
     * displayHeader displays header for specified email and subject
     * @param email String type of email
     * @param subject String type of subject
     */
    public static void displayHeader(String email, String subject)
    {
        System.out.println("0000000000000000000000000000000000000000000000000000000000");
        System.out.println("from    : magazine@personalizedmagazineservice.com");
        System.out.println("to      : "+email);
        System.out.println("subject : "+subject);
        System.out.println("00000000000000000000000000000000000000000000000000000000000");
    }
    
    /**
     * displayWSupp displays weekly supplement of a magazine
     * @param supp WeeklySupplement type of specified weekly supplement to be displayed
     */
    public static void displayWSupp(WeeklySupplement supp)
    {
        System.out.print("[["+supp.getSuppType().getName()+"]] "+supp.getHeading());
        System.out.println("    by : "+supp.getAuthor());
        displayStr(supp.getContent());
        System.out.println("===============================================================================================================");
    }
    
    /**
     * displayMagazine displays a magazine
     * @param mg Magazine type of a specified weekly magazine to be displayed
     */
    public static void displayMagazine(Magazine mg)
    {
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        System.out.println("Date Published : "+mg.getDate()+ " (Week "+mg.getWeekNum()+")");
        System.out.println(mg.getHeading());
        displayStr(mg.getMainPart());
        System.out.println("---------------------------------------------------------------------------------------------------------------");
    }
    
    /**
     * displayStr formats and displays the content of magazine and weekly supplement
     * @param text String type of text
     */
    private static void displayStr(String text)
    {
        if(text.length() > 105)
        {
            for(int n = 0; n < text.length(); n = n + 105)
            {
                if( n + 105 < text.length())
                    System.out.println(text.substring(n, n+105));
                else
                    System.out.println(text.substring(n));
            }
        }
        else
            System.out.println(text);
    }
    
    /**
     * displayPaymentEmail displays payment due notification for paying customer
     * @param cstmr 
     */
    public static void displayPaymentEmail(PayingCustomer cstmr)
    {
        displayHeader(cstmr.getEmailAddr(), "Payment due for Magazine Subscription");
        System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
        double cost = Magazine.getWeeklyCost();
        System.out.println(" * "+cstmr.getEmailAddr());
        System.out.println("List of items suscribed for a week : ");
        System.out.println("    - Magazine (USD "+Magazine.getWeeklyCost()+")");
        for(Supplement sp : cstmr.getSuppList())
        {
            System.out.println("    - "+sp.getName()+" (USD "+sp.getWeeklyCost()+")");
            cost += sp.getWeeklyCost();
        }
        ArrayList<AssociativeCustomer> aList = cstmr.getAssocList();
        System.out.print("\nAssociative customer(s) : ");
        if(aList!=null)
        {
            if(aList.isEmpty())
                System.out.println("-");
            else
                System.out.println();
            
            for(AssociativeCustomer ac : aList)
            {
                System.out.println(" * "+ac.getEmailAddr());
                System.out.println("    List of items suscribed for a week : ");
                System.out.println("    - Magazine (USD "+Magazine.getWeeklyCost()+")");
                cost += Magazine.getWeeklyCost();
                for(Supplement sp : ac.getSuppList())
                {
                    System.out.println("    - "+sp.getName()+" (USD "+sp.getWeeklyCost()+")");
                    cost += sp.getWeeklyCost();
                }
                System.out.println();
            }
        }
        System.out.printf("Total payment due : USD %.2f x 4 = %.2f\n", cost, cost*4);
        System.out.print("Account charged : "+cstmr.getPayMethod().getCardNum().substring(0,3));
        int length = cstmr.getPayMethod().getCardNum().length() - 3;
        for(int i = 1; i <= length; i++)
            System.out.print("*");
        System.out.println("\n/////////////////////////////////////////////////////////////////////////////////////////////////////////////////\n");
    }
    
    /**
     * inputInfo prompts for a customer's information
     * @return String[] type of customer information
     */
    public static String[] inputInfo()
    {
        String email = "";
        String[] info = new String[3];
        boolean exist = true;
        while(exist)
        {
            System.out.print(">> Enter email address of the customer : ");
            email = kb.nextLine();
            
            if(email.isEmpty() || email.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
                
            Customer cstmr = findCustomer(email);
            if(cstmr == null)
                exist = false;
            else
                System.out.println("!! The email has already been registered in the system.");
        }
        info[0] = email;
        boolean hasDigit = true;
        while(hasDigit)
        {
            System.out.print(">> Enter first name of the customer : ");
            info[1] = kb.nextLine();
            
            if(info[1].isEmpty() || info[1].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            if(hasDigit(info[1]))
                System.out.println("!! Names cannot contain digits.");
            else
                hasDigit = false;
        }
        hasDigit = true;
        while(hasDigit)
        {
            System.out.print(">> Enter last name of the customer : ");
            info[2] = kb.nextLine();
            
            if(info[2].isEmpty() || info[2].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            if(hasDigit(info[2]))
                System.out.println("!! Names cannot contain digits.");
            else
                hasDigit = false;
        }
        
        return info;
    }
    
    public static boolean hasDigit(String str)
    {
        for(int i = 0; i < str.length(); i++)
        {
            if(Character.isDigit(str.charAt(i)))
            {
                return true;
            }
        }
        return false;
    }
    
    /**
     * inputSupp prompts for desired supplement type
     * @return ArrayList<Supplement> type of desired supplement list
     */
    public static ArrayList<Supplement> inputSupp()
    {
        boolean finish = false;
        boolean found = false;
        ArrayList<Supplement> spList = new ArrayList<Supplement>();
        displaySupp();
        while(!finish)
        {
            System.out.print(">> Enter name(s) of supplement(s) (type DONE to finish) : ");
            String suppName = kb.nextLine();
            
            if(suppName.isEmpty() || suppName.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            if(suppName.equals("DONE"))
                finish = true;
            else
            {
                Supplement sp = findSupp(suppName);
                if(sp!=null)
                {
                    found = true;
                    spList.add(sp);
                }
                else
                    found = false;
                if(!found)
                    System.out.println("!! Supplement not found.");
            }
        }
        return spList;
    }
    
    /**
     * inputDate prompts user for date information
     * @param prompt String type of prompt message
     * @return LocalDate type of date information
     */
    public static LocalDate inputDate(String prompt)
    {
        while(true)
        {
            try
            {
                System.out.print(">> Year of "+prompt+" : ");
                int year = kb.nextInt();
                int month = inputMonth("Month of "+prompt+" (1-12)");
                System.out.print(">> Day of "+prompt+" : ");
                int day = kb.nextInt();
                kb.nextLine();
                return LocalDate.of(year, month, day);
            }
            catch(DateTimeException e)
            {
                System.out.println("!! Invalid date.");
            }
        }
    }
    
    /**
     * inputPayment prompts user for payment method information
     * @return PaymentMethod type of payment method information
     */
    public static PaymentMethod inputPayment()
    {
        String[] data = new String[5];
        
        while(true)
        {
            System.out.print(">> Holder name : ");
            data[0] = kb.nextLine();

            if(data[0].isEmpty() || data[0].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            else
                break;
        }
        
        boolean found = false;
        boolean valid = false;
        while(!valid)
        {
            System.out.print(">> Card number : ");
            data[1] = kb.nextLine();
            
            if(data[1].isEmpty() || data[1].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            for (int i = 0; i < data[1].length(); i++)
            {
                if(!Character.isDigit(data[1].charAt(i)))
                    found = true;
            }
            if(found)
                System.out.println("!! Digits only are expected.");
            else
            {
                if(data[1].length()<16 || data[1].length()>19)
                    System.out.println("!! Number of digits is 16-19.");
                else
                    valid = true;
            }
        }
        while(true)
        {
            System.out.print(">> Card type : ");
            data[2] = kb.nextLine();
        
            if(data[2].isEmpty() || data[2].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            else
                break;
        }
        
        LocalDate dt = inputDate("the expiry date");
        System.out.print(">> CVV : ");
        int cvv = kb.nextInt();
        kb.nextLine();
        while(true)
        {
            System.out.print(">> Billing address : ");
            data[3] = kb.nextLine();
            if(data[3].isEmpty() || data[3].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            else
                break;
        }
        while(true)
        {
            System.out.print(">> Postal code : ");
            data[4] = kb.nextLine();
            if(data[4].isEmpty() || data[4].trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            else
                break;
        }
        
        return new PaymentMethod(data[0], data[1], data[2], dt, cvv, data[3], data[4]);
    }
    
    public static int inputYear(String prompt)
    {
        while(true)
        {
            System.out.print(">> "+prompt+" : ");
            int year = kb.nextInt();
            kb.nextLine();
            if(year >= 0)
                return year;
            else
                System.out.println("!! Year must not have a negative value.");
        }
    }
    
    public static void addCustomer(char type)
    {
        String[] info = inputInfo();
        ArrayList<Supplement> spList = inputSupp();
        
        if(type == 'p') // p means Paying Customer
        {
            char[] opt = {'y', 'n'};
            System.out.println(">> Recording payment method...");
            PaymentMethod payM = inputPayment();
            PayingCustomer pc = new PayingCustomer(info[1], info[2], info[0], spList, payM, null);
            payList.add(pc);
            System.out.println("## (1) new paying customer added. Total number of paying customer(s) : "+payList.size());
            char ans = 'y';
            while(ans != 'n')
            {
                ans = inputOpt("Do you wish to add any associative customer for this payer? (y/n)", opt);
                if(ans == 'y')
                {
                    char ans2 = inputOpt("Has the associative customer been registered in the system? (y/n)", opt);
                    if(ans2 == 'y')
                    {
                        Customer cstmr = null;
                        boolean finish = false;
                        while(!finish)
                        {
                            System.out.print(">> Email of associative customer : ");
                            String email = kb.nextLine();
                            
                            if(email.isEmpty() || email.trim().length() == 0)
                            {
                                System.out.println("!! Input is empty.");
                                continue;
                            }
                            
                            cstmr = findCustomer(email);
                            if(cstmr instanceof AssociativeCustomer)
                            {
                                finish = true;
                                AssociativeCustomer ac = (AssociativeCustomer)cstmr;
                                if(ac.getPayer() == null)
                                {
                                    pc.addAssoc(ac);
                                    System.out.println("## "+ac.getEmailAddr()+" is now paid by "+ac.getPayer().getEmailAddr());
                                }
                                else
                                {
                                    char ans3 = inputOpt("Remove "+ac.getPayer().getEmailAddr()+" as the payer? (y/n)", opt);
                                    if(ans3 == 'y')
                                    {
                                        System.out.println("## "+ac.getPayer().getEmailAddr()+" was removed from the payer role.");
                                        PayingCustomer old_payer = (PayingCustomer)findCustomer(ac.getPayer().getEmailAddr());
                                        old_payer.removeAssoc(ac);
                                        pc.addAssoc(ac);
                                        System.out.println("## "+ac.getPayer().getEmailAddr()+" was updated as the new payer.");
                                    }
                                    else
                                        System.out.println(">> Removal of payer role aborted.");
                                }
                            }
                            else if(cstmr instanceof PayingCustomer)
                                System.out.println("!! Customer is not an associative customer.");
                            else
                                System.out.println("!! Email not found.");
                        }
                    }
                    else
                    {
                        System.out.println(">> Adding a new associative customer...");
                        String[] info2 = inputInfo();
                        ArrayList<Supplement> spList2 = inputSupp();
                        AssociativeCustomer ac = new AssociativeCustomer(info2[1], info2[2], info2[0], spList2);
                        assocList.add(ac);
                        pc.addAssoc(ac);
                        System.out.println("## (1) new associative customer added. Total number of associative customer(s) : "+assocList.size());
                    }
                }
            }
        }
        else if(type == 'a') // a means Associative Customer with no paying customer stated yet
        {
            boolean exist = false;
            while(!exist)
            {
                System.out.print(">> Enter email of paying customer : ");
                String email = kb.nextLine();
                
                if(email.isEmpty() || email.trim().length() == 0)
                {
                    System.out.println("!! Input is empty.");
                    continue;
                }
                
                Customer cstmr = findCustomer(email);
                if(cstmr instanceof PayingCustomer)
                {
                    exist = true;
                    PayingCustomer pc = (PayingCustomer)cstmr;
                    AssociativeCustomer ac = new AssociativeCustomer(info[1], info[2], info[0], spList);
                    pc.addAssoc(ac);
                    assocList.add(ac);
                    System.out.println("## (1) new associative customer added. Total number of associative customer(s) : "+assocList.size());
                }
                else if(cstmr instanceof AssociativeCustomer)
                    System.out.println("!! Customer is not a paying customer.");
                else
                    System.out.println("!! Email has not been registered in the system.");
            }
        }
    }
    
    /**
     * exec1 allows for addition of a new magazine.
     */
    public static void exec1()
    {
        boolean done = false;
        System.out.println("++ Add a new magazine ++");
        LocalDate dt = inputDate("the published date");
        int weekNum = inputWk("Week of the magazine (1-4)");
        String heading, text;
        
        while(true)
        {
            System.out.print(">> Heading of the main part : ");
            heading = kb.nextLine();

            if(heading.isEmpty() || heading.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
            }
            else
                break;
        }
        
        while(true)
        {
            System.out.print(">> Text of the main part : ");
            text = kb.nextLine();
            
            if(text.isEmpty() || text.trim().length() == 0)
                System.out.println("!! Input is empty.");
            else
                break;
        }
        
        for(Magazine mag : magList)
        {
            LocalDate date = mag.getDate();
            if(mag.getWeekNum() == weekNum && date.getMonth().getValue() == dt.getMonth().getValue() && date.getYear() == dt.getYear())
            {
                char[] option = {'y', 'n'};
                char ans = inputOpt("Magazine for specified month and week number has existed. Overwrite the magazine? (y/n)", option);
                if(ans == 'y')
                {
                    Magazine newMgz = new Magazine(dt, weekNum, heading, text, mag.getWeekSuppList());
                    magList.remove(mag);
                    magList.add(newMgz);
                    System.out.println("## (1) new magazine was added and (1) removed. Total number of magazine(s) : "+magList.size());
                    done = true;
                    break;
                }
                else
                {
                    System.out.println(">> Creation of magazine aborted.");
                    done = true;
                    break;
                }
            }
        }
        if(!done)
        {
            Magazine newMgz = new Magazine(dt, weekNum, heading, text, null);
            magList.add(newMgz);
            System.out.println("## (1) new magazine was added. Total number of magazine(s) : "+magList.size());
        }
            
    }
    
    /**
     * exec2 allows for addition of a new supplement.
     */
    public static void exec2()
    {
        boolean exist = true;
        String suppName = "";
        double cost = 0;
        System.out.println("++ Add a new supplement ++");
        displaySupp();
        while(exist)
        {
            System.out.print(">> Supplement name : ");
            suppName = kb.nextLine();
            
            if(suppName.isEmpty() || suppName.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            Supplement sp = findSupp(suppName);
            if(sp == null)
                exist = false;
            else
                System.out.println("!! Supplement has existed.");
        }
        boolean invalid = true;
        while(invalid)
        {
            System.out.print(">> Weekly cost of supplement : ");
            cost = kb.nextDouble();
            if(cost<0)
                System.out.println("!! Cost must have a positive value.");
            else
                invalid = false;
        }
        Supplement supp = new Supplement(suppName, cost);
        suppList.add(supp);
        System.out.println(">> (1) new supplement was added. Total number of supplement(s) : " +suppList.size());
    }
    
    /**
     * exec3 allows addition of a new weekly supplement
     */
    public static void exec3()
    {
        Supplement sp_in = null;
        
        System.out.println("++ Add a new weekly supplement ++");
        displaySupp();
        sp_in = input1Supp();
        String heading, content, author;
        
        LocalDate dt = inputDate("the published date");
        
        while(true)
        {
            System.out.print(">> Heading : ");
            heading = kb.nextLine();
            if(heading.isEmpty() || heading.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
            }
            else
                break;
        }
        
        while(true)
        {
            System.out.print(">> Content : ");
            content = kb.nextLine();
            if(content.isEmpty() || content.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
            }
            else
                break;
        }
        
        while(true)
        {
            System.out.print(">> Author : ");
            author = kb.nextLine();
            if(author.isEmpty() || author.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
            }
            else
                break;
        }
        
        WeeklySupplement ws = new WeeklySupplement(sp_in, dt, heading, content, author);
        wSuppList.add(ws);
        
        char[] option = {'y', 'n'};
        char ans = inputOpt("Include it in a magazine? (y/n)", option);
        
        if(ans == 'y')
        {
            int year = inputYear("Year of which the magazine was published");
            int month = inputMonth("Month of which the magazine was published (1-12)");
            int weekNum = inputWk("Week of the magazine (1-4)");
            boolean found = false;
            for(Magazine mg : magList)
            {
                if(mg.getWeekNum() == weekNum && mg.getDate().getMonth().getValue() == month && mg.getDate().getYear() == year)
                {
                    mg.addWeekSupp(ws);
                    found = true;
                }
            }
            if(!found)
            {
                LocalDate dt2;
                System.out.println(">> Magazine has not been created. Adding a new magazine...");
                
                while(true)
                {
                    System.out.print(">> Day of the published date of the magazine : ");
                    int day = kb.nextInt();
                    kb.nextLine();
                    try
                    {
                        dt2 = LocalDate.of(year, month, day);
                        break;
                    }
                    catch(DateTimeException e)
                    {
                        System.out.println("!! Invalid date.");
                    }
                }
                ArrayList<WeeklySupplement> list = new ArrayList<>();
                list.add(ws);
                Magazine mg = new Magazine(dt2, weekNum, "", "", list);
                magList.add(mg);
                System.out.println("## (1) new empty magazine added. Total number of magazine(s) : " +magList.size());
            }
        }
        System.out.println("## (1) new weekly supplement added. Total number of weekly supplement(s) : "+wSuppList.size());
    }
    
    /**
     * exec4 displays email text containing the magazine for specified customer or all customers
     */
    public static void exec4()
    {
        boolean found = false;
        int month = 0;
        String email = "";
        Customer cstmr = null;
        System.out.println("++ Display email text containing magazine for customer(s) ++");
        
        while(!found)
        {
            System.out.print(">> Email of the customer (type ALL for all customers) : ");
            email = kb.nextLine();
            
            if(email.isEmpty() || email.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            if (!email.equals("ALL"))
            {
                cstmr = findCustomer(email);
                if(cstmr == null)
                {
                    System.out.println("!! Email not found.");
                    continue;
                }
            }
            found = true;
        }

        month = inputMonth("Month of which the email was sent (1-12)");
        int year = inputYear("Year of which the email was sent");
        
        System.out.println("");
        
        if(email.equals("ALL"))
        {
            for(PayingCustomer p_cstmr : payList)
            {
                System.out.println("Paying Customer : "+p_cstmr.getFName()+" "+p_cstmr.getLName()+" ("+p_cstmr.getEmailAddr()+")");
                for(int n = 1; n<5; n++)
                {
                    displayMagEmail(p_cstmr, year, month, n);
                }
            }
            for(AssociativeCustomer a_cstmr : assocList)
            {
                System.out.println("Associative Customer : "+a_cstmr.getFName()+" "+a_cstmr.getLName()+" ("+a_cstmr.getEmailAddr()+")");
                for(int n = 1; n<5; n++)
                {
                    displayMagEmail(a_cstmr, year, month, n);
                }
            }
        }
        else
        {
            for(int n = 1; n < 5; n++)
            {
                displayMagEmail(cstmr, year, month, n);
            }
        }
    }
    
    /**
     * exec5 displays end-of-month email text for specified paying customer or all customers
     */
    public static void exec5()
    {
        String email = "";
        System.out.println("++ Display end of month email text ++");
        boolean found = false;
        Customer cstmr = null;
        
        while(!found)
        {
            System.out.print("Email of the customer (type ALL for all customers) : ");
            email = kb.nextLine();
            
            if(email.isEmpty() || email.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            if(email.equals("ALL"))
                break;
            
            cstmr = findCustomer(email);
            
            if(cstmr instanceof AssociativeCustomer)
                System.out.println("!! Customer is not a paying customer.");
            else if (cstmr == null)
                System.out.println("!! Email has not been registered in the system.");
            else
                found = true;
        }
        
        System.out.println();
        
        if(email.equals("ALL"))
        {
            for(PayingCustomer pc : payList)
            {
                displayPaymentEmail(pc);
            }
        }
        else
        {
            PayingCustomer pc = (PayingCustomer)cstmr;
            displayPaymentEmail(pc);
        }
    }
    
    /**
     * exec6 allows for addition of a new customer
     */
    public static void exec6()
    {
        boolean isValid = false;
        System.out.println("++ Add a new customer ++");
        while(!isValid)
        {
            System.out.print(">> Which type does the new customer belong to? (p for Paying Customer, a for Associative Customer) : ");
            char type = kb.next().charAt(0);
            kb.nextLine();

            if(type == 'p')
            {
                isValid = true;
                addCustomer('p');
            }
            else if(type == 'a')
            {
                isValid = true;
                addCustomer('a');
            }
            else
                System.out.println("!! Invalid customer type.");
        }
    }
    
    /**
     * exec7 allows for removal of an existing customer.
     */
    public static void exec7()
    {
        boolean found = false;
        System.out.println("++ Remove an existing customer ++");
        
        while(!found)
        {
            System.out.print(">> Enter email address of the customer : ");
            String email = kb.nextLine();
            
            if(email.isEmpty() || email.trim().length() == 0)
            {
                System.out.println("!! Input is empty.");
                continue;
            }
            
            Customer cstmr = findCustomer(email);

            if(cstmr instanceof PayingCustomer)
            {
                PayingCustomer cstmr1 = (PayingCustomer)cstmr;
                for(AssociativeCustomer cstmr2 : cstmr1.getAssocList())
                {
                    cstmr1.removeAssoc(cstmr2);
                    System.out.println("## Payer of associative customer "+cstmr2.getEmailAddr()+" was removed.");
                }
                payList.remove(cstmr1);
                
                System.out.println("## Customer of email "+email+" was removed. Total number of paying customer(s) : "+payList.size());
                found = true;
            }
            else if(cstmr instanceof AssociativeCustomer)
            {
                AssociativeCustomer cstmr1 = (AssociativeCustomer)cstmr;
                PayingCustomer pc = cstmr1.getPayer();
                if (pc != null)
                {
                    for(PayingCustomer pc_cstmr : payList)
                    {
                        if(pc_cstmr.getEmailAddr().equals(pc.getEmailAddr()))
                        {
                            pc_cstmr.removeAssoc(cstmr1);
                            System.out.println("## Associative customer "+cstmr1.getEmailAddr()+" was removed from associative customer list of "+pc_cstmr.getEmailAddr());
                        }
                    }
                }
                assocList.remove(cstmr1);
                System.out.println("## Customer of email "+email+" was removed. Total number of associative customer(s) : "+assocList.size());
                found = true;
            }
            
            if(!found)
                System.out.println("!! Customer not found.");
        }
    }
    
    /**
     * findCustomer finds a customer through specified email address
     * @param email String type of email address
     * @return Customer object if found, null if not found
     */
    public static Customer findCustomer(String email)
    {
        email = email.toLowerCase();
        for(PayingCustomer cstmr : payList)
        {
            if(email.equals(cstmr.getEmailAddr()))
                return cstmr;
        }
        for(AssociativeCustomer cstmr : assocList)
        {
            if(email.equals(cstmr.getEmailAddr()))
                return cstmr;
        }
        return null;
    }
    
    /**
     * displayMenu displays menu options
     */
    public static void displayMenu()
    {
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(" ****************** Personalised Magazine Service Client ***************** ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                                    M E N U                                ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("(0) Exit");
        System.out.println("(1) Add a new magazine");
        System.out.println("(2) Add a new supplement");
        System.out.println("(3) Add a new weekly supplement");
        System.out.println("(4) Display the email text containing the magazine for the customer(s)");
        System.out.println("(5) Display end of month email text for paying customer(s)");
        System.out.println("(6) Add a new customer");
        System.out.println("(7) Remove an existing customer");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(">>   : System information message");
        System.out.println("##   : Success message");
        System.out.println("!!   : Error message");
        System.out.println("//// : Divider of email content");
        System.out.println("0000 : Divider of email header");
        System.out.println("@@@@ : End of a weekly magazine's header");
        System.out.println("---- : End of a weekly magazine's content");
        System.out.println("&&&& : End of a weekly supplement's header");
        System.out.println("==== : End of a weekly supplement of a weekly magazine\n");
    }
    
    /**
     * startProgram initializes dummy data
     */
    public static void startProgram()
    {   
        // Adding supplements...
        System.out.println(">> Generating dummy data for supplements...");
        suppList.add(new Supplement("Health and Fitness", 3.9));
        suppList.add(new Supplement("Beauty", 3));
        suppList.add(new Supplement("Anime", 1.9));
        suppList.add(new Supplement("Culinary", 2.9));
        System.out.println("## ("+suppList.size()+") new supplements added");
        
        // Adding magazines...
        System.out.println(">> Generating dummy data for magazines...");
        String text1 = "Jose Rodriguez, 22-year-old man died due to shooting in Milwaukee. Lexi Rivera was in the vehicle when the shooting happened.";
        magList.add(new Magazine(LocalDate.of(2019, 5, 7), 1, "Pregnant woman says boyfriend died shielding her in Milwaukee shooting: ‘We didn’t have a chance", text1, null));
       
        String text2 = "Kim Kardashian and Kanye West are now a happy family of six. Their baby boy has officially arrived via surrogate, confirmed on Twitter on Friday afternoon. \"He's here and he's perfect!\" tweeted the new mom of four. According to her, the baby was born on Thursday, weighing six pounds and nine ounces.";
        magList.add(new Magazine(LocalDate.of(2019, 5, 14), 2, "Kim Kardashian and Kanye West Welcome Baby No. 4 Via Surrogate", text2, null));
        
        String text3 = "Grumpy Cat, the feline famous on the internet for her permanent scowl, has died aged seven on Tuesday. The cat from Arizona had \"helped millions of people smile\".";
        magList.add(new Magazine(LocalDate.of(2019, 5, 21), 3, "Grumpy Cat internet legend dies", text3, null));
        
        String text4 = "When you play the Game of Thrones, you win or you die.\" After eight seasons, 73 episodes and a much-debated flurry of plot developments, that signature first-season line proved the key to unraveling the \"Game of Thrones\" finale.";
        magList.add(new Magazine(LocalDate.of(2019, 5, 28), 4, "'Game of Thrones' finale flies high, but can't quite stick the landing", text4, null));
        
        Magazine.setWeeklyCost(defCost);
        System.out.println("## ("+magList.size()+") new magazines added");
        
        // Adding weekly supplements...
        // Weekly supplements for Health and Fitness
        String sText1 = "White Smoothie Bowl consists of (1) 100 grams of frozen banana, (2) 50 grams of frozen soy milk, (3) vanilla extract, and (4) cinnamons."
        + "Toppings are up to your creativity but you can use dragonfruit, mixed nuts, and benniseeds.";
        String sText2 = "Whether your goal is to lose weight or get fit, cardio is an essential component to your workout program. You know that cardio is where you burn the most calories at one time and, not only that, cardio workouts strengthen your heart, lungs, and the muscles you're working.";
        String sText3 = "Hair porosity is the hair's ability to absorb and retain moisture, usually categorised as low, normal, and high. You can use #1 The Strand Test, #2 The Shedding Hair Test, and #3 The H2O test.";
        String sText4 = "Think of hair masks as a kind of high-powered hair conditioner. They have similar benefits—like deeply nourishing and strengthening hair—but are more intense and powerful. Hair masks can also target specific hair concerns. Incorporate them into your hair care routine and you may find that the results (think softer, stronger and shinier looking hair) are worth the extra time.";
        wSuppList.add(new WeeklySupplement(suppList.get(0),LocalDate.of(2019, 5, 7), "The Perfect Recipe for a Healthy Smoothie Bowl", sText1, "Yulia Baltschun"));
        wSuppList.add(new WeeklySupplement(suppList.get(0),LocalDate.of(2019, 5, 14), "Hiit Cardio vs Steady-State Cardio", sText2, "Yulia Baltschun"));
        wSuppList.add(new WeeklySupplement(suppList.get(0),LocalDate.of(2019, 5, 21), "How to Test Hair Porosity", sText3, "Dr. Oz"));
        wSuppList.add(new WeeklySupplement(suppList.get(0),LocalDate.of(2019, 5, 28), "Hair Mask for your Hair", sText4, "Dr. Oz"));
        
        // Weekly supplements for Beauty
        String sText5 = "In this week's Beauty Section, I will be reviewing various makeup products by MAKEOVER, from foundation to eyeshadows. Make Over Cosmetics is a professional cosmetics with complete range of colors, textures and functions for each product categories.";
        String sText6 = "Hello Beauty Lovers! In this week's Beauty Section, I will discuss about Whitening Teeth Kit that are advertised everywhere. They promise everlasting and instant results. Are they too good to be true? Let's find out!";
        String sText7 = "Male or female, young or old, acne-prone or oily skin, everyone needs skincare to maintain the health and moisture of the skin. This week we will be covering skincare routine steps : (1) double cleansing, (2) toner, (3) serum, (4) moisturize, and (5) suncare. ";
        String sText8 = "There are many skincare products that could give moisture to your dry skin. This week, I will review Senkai Beauty Collagen Facial Cleanser, Shu Uemura Cleansing Oil, Nivea Moisturizing Cream, Aloe Vera Cream, and so on!";
        wSuppList.add(new WeeklySupplement(suppList.get(1),LocalDate.of(2019, 5, 7), "MAKEOVER Brand Review",sText5, "Livjunkie"));
        wSuppList.add(new WeeklySupplement(suppList.get(1),LocalDate.of(2019, 5, 14), "Whitening Teeth Kit, Yay or Nay?", sText6, "Livjunkie"));
        wSuppList.add(new WeeklySupplement(suppList.get(1),LocalDate.of(2019, 5, 21), "Skincare Routine for Beginners", sText7, "Molita"));
        wSuppList.add(new WeeklySupplement(suppList.get(1),LocalDate.of(2019, 5, 28), "Skincare for Dry Skin", sText8, "Suhay"));
        
        // Weekly supplements for Anime
        String sText9 = "Inuyashiki (いぬやしき) is a Japanese science fiction manga series written and illustrated by Hiroya Oku. Inuyashiki debuted in the January 2014 issue of Kodansha's seinen manga magazine, Evening and ended in July 2017. Ten compilation volumes have been published.";
        String sText10 = "One-Punch Man is a Japanese superhero web manga created by ONE which began publication in early 2009. The series quickly went viral, surpassing 7.9 million hits in June 2012. The Japanese shortened name Wanpanman is a play on the long-running children's character Anpanman, wanpan being a contraction of wanpanchi.";
        String sText11 = "Hunter × Hunter (Japanese: ハンター×ハンター Hepburn: Hantā Hantā, abbreviated: HxH) is a Japanese manga series written and illustrated by Yoshihiro Togashi. It has been serialized in Weekly Shōnen Jump magazine since March 16, 1998, although the manga has frequently gone on extended hiatuses since 2006. As of October 2018, 380 chapters have been collected into 36 volumes by Shueisha. The story focuses on a young boy named Gon Freecss, who discovers that his father, Ging, is actually a world renowned Hunter, a licensed profession for those who specialize in, but are not limited to, fantastic pursuits such as locating rare or unidentified animal species, treasure hunting, surveying unexplored enclaves, or hunting down lawless individuals. ";
        String sText12 = "According to fandom.com, top 5 anime in 2018 are :\t1. Devil Man Crybaby\t2. A Place Further Than Universe\t3. My Hero Academia S3\t4. Banana Fish\t5. Darling in the Franxx";
        wSuppList.add(new WeeklySupplement(suppList.get(2),LocalDate.of(2019, 5, 7), "Inuyashiki Review", sText9, "2spooky"));
        wSuppList.add(new WeeklySupplement(suppList.get(2),LocalDate.of(2019, 5, 14), "One Punch Man Season 2 is out!", sText10, "2spooky"));
        wSuppList.add(new WeeklySupplement(suppList.get(2),LocalDate.of(2019, 5, 21), "Hunter x Hunter ver.1999 vs ver.2011", sText11, "Kamata Junko"));
        wSuppList.add(new WeeklySupplement(suppList.get(2),LocalDate.of(2019, 5, 28), "Top 5 Anime in 2018", sText12, "Kamata Junko"));
        
        String sText13 = "Ever wondered how to cook a fried chicken that is as tasty as KFC's? The ingredients needed are chicken drumsticks, garlic, flour, and spices.";
        String sText14 = "Ya Hua Bak Kut Teh is located at 593 Havelock Rd, Singapore. It is rated 4.1 out of 5 stars on Google Reviews.";
        String sText15 = "Shake Shack, the famous burger chain has opened its first outlet in Singapore. The first crowd was overwhelming with more than 100 metres queue.";
        String sText16 = "Ayam Penyet President is a chain of value-for-money casual dining restaurants serving authentic Indonesian cuisine.";
        // Weekly supplements for Culinary
        wSuppList.add(new WeeklySupplement(suppList.get(3),LocalDate.of(2019, 5, 7), "Fried Chicken Recipe", sText13, "Arnold Poernomo"));
        wSuppList.add(new WeeklySupplement(suppList.get(3),LocalDate.of(2019, 5, 14), "Ya Hua Bak Kut Teh Review", sText14, "Arnold Poernomo"));
        wSuppList.add(new WeeklySupplement(suppList.get(3),LocalDate.of(2019, 5, 21), "Shake Shack Jewel Changi Review", sText15, "Renatta Moeloek"));
        wSuppList.add(new WeeklySupplement(suppList.get(3),LocalDate.of(2019, 5, 28), "Ayam Penyet President Review", sText16, "Juna Rorimpande"));
        
        ArrayList<WeeklySupplement> week1 = new ArrayList<>();
        ArrayList<WeeklySupplement> week2 = new ArrayList<>();
        ArrayList<WeeklySupplement> week3 = new ArrayList<>();
        ArrayList<WeeklySupplement> week4 = new ArrayList<>();
        
        week1.add(wSuppList.get(0));
        week1.add(wSuppList.get(4));
        week1.add(wSuppList.get(8));
        week1.add(wSuppList.get(12));
        magList.get(0).setWeekSuppList(week1);
        
        week2.add(wSuppList.get(1));
        week2.add(wSuppList.get(5));
        week2.add(wSuppList.get(9));
        week2.add(wSuppList.get(13));
        magList.get(1).setWeekSuppList(week2);
        
        week3.add(wSuppList.get(2));
        week3.add(wSuppList.get(6));
        week3.add(wSuppList.get(10));
        week3.add(wSuppList.get(14));
        magList.get(2).setWeekSuppList(week3);
        
        week4.add(wSuppList.get(3));
        week4.add(wSuppList.get(7));
        week4.add(wSuppList.get(11));
        week4.add(wSuppList.get(15));
        magList.get(3).setWeekSuppList(week4);
        
        System.out.println("## ("+wSuppList.size()+") new weekly supplements added");
        
        // Adding associative customers...
        assocList.add(new AssociativeCustomer("Reagan", "Noel", "reagannoel@gmail.com", null));
        
        ArrayList<Supplement> sList = new ArrayList<>();
        sList.add(suppList.get(0));
        sList.add(suppList.get(1));
        sList.add(suppList.get(2));
        sList.add(suppList.get(3));
        assocList.add(new AssociativeCustomer("Siew", "Cheong", "siewcheong@kaplan.com", sList));
        sList.remove(0);
        assocList.add(new AssociativeCustomer("Killua", "Zoldyck", "killuazoldyck@gmail.com", sList));
        sList.remove(0);
        sList.add(suppList.get(0));
        assocList.add(new AssociativeCustomer("Gon", "Freecs", "gonfreecs@gmail.com", sList));
        
        // Adding paying customers...
        System.out.println(">> Generating dummy data for customers...");
        
        ArrayList<Supplement> sp1 = new ArrayList<>();
        sp1.add(suppList.get(1));
        sp1.add(suppList.get(2));
        sp1.add(suppList.get(3));
        PaymentMethod p1 = new PaymentMethod("Madyarini Grace", "1234567890123456", "VISA", LocalDate.of(2021,9,6), 876, "24 Beo Crescent", "130024");
        PaymentMethod p2 = new PaymentMethod("Momo Taro", "4234567890123456", "MasterCard", LocalDate.of(2021, 5, 5), 899, "32 Crimson Rose", "2322190");
        PaymentMethod p3 = new PaymentMethod("Shauf Khan", "5634567890123456", "VISA", LocalDate.of(2024,5,4), 900, "21 Selegie Road", "90092121");
        
        ArrayList<AssociativeCustomer> aList = new ArrayList<>();
        aList.add(assocList.get(0));
        
        ArrayList<AssociativeCustomer> aList2 = new ArrayList<>();
        aList2.add(assocList.get(1));
        aList2.add(assocList.get(2));
        aList2.add(assocList.get(3));
        
        payList.add(new PayingCustomer("Madyarini Grace", "Ariel", "madyagra@gmail.com", sp1, p1, aList));
        sp1.remove(2);
        payList.add(new PayingCustomer("Momo", "Taro", "mom_o@hotmail.com", sp1, p2, aList2));
        sp1.remove(0);
        payList.add(new PayingCustomer("Shauf", "Khan", "kh1234@yahoo.com", sp1, p3, null));
        
        System.out.println("## ("+payList.size()+") new paying customers added");
        System.out.println("## ("+assocList.size()+") new associative customers added");
    }
    
    /**
     * displayStudentDetails displays author's details
     */
    public static void displayStudentDetails()
    {
        System.out.println("---------- S T U D E N T  D E T A I L S ----------");
        System.out.println("    Name              : Madyarini Grace Ariel");
        System.out.println("    Student Number    : 33317512");
        System.out.println("    Mode of Enrolment : External");
        System.out.println("    Tutor Name        : Chong Siew Cheong");
        System.out.println("    Tutorial Time     : Friday/4:15-6:15 PM");
        System.out.println("--------------------------------------------------");
    }
}
