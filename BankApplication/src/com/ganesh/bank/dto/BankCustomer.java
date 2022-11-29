package com.ganesh.bank.dto;

public class BankCustomer {
    int customerId;
    String customerName;
    String customerGender;
    String customerEmail;
    String customerPassword;
    String customerAddress;
    float customerAmount = 500.0f;

    boolean customerStatus = false;

    public boolean isCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(boolean customerStatus) {
        this.customerStatus = customerStatus;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerGender() {
        return customerGender;
    }

    public void setCustomerGender(String customerGender) {
        this.customerGender = customerGender;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public float getCustomerAmount() {
        return customerAmount;
    }

    public void setCustomerAmount(float customerAmount) {
        this.customerAmount = customerAmount;
    }
}
