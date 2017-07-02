package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 * Created by Hp_Workplace on 2017. 06. 23..
 */
public class ConnectionConfiguration {

    public static Connection getConnection() {
        Connection connection = null;
        Properties p = getProperties();

        try {

            Class.forName(p.getProperty("DB_DRIVER_NAME"));
            connection = DriverManager.getConnection(p.getProperty("DB_URL"), p.getProperty("DB_USER"), p.getProperty("DB_PASSWORD"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static Properties getProperties() {
        Properties p = new Properties();
        FileInputStream fls;
        try {
            fls = new FileInputStream("C:\\Users\\Hp_Workplace\\IdeaProjects\\homework14\\src\\main\\resources\\db.properties");
            p.load(fls);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return p;
    }
}
