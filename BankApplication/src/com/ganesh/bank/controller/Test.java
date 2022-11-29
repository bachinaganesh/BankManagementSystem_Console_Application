package com.ganesh.bank.controller;

import com.ganesh.bank.Services.BankService;
import com.ganesh.bank.dto.BankCustomer;
import com.ganesh.bank.factories.BankServiceFactory;
import com.ganesh.bank.factories.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Scanner;

public class Test {
    static Scanner scanner;
    static int option;

    static int customerId;
    static String customerPassword;
    static String status;

    static BankCustomer bankCustomer;


    public static void displayModules()
    {
        System.out.println();
        System.out.println("1. Create Bank Account");
        System.out.println("2. Login Into Bank Account");
        System.out.println("3. Exit");
        System.out.println();
    }
    public static void displayUpdationModules()
    {
        System.out.println();
        System.out.println("1. Deposit Money");
        System.out.println("2. Withdraw Money");
        System.out.println("3. Check Balance");
        System.out.println("4. Display Customer Details");
        System.out.println("5. Set New Password");
        System.out.println("6. Logout");
        System.out.println();
    }
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        BankService bankService = BankServiceFactory.getBankService();
        while (true)
        {
            displayModules();
            System.out.print("Choose the above option - ");
            option = scanner.nextInt();

            switch (option)
            {
                case 1:
                    bankCustomer = new BankCustomer();
                    System.out.print("Enter the customer id - ");
                    customerId = scanner.nextInt();
                    status = bankService.searchId(customerId);
                    if(status.equalsIgnoreCase("existed"))
                    {
                        System.out.println("--------------------- Customer Already Existed ---------------------");
                    }
                    else if(status.equalsIgnoreCase("failure"))
                    {
                        System.out.println("-------------------- Customer Searching Error Occurred -------------------");
                    }
                    else
                    {
                        bankCustomer.setCustomerId(customerId);

                        System.out.print("Enter the customer name - ");
                        bankCustomer.setCustomerName(scanner.next());

                        System.out.print("Enter the Customer gender - ");
                        bankCustomer.setCustomerGender(scanner.next());

                        System.out.print("Enter the customer email - ");
                        bankCustomer.setCustomerEmail(scanner.next());

                        System.out.print("Enter the customer password - ");
                        bankCustomer.setCustomerPassword(scanner.next());

                        System.out.print("Enter the customer address - ");
                        bankCustomer.setCustomerAddress(scanner.next());

                        status = bankService.createAccount(bankCustomer);
                        if(status.equalsIgnoreCase("success"))
                        {
                            System.out.println("---------------------- Customer Account Created Successfully ----------------------------");
                        }
                        else
                        {
                            System.out.println("---------------------- Customer Account Created Failure ----------------------------");
                        }
                    }
                    bankCustomer = null;
                    break;
                case 2:
                    bankCustomer = new BankCustomer();
                    System.out.print("Enter the customer id - ");
                    customerId = scanner.nextInt();
                    System.out.print("Enter the customer password - ");
                    customerPassword = scanner.next();
                    status = bankService.loginAccount(customerId, customerPassword);
                    if(status.equalsIgnoreCase("verified"))
                    {
                        System.out.println("----------------------- You Successfully Logged Into Your Account -------------------------");
                        bankCustomer.setCustomerStatus(true);
                        float amount;
                        while (true)
                        {
                            displayUpdationModules();
                            System.out.print("Choose the above option - ");
                            option = scanner.nextInt();
                            switch (option)
                            {
                                case 1:
                                    System.out.print("Enter the amount to be deposit - ");
                                    amount = scanner.nextFloat();
                                    if(amount > 0.0f)
                                    {
                                        status = bankService.depositAmount(customerId, amount);
                                        if(status.equalsIgnoreCase("success"))
                                        {
                                            System.out.println("----------------- Amount " + amount + " Is Deposited Into Your Account Successfully -----------------");
                                        }
                                        else
                                        {
                                            System.out.println("---------------- Amount Deposition Error Occurred --------------");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("--------------------- Invalid Amount Entered --------------------");
                                    }
                                    break;
                                case 2:
                                    System.out.print("Enter the amount to withdraw - ");
                                    amount = scanner.nextFloat();
                                    float balance = bankService.checkAmount(customerId) - amount;
                                    if(balance >= 500.00)
                                    {
                                        status = bankService.withdrawAmount(customerId, amount);
                                        if(status.equalsIgnoreCase("success"))
                                        {
                                            System.out.println("---------------- Amount " + amount + " successfully withdrawn and remaining balance is " +  balance);
                                        }
                                        else
                                        {
                                            System.out.println("----------------------- Amount withdrawn Failure Occurred ----------------------");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("---------------- Sorry you don't have enough balance. It is compulsory to maintain minimum balance 500.00 -------------");
                                    }
                                    break;
                                case 3:
                                    amount = bankService.checkAmount(customerId);
                                    System.out.println("----------------- Your Account Balance is " + amount + " -----------------");
                                    break;
                                case 4:
                                    ResultSet resultSet = bankService.displayCustomerDetails(customerId);
                                    if(resultSet == null)
                                    {
                                        System.out.println("------------- Fetching Data Error Occurred ---------------");
                                    }
                                    else
                                    {
                                        System.out.println();
                                        System.out.println("CID\t\tCNAME\t\tCGENDER\t\tCEMAIL\t\tCADDRESS");
                                        System.out.println("=============================================================================");
                                        try {
                                            while (resultSet.next()) {
                                                System.out.print(resultSet.getInt("CID") + "\t");
                                                System.out.print(resultSet.getString("CNAME") + "\t");
                                                System.out.print(resultSet.getString("CGENDER") + "\t");
                                                System.out.print(resultSet.getString("CEMAIL") + "\t");
                                                System.out.print(resultSet.getString("CADDRESS") + "\n");
                                            }
                                            System.out.println();
                                        }
                                        catch (Exception e)
                                        {
                                            System.out.println("------------- Data Error Occurred ------------");
                                        }
                                    }
                                    break;
                                case 5:
                                    System.out.print("Do you want change password press 1 - ");
                                    option = scanner.nextInt();
                                    if(option == 1)
                                    {
                                        String newPassword;
                                        String newConfirmPassword;
                                        System.out.print("Enter the new password - ");
                                        newPassword = scanner.next();
                                        System.out.print("Enter the same password again - ");
                                        newConfirmPassword = scanner.next();
                                        if(newPassword.equals(newConfirmPassword))
                                        {
                                            status = bankService.updatePassword(customerId, newPassword);
                                            if(status.equalsIgnoreCase("success"))
                                            {
                                                System.out.println("------------------ Password Is Successfully Updated -------------------");
                                            }
                                            else
                                            {
                                                System.out.println("------------------- Password Updation Failure ------------------");
                                            }
                                        }
                                        else
                                        {
                                            System.out.println("---------------- Both password's doesn't match ----------------");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Thank you for you aren't interesting to change the password");
                                    }
                                    break;
                                case 6:
                                    System.out.println("--------------- Successfully LoggedOut Into Your Account ---------------");
                                    bankCustomer.setCustomerStatus(false);
                                    break;
                            }
                            if(!bankCustomer.isCustomerStatus())
                                break;
                        }
                    }
                    else
                    {
                        System.out.println("---------------------- Invalid CustomerId or CustomerPassword Please Check Once -----------------");
                    }
                    break;
                case 3:
                    try {
                        ConnectionFactory.close();
                        System.out.println("------------------ Thank You For Using Bank Application Visit Again :) -----------------");
                        System.exit(0);
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("------------------------ Invalid Option Chosen --------------------------");
            }
        }
    }
}
