package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;
import com.util.wolfika222.connection.ConnectionConfiguration;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by Hp_Workplace on 2017. 07. 02..
 */
public class LogInUser {

    private String userName;
    private String pwd;

    public Customer login() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Add meg a felhasználóneved: ");
        userName = scanner.nextLine();
        System.out.println("Add meg a jelszavad: ");
        pwd = scanner.nextLine();
        Customer logIn = new Customer();
        Connection connection = ConnectionConfiguration.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer WHERE username ='" + userName + "' AND password='" + pwd + "'");
            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

                logIn.setCustomerId(resultSet.getInt("customer_id"));
                logIn.setUserName(resultSet.getString("username"));
                logIn.setPassword(resultSet.getString("password"));
                logIn.setIdentity(resultSet.getInt("identity"));

                connection.close();

        } catch (SQLException e) {
            System.out.println("Rossz felhasználó név, vagy jelszó! --> újra");
            login();
        }
        return logIn;
    }
}
