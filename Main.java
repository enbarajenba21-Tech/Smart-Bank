import java.util.*;
import java.io.*;
public class Main
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        ArrayList<BankAccount> accounts = new ArrayList<>();
        loadAccount(accounts);
        int choice = 0;
        do
        {
            System.out.println("\n----SMART BANK SYSTEM----");
            System.out.println("1. Create Account");
            System.out.println("2. View Accounts");
            System.out.println("3. Select Account");
            System.out.println("4. Search Account");
            System.out.println("5. Delete Account");
            System.out.println("6. Exit");
            try
            {
                choice = sc.nextInt();
                sc.nextLine();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Invalid Input! Please enter a number.");
                sc.nextLine();
                choice = 0;
                continue;
            }
            switch(choice)
            {
                case 1:
                    System.out.print("Enter Account Holder Name: ");
                    String accHolder = sc.nextLine();
                    System.out.print("Enter Account Number: ");
                    String accNumber = sc.nextLine();
                    double balance = 0;
                    try
                    {
                        System.out.print("Enter Initial Amount: ");
                        balance = sc.nextDouble();
                        if(balance < 0)
                        {
                            System.out.println("Balance cannot be negative.");
                            balance = 0;
                        }
                        sc.nextLine();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Invalid amount! Starting balance set to 0.");
                        sc.nextLine();
                    }
                    long phno = 0;
                    try
                    {
                        System.out.print("Enter Phone Number: ");
                        phno = sc.nextLong();
                        sc.nextLine();
                    }
                    catch(InputMismatchException e)
                    {
                        System.out.println("Invalid Phone Number!");
                        sc.nextLine();
                        break;
                    }
                    BankAccount newAccount = new BankAccount(accHolder,accNumber,balance,phno);
                    accounts.add(newAccount);
                    System.out.println("Account Created Successfully!");
                    break;
                case 2:
                    if(accounts.isEmpty())
                    {
                        System.out.println("No Accounts Exist.");
                    }
                    else
                    {
                        System.out.println("\n----ALL ACCOUNTS----");
                        for(BankAccount account : accounts)
                        {
                            System.out.println(account.getaccNumber()+ " - "+ account.getaccHolder());
                        }
                    }
                    break;
                case 3:
                    System.out.print("Enter Account Number: ");
                    String searchacc = sc.nextLine();
                    BankAccount selectacc = null;
                    for(BankAccount account : accounts)
                    {
                        if(account.getaccNumber().equals(searchacc))
                        {
                            selectacc = account;
                            break;
                        }
                    }
                    if(selectacc == null)
                    {
                        System.out.println("Account Not Found!");
                    }
                    else
                    {
                        int acchoice = 0;
                        do
                        {
                            System.out.println("\n====ACCOUNT MENU====");
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Account Details");
                            System.out.println("5. Transaction History");
                            System.out.println("6. Main Menu");
                            try
                            {
                                acchoice = sc.nextInt();
                                sc.nextLine();
                            }
                            catch(InputMismatchException e)
                            {
                                System.out.println("Invalid Choice!");
                                sc.nextLine();
                                acchoice = 0;
                                continue;
                            }
                            switch(acchoice)
                            {
                                case 1:
                                    try
                                    {
                                        System.out.print("Enter Amount to Deposit: ");
                                        double amount = sc.nextDouble();
                                        sc.nextLine();
                                        selectacc.deposit(amount);
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        System.out.println("Invalid Amount!");
                                        sc.nextLine();
                                    }

                                    break;


                                // -------- WITHDRAW --------

                                case 2:

                                    try
                                    {
                                        System.out.print(
                                            "Enter Amount to Withdraw: "
                                        );
                                        double wamount = sc.nextDouble();
                                        sc.nextLine();
                                        selectacc.withdraw(wamount);
                                    }
                                    catch(InputMismatchException e)
                                    {
                                        System.out.println("Invalid Amount!");
                                        sc.nextLine();
                                    }
                                    break;
                                case 3:
                                    selectacc.checkBalance();
                                    break;
                                case 4:
                                    selectacc.display();
                                    break;
                                case 5:
                                    selectacc.TransactionHistory();
                                    break;
                                case 6:
                                    System.out.println("Returning to Main Menu...");
                                    break;
                                default:
                                    System.out.println( "Invalid Choice!");
                            }
                        }
                        while(acchoice != 6);
                    }
                    break;
                case 4:
                    System.out.print( "Enter Account Number to Search: ");
                    String searchnum = sc.nextLine();
                    BankAccount found = null;
                    for(BankAccount account : accounts)
                    {
                        if(account.getaccNumber().equals(searchnum))
                        {
                            found = account;
                            break;
                        }
                    }
                    if(found != null)
                    {
                        System.out.println("\nAccount Found!");
                        found.display();
                    }
                    else
                    {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account Number to Delete: ");
                    String delNum = sc.nextLine();
                    BankAccount accdel = null;
                    for(BankAccount account : accounts)
                    {
                        if(account.getaccNumber().equals(delNum))
                        {
                            accdel = account;
                            break;
                        }
                    }
                    if(accdel != null)
                    {
                        accounts.remove(accdel);
                        System.out.println("Account Deleted Successfully!");
                    }
                    else
                    {
                        System.out.println("Account Not Found!");
                    }
                    break;
                case 6:
                    saveAccounts(accounts);
                    System.out.println("Thank You For Using Smart Bank System!");
                    break;
                default:
                   System.out.println("Invalid Choice!");
            }
        }
        while(choice != 6);
        sc.close();
    }
    public static void saveAccounts(ArrayList<BankAccount> accounts)
    {
        try
        {
            FileWriter writer = new FileWriter("accounts.txt");
            for(BankAccount account : accounts)
            {
                writer.write(account.toFileString() + "\n");
            }
            writer.close();
            System.out.println("Accounts Saved Successfully!");
        }
        catch(IOException e)
        {
            System.out.println("Accounts Cannot Be Saved!");
        }
    }
    public static void loadAccount(
            ArrayList<BankAccount> accounts)
    {
        try
        {
            BufferedReader read = new BufferedReader(new FileReader("accounts.txt"));
            String line;
            while((line = read.readLine()) != null)
            {
                String[] data = line.split(",");
                if(data.length == 4)
                {
                    String accHolder = data[0];
                    String accNumber = data[1];
                    double balance = Double.parseDouble(data[2]);
                    long phno = Long.parseLong(data[3]);
                    BankAccount account =  new BankAccount(accHolder,accNumber,balance,phno);
                    accounts.add(account);
                }
            }
            read.close();
            System.out.println( "Accounts Loaded Successfully!");
        }
        catch(FileNotFoundException e)
        {
            System.out.println("No Previous Accounts Found. Starting Fresh!");
        }
        catch(IOException e)
        {
            System.out.println("Error Loading Accounts!");
        }
    }
}
