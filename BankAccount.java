import java.util.*;

public class BankAccount
{
    private String accHolder;
    private String accNumber;
    private double balance;
    private long phno;
    private ArrayList<String> transaction = new ArrayList<>();
    public BankAccount(String accHolder, String accNumber, double balance, long phno)
    {
        this.accHolder = accHolder;
        this.accNumber = accNumber;
        this.balance = balance;
        this.phno = phno;
    }
    public String getaccHolder()
    {
        return accHolder;
    }
    public String getaccNumber()
    {
        return accNumber;
    }
    public double getbalance()
    {
        return balance;
    }
    public long getphno()
    {
        return phno;
    }
    void display()
    {
        System.out.println("\n----ACCOUNT DETAILS----");

        System.out.println("Account Holder Name: " + accHolder);
        System.out.println("Account Number: " + accNumber);
        System.out.println("Balance: " + balance);
        System.out.println("Phone Number: " + phno);
    }
    void deposit(double amount)
    {
        if(amount > 0)
        {
            balance += amount;

            System.out.println(amount + " Deposited Successfully");

            transaction.add(amount + " Deposited Successfully");
        }
        else
        {
            System.out.println("Invalid Amount");
        }
    }
    void withdraw(double wamount)
    {
        if(wamount <= 0)
        {
            System.out.println("Invalid amount");
        }
        else if(wamount > balance)
        {
            System.out.println("Insufficient Balance");
        }
        else
        {
            balance -= wamount;

            System.out.println(wamount + " Withdrawn Successfully");

            transaction.add(wamount + " Withdrawn Successfully");
        }
    }
    void checkBalance()
    {
        System.out.println("Available Balance: " + balance);
    }
    void TransactionHistory()
    {
        System.out.println("\n----TRANSACTION HISTORY----");

        if(transaction.isEmpty())
        {
            System.out.println("No Transaction Found");
        }
        else
        {
            for(String transact : transaction)
            {
                System.out.println(transact);
            }
        }
    }
    public String toFileString()
    {
        return accHolder + "," +accNumber + "," +balance + "," +phno;
    }
}
