package com.ganesh.bank.Services;

import com.ganesh.bank.dto.BankCustomer;

import java.sql.ResultSet;

public interface BankService {

    String createAccount(BankCustomer bankCustomer);
    String loginAccount(int customerId, String customerPassword);
    String searchId(int customerId);
    String depositAmount(int customerId, float amount);
    float checkAmount(int customerId);

    String withdrawAmount(int customerId, float amount);

    ResultSet displayCustomerDetails(int customerId);

    String updatePassword(int customerId, String password);
}
