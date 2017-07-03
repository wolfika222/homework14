package com.util.wolfika222.utilities;

/**
 * Created by Hp_Workplace on 2017. 06. 24..
 */
public class QueryConstans {

    public static final String GET_ALL_USER = "SELECT * FROM customer";
    public static final String INSERT_CUSTOMER_TO_DATABASE = "INSERT INTO customer (username, password, fullname, email, city_id, company_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_USER__GET_ID = "DELETE FROM customer WHERE customer_id = ?";
    public static final String QUERY_ALL_PROJECT = "SELECT * FROM project";
    public static final String UPDATE_USER_IDENTITY_BY_ID = "UPDATE customer SET identity = ? WHERE customer_id = ?";
    public static final String ADD_NEW_CITY = "INSERT INTO city (city_name) VALUES (?)";
    public static final String ADD_NEW_PROJECT = "INSERT INTO project (project_name) VALUES (?)";
    public static final String LOGGED_IN_USER_CHECK = "SELECT * FROM customer WHERE username = ? AND password= ?";

}
