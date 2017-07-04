package com.util.wolfika222.utilities;

import com.util.wolfika222.pojo.Customer;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Created by Hp_Workplace on 2017. 06. 24..
 */
public class Registry implements IRegistry {

    IAdminSide adminSide = new AdminSide();
    IUserSide userSide = new UserSide();
    LogInUser logInUser = new LogInUser();
    Scanner scanner = new Scanner(System.in);

    public void run() {

        int choose = -1;
        try {

            while (choose != 0) {
                System.out.println("Mit szeretnél tenni?(1 = regisztráció, 2 = bejelentkezés, 0 = kilépés)");
                choose = scanner.nextInt();
                scanner.nextLine();
                switch (choose) {
                    case 1:
                        registration();
                        break;
                    case 2:
                        signIn();
                        break;
                    case 0:
                        System.out.println("Bye");
                        break;
                    default:
                        System.out.println("Ilyen lehetőség nincs!\n");
                        break;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Érvénytelen karakter!");
        }
    }

    public void registration() {

        System.out.println("Kérlek add meg az adataidat!\nUsername: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String passwd = scanner.nextLine();
        System.out.println("Teljes név: ");
        String fullName = scanner.nextLine();
        System.out.println("Email: ");
        String email = scanner.nextLine();
        System.out.println("Város: ");
        int city = scanner.nextInt();
        System.out.println("Cég: ");
        int company = scanner.nextInt();
        Customer customer = new Customer(username, passwd, fullName, email, city, company);
        userSide.insert(customer);
        signIn();
    }

    public void signIn() {
        int choose = -1;
        Customer loggedIn = logInUser.login();

        if (loggedIn.getIdentity() == 0) {

            try {
                while (choose != 0) {
                    adminSide.printAdminMenu();
                    System.out.println("Adj meg egy menüpontot: (0-6)");
                    choose = scanner.nextInt();
                    switch (choose) {
                        case 1:
                            for (Customer item : adminSide.getAllUser()) {
                                System.out.println(item.toString());
                            }
                            break;
                        case 2:
                            adminSide.upIdentity();
                            break;
                        case 3:
                            adminSide.addNewCity();
                            break;
                        case 4:
                            adminSide.addNewProject();
                            break;
                        case 5:
                            adminSide.deleteUser();
                            break;
                        case 6:
                            adminSide.writeToExcel();
                            break;
                        case 0:
                            System.out.println("Bye");
                            break;
                        default:
                            System.out.println("Ilyen lehetőség nincs!\n");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Érvénytelen karakter!");
            }
        } else {
            try {
                while (choose != 0) {
                    userSide.printUserMenu();
                    System.out.println("Adj meg egy menüpontot: (0-3)");
                    choose = scanner.nextInt();
                    switch (choose) {
                        case 1:
                            userSide.selectProject();
                            break;
                        case 2:
                            userSide.unselectProject();
                            break;
                        case 3:
                            userSide.queryProject();
                            break;
                        case 0:
                            System.out.println("Bye");
                            break;
                        default:
                            System.out.println("Ilyen lehetőség nincs!\n");
                            break;
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("Érvénytelen karakter!");
            }
        }
    }
}
