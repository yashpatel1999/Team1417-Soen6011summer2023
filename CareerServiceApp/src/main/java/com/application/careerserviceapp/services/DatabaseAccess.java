package com.application.careerserviceapplication.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseAccess {
    static Connection connection = null;

    public static Statement getConnection() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver"); //JDBC Driver
        String url = "jdbc:mysql://localhost:3306/CSA";
        String user = "root";
        String password = "admin";

        connection = DriverManager.getConnection(url, user, password);
        if(connection.isClosed())
        {
            System.out.println("Connection unsuccesful");
        }

        Statement statement = connection.createStatement();

        return statement;
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
