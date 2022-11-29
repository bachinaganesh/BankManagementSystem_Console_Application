package com.ganesh.bank.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

    private static Connection connection = null;
    public static Connection getConnection()
    {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL", "ganesh", "ganesh");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
    public static void close()
    {
        try {
            connection.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
