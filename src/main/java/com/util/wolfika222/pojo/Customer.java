package com.util.wolfika222.pojo;

import lombok.Data;

@Data
public class Customer {

    private int customerId;
    private String userName;
    private String password;
    private String fullName;
    private String email;
    private int cityId;
    private int companyId;
    private int identity;



public Customer() {
}

    public Customer(String userName, String password, String fullName, String email, int cityId, int companyId) {
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.cityId = cityId;
        this.companyId = companyId;
    }

    public Customer(int identity) {
        this.identity = identity;
    }

}
