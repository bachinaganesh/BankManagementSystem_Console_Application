package com.ganesh.bank.factories;

import com.ganesh.bank.dao.BankDao;
import com.ganesh.bank.dao.BankDaoImpl;

public class BankDaoFactory {
    private static BankDao bankDao = null;
    public static BankDao getBankDao()
    {
        bankDao = new BankDaoImpl();
        return bankDao;
    }
}
