package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;

import com.util.wolfika222.connection.ConnectionConfiguration;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

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
        System.out.println("***********************");
        System.out.println("**        Menü       **");
        System.out.println("**Get All User(1)    **");
        System.out.println("**Update Identity(2) **");
        System.out.println("**Add new city(3)    **");
        System.out.println("**Add new project(4) **");
        System.out.println("**Delete User(5)     **");
        System.out.println("**Create Apache POI(6)*");
        System.out.println("**Exit(0)            **");
        System.out.println("***********************");

    }

    public List<Customer> getAllUser() {
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

        return customersList;
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
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.ADD_NEW_CITY);
            preparedStatement.setString(1, newCityName);

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

    public void addNewProject() {

        System.out.println("Adj meg egy új projektet");
        String newProjectName = scanner.nextLine();

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.ADD_NEW_PROJECT);
            preparedStatement.setString(1, newProjectName);

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

    public void deleteUser() {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            System.out.println("Melyik usert szeretné törölni?");
            int id = scanner.nextInt();
            connection = ConnectionConfiguration.getConnection();
            preparedStatement = connection.prepareStatement(QueryConstans.DELETE_USER_GET_ID);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (InputMismatchException e) {
            System.out.println("Érvénytelen karakter!");
            deleteUser();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
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

    public void writeToExcel() {

        try {

            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet sheet = workbook.createSheet("Customers");

            Row heading = sheet.createRow(0);
            heading.createCell(0).setCellValue("Id");
            heading.createCell(1).setCellValue("Username");
            heading.createCell(2).setCellValue("Password");
            heading.createCell(3).setCellValue("FullName");
            heading.createCell(4).setCellValue("Email");
            heading.createCell(5).setCellValue("City");
            heading.createCell(6).setCellValue("Company");
            heading.createCell(7).setCellValue("Identity");
            for (int i = 0; i < 8; i++) {
                CellStyle rowCellStyle = workbook.createCellStyle();
                Font font = workbook.createFont();
                font.setBold(true);
                font.setFontName(HSSFFont.FONT_ARIAL);
                font.setFontHeightInPoints((short) 11);
                rowCellStyle.setFont(font);
                heading.getCell(i).setCellStyle(rowCellStyle);
            }
            
            int r = 1;
            for (Customer item: getAllUser()) {
                Row row = sheet.createRow(r);

                Cell cellId = row.createCell(0);
                cellId.setCellValue(item.getCustomerId());

                Cell cellUsername = row.createCell(1);
                cellUsername.setCellValue(item.getUserName());

                Cell cellPasswd = row.createCell(2);
                cellPasswd.setCellValue(item.getPassword());

                Cell cellFullname = row.createCell(3);
                cellFullname.setCellValue(item.getFullName());

                Cell cellEmail = row.createCell(4);
                cellEmail.setCellValue(item.getEmail());

                Cell cellCity = row.createCell(5);
                cellCity.setCellValue(item.getCityId());

                Cell cellCompany = row.createCell(6);
                cellCompany.setCellValue(item.getCompanyId());

                Cell cellIdentity = row.createCell(7);
                cellIdentity.setCellValue(item.getIdentity());

                r++;
            }
            for (int i = 0; i < 8; i++) {
                sheet.autoSizeColumn(i);
            }

            FileOutputStream out = new FileOutputStream(new File("C:\\Users\\Hp_Workplace\\IdeaProjects\\homework14\\documents\\customer.xls"));
            workbook.write(out);
            out.close();
            workbook.close();
            System.out.println("A táblázat sikeresen elkészült!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
