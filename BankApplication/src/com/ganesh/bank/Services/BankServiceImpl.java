package com.ganesh.bank.Services;

import com.ganesh.bank.dao.BankDao;
import com.ganesh.bank.dao.BankDaoImpl;
import com.ganesh.bank.dto.BankCustomer;
import com.ganesh.bank.factories.BankDaoFactory;

import java.sql.ResultSet;

public class BankServiceImpl implements BankService{
    @Override
    public String createAccount(BankCustomer bankCustomer) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.create(bankCustomer);
    }

    @Override
    public String loginAccount(int customerId, String customerPassword) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.login(customerId, customerPassword);
    }

    @Override
    public String searchId(int customerId) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.search(customerId);
    }

    @Override
    public String depositAmount(int customerId, float amount) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.deposit(customerId, amount);
    }

    @Override
    public float checkAmount(int customerId) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.check(customerId);
    }

    @Override
    public String withdrawAmount(int customerId, float amount) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.withdraw(customerId, amount);
    }

    @Override
    public ResultSet displayCustomerDetails(int customerId) {
        BankDao bankDao = new BankDaoImpl();
        return bankDao.display(customerId);
    }

    @Override
    public String updatePassword(int customerId, String password) {
        BankDao bankDao = BankDaoFactory.getBankDao();
        return bankDao.password(customerId, password);
    }
}

