package utilities;

import POJO.City;
import POJO.Company;
import POJO.Customer;
import POJO.Project;

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
