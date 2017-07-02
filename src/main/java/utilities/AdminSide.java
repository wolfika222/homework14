package utilities;

import POJO.Customer;
import java.sql.PreparedStatement;
import com.util.ConnectionConfiguration;

import java.sql.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hp_Workplace on 2017. 06. 28..
 */
public class AdminSide implements IAdminSide {

    Scanner scanner = new Scanner(System.in);

    public void printAdminMenu() {
        System.out.println("*********************");
        System.out.println("**       Menü      **");
        System.out.println("**Get All User(1)  **");
        System.out.println("**Update Identity(2)*");
        System.out.println("**Add new city(3)  **");
        System.out.println("**Add new project(4)*");
        System.out.println("**Delete User(5)   **");
        System.out.println("**Exit(0)          **");
        System.out.println("*********************");

    }

    public void getAllUser() {
        List<Customer> customersList = new ArrayList<Customer>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstans.GET_ALL_USER);

            while (resultSet.next()) {
                Customer customer = new Customer();
                customer.setCustomerId(resultSet.getInt("customer_id"));
                customer.setUserName(resultSet.getString("username"));
                customer.setFullName(resultSet.getString("fullname"));
                customer.setEmail(resultSet.getString("email"));
                customer.setCityId(resultSet.getInt("city_id"));
                customer.setCompanyId(resultSet.getInt("company_id"));
                customer.setIdentity(resultSet.getInt("identity"));

                customersList.add(customer);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        for (Customer item : customersList) {
            System.out.println(item.toString());
        }
    }


    public void updateUser(Customer customer, int id) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.UPDATE_USER_IDENTITY_BY_ID);
            preparedStatement.setInt(1, customer.getIdentity());
            preparedStatement.setInt(2, id);

            preparedStatement.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void addNewCity() {

        System.out.println("Adj meg egy új várost");
        String newCityName = scanner.nextLine();

        try {
            Connection connection = ConnectionConfiguration.getConnection();
            Statement statement = connection.createStatement();

            String sql = "insert into city (city_name) values ('" + newCityName + "')";

            statement.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewProject() {

        try {
            System.out.println("Adj meg egy új projektet");
            String newProjectName = scanner.nextLine();
            Connection connection = ConnectionConfiguration.getConnection();
            Statement statement = connection.createStatement();

            String sql = "insert into project (project_name) values ('" + newProjectName + "')";

            statement.executeUpdate(sql);

        } catch (InputMismatchException e){
            System.out.println("Érvénytelen karakter!");
            addNewProject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteUser() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            System.out.println("Melyik usert szeretné törölni?");
            int id = scanner.nextInt();
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.DELETE_USER__GET_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        }catch (InputMismatchException e){
            System.out.println("Érvénytelen karakter!");
            deleteUser();
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void upIdentity() {

        System.out.println("Melyik usert szeretnéd updatelni?");
        int id = scanner.nextInt();
        System.out.println("Mire szeretnéd módosítani a jogosultságot? (admin = 0, user = 1)");
        int selectedIDentity = scanner.nextInt();

        if (selectedIDentity != 0 && selectedIDentity != 1) {
            System.out.println("Ilyen lehetőség nincs!");
        } else {
            Customer customer = new Customer(selectedIDentity);
            updateUser(customer, id);
        }
    }
}
