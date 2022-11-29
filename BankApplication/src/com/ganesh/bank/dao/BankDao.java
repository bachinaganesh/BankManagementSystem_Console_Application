package com.ganesh.bank.dao;

import com.ganesh.bank.dto.BankCustomer;

import java.sql.ResultSet;

public interface BankDao {
    String create(BankCustomer bankCustomer);
    String login(int customerId, String customerPassword);
    String search(int customerId);
    String deposit(int customerId, float amount);
    float check(int customerId);
    String withdraw(int customerId, float amount);
    ResultSet display(int customerId);
    String password(int customerId, String password);
}
