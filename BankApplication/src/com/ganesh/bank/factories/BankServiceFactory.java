package com.ganesh.bank.factories;

import com.ganesh.bank.Services.BankService;
import com.ganesh.bank.Services.BankServiceImpl;

public class BankServiceFactory {
    private static BankService bankService = null;
    public static BankService getBankService()
    {
        bankService = new BankServiceImpl();
        return bankService;
    }
}
