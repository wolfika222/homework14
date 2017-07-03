package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;
import com.util.wolfika222.pojo.Project;
import com.util.wolfika222.connection.ConnectionConfiguration;

import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Hp_Workplace on 2017. 06. 28..
 */
public class UserSide implements IUserSide {

    public void printUserMenu() {
        System.out.println("**********************");
        System.out.println("**       Menü       **");
        System.out.println("**Select Project(1) **");
        System.out.println("**Unselect Project(2)*");
        System.out.println("**Query Project(3)  **");
        System.out.println("**Exit(0)           **");
        System.out.println("**********************");
    }


    public void insert(Customer customer) {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.INSERT_CUSTOMER_TO_DATABASE);
            preparedStatement.setString(1, customer.getUserName());
            preparedStatement.setString(2, customer.getPassword());
            preparedStatement.setString(3, customer.getFullName());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getCityId());
            preparedStatement.setInt(6, customer.getCompanyId());
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

    public void selectProject() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Add meg az azonosítód: ");
        int id = scanner.nextInt();
        System.out.println("Melyik projectet választot?");
        int projectId = scanner.nextInt();

        try {
            Connection connection = ConnectionConfiguration.getConnection();
            Statement statement = connection.createStatement();

            String sql = "INSERT INTO customer_project (customer_id, project_id) VALUES (" + id + ", " + projectId + ")";

            statement.executeUpdate(sql);

        } catch (InputMismatchException e){
            System.out.println("Érvénytelen karakter!");
            selectProject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void unselectProject() {

        Scanner scanner = new Scanner(System.in);

        try {

            System.out.println("Add meg az azonosítód: ");
            int id = scanner.nextInt();
            System.out.println("Melyik projectet törlöd?");
            int projectId = scanner.nextInt();

            Connection connection = ConnectionConfiguration.getConnection();
            Statement statement = connection.createStatement();

            String sql = "DELETE FROM customer_project WHERE customer_id = " + id + " AND project_id = " + projectId + "";

            statement.executeUpdate(sql);

        } catch (InputMismatchException e){
            System.out.println("Érvénytelen karakter!");
            unselectProject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void queryProject() {

        List<Project> projectList = new ArrayList<Project>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(QueryConstans.QUERY_ALL_PROJECT);

            while (resultSet.next()) {
                Project project = new Project();
                project.setProjectId(resultSet.getInt("project_id"));
                project.setProjectName(resultSet.getString("projectname"));
                projectList.add(project);
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
        for (Project item : projectList) {
            System.out.println(item.toString());
        }
    }
}
