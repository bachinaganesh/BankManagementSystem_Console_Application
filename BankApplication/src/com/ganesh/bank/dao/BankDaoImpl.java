package com.ganesh.bank.dao;

import com.ganesh.bank.dto.BankCustomer;
import com.ganesh.bank.factories.ConnectionFactory;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class BankDaoImpl implements BankDao{
    @Override
    public String create(BankCustomer bankCustomer) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query = "insert into bank values(" + bankCustomer.getCustomerId() + ", '" + bankCustomer.getCustomerName() + "', '"
                    + bankCustomer.getCustomerGender() + "', '" + bankCustomer.getCustomerEmail() + "', '" + bankCustomer.getCustomerPassword() +
                    "', " + bankCustomer.getCustomerAmount() + ", '" + bankCustomer.getCustomerAddress() + "')";
            int rowCount = statement.executeUpdate(query);
            if(rowCount == 1)
                status = "success";
            else
                status = "failure";
        }
        catch (Exception e)
        {
            status = "failure";
        }
        return status;
    }

    @Override
    public String login(int customerId, String customerPassword) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "select * from bank where cid = " + customerId + " and cpassword = '" + customerPassword + "'";
            ResultSet resultSet = statement.executeQuery(query);
            boolean flag = resultSet.next();
            if(flag)
                status = "verified";
            else
                status = "not verified";
        }
        catch (Exception e)
        {
            status = "not verified";
        }
        return status;
    }

    @Override
    public String search(int customerId) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query = "select * from bank where cid = " + customerId;
            ResultSet resultSet = statement.executeQuery(query);
            boolean flag = resultSet.next();
            if(flag)
                status = "existed";
            else
                status = "not existed";
        }
        catch (Exception e)
        {
            status = "failure";
        }
        return status;
    }

    @Override
    public String deposit(int customerId, float amount) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "update bank set camount = camount + " + amount + " where cid = " + customerId;
            int rowCount = statement.executeUpdate(query);
            if(rowCount == 1)
                status = "success";
            else
                status = "failure";
        }
        catch (Exception e)
        {
            status = "failure";
        }
        return status;
    }

    @Override
    public float check(int customerId) {
        float amount;
        try {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "select camount from bank where cid = " + customerId;
            ResultSet resultSet = statement.executeQuery(query);
            resultSet.next();
            amount = resultSet.getFloat("CAMOUNT");
        }
        catch (Exception e)
        {
            amount = 0.0f;
        }
        return amount;
    }

    @Override
    public String withdraw(int customerId, float amount) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "update bank set camount = camount - " + amount + " where cid = " + customerId;
            int rowCount = statement.executeUpdate(query);
            if(rowCount == 1)
            {
                status = "success";
            }
            else
            {
                status = "failure";
            }
        }
        catch (Exception e)
        {
            status = "failure";
        }
        return status;
    }

    @Override
    public ResultSet display(int customerId) {
        ResultSet resultSet;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "select cid, cname, cgender, cemail, caddress from bank where cid = " + customerId;
            resultSet  = statement.executeQuery(query);
        }
        catch (Exception e)
        {
            resultSet = null;
        }
        return resultSet;
    }

    @Override
    public String password(int customerId, String password) {
        String status;
        try
        {
            Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            String query;
            query = "update bank set cpassword = '" + password + "'";
            int rowCount = statement.executeUpdate(query);
            if(rowCount == 1)
            {
                status = "success";
            }
            else {
                status = "failure";
            }
        }
        catch (Exception e)
        {
            status = "failure";
        }
        return status;
    }
}
