package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;

/**
 * Created by Hp_Workplace on 2017. 06. 28..
 */
public interface IUserSide {

    void printUserMenu();
    void insert(Customer customer);
    void selectProject();
    void unselectProject();
    void queryProject();
}
