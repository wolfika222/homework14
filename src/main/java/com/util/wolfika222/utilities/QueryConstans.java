package com.util.wolfika222.utilities;

/**
 * Created by Hp_Workplace on 2017. 06. 24..
 */
public class QueryConstans {

    public static final String GET_ALL_USER = "SELECT * FROM customer";
    public static final String INSERT_CUSTOMER_TO_DATABASE = "INSERT INTO customer (username, password, fullname, email, city_id, company_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_USER__GET_ID = "DELETE FROM customer WHERE customer_id = ?";
    public  static final String QUERY_ALL_PROJECT = "SELECT * FROM project";
    public static final String UPDATE_USER_IDENTITY_BY_ID = "UPDATE customer SET identity = ? WHERE customer_id = ?";

}
