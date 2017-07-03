package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;


/**
 * Created by Hp_Workplace on 2017. 06. 28..
 */
public interface IAdminSide {

    void printAdminMenu();
    void getAllUser();
    void updateUser(Customer customer, int id);
    void addNewCity();
    void addNewProject();
    void deleteUser();
    void upIdentity();

}
