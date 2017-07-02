package utilities;

import POJO.City;
import POJO.Customer;
import POJO.Project;

import java.util.List;

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
