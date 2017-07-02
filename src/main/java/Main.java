import POJO.City;
import POJO.Customer;
import com.util.ConnectionConfiguration;
import utilities.AdminSide;
import utilities.Registry;
import utilities.UserSide;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Hp_Workplace on 2017. 06. 23..
 */
public class Main {

    public static void main(String [] args) {

        Registry registry = new Registry();
        registry.run();


    }
}
