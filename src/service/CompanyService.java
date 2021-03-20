package service;

import model.Company;
import model.Coordinate;
import model.account.Driver;
import model.account.Account;
import model.account.User;

import java.util.Scanner;

public class CompanyService {
    UserService userService = new UserService();
    DriverService driverService = new DriverService();
    LocalService localService = new LocalService();


    public void addAccount(Company company, Account account){
        company.getCostumers().add(account);
        if (account instanceof User){
            company.getUsers().add((User) account);
        }
        else{
            company.getDrivers().add((Driver) account);
        }
    }

//    public void removeAccount(Company company, Account account){
//        Account toDeleteAccount = null;
//        for (Account a : company.getCostumers()){
//            if (a.equals(account)){
//                System.out.println("Enter password:");
//                Scanner scanner = new Scanner(System.in);
//                String password = scanner.next();
//                if (password.equals(a.getPassword())){
//                    toDeleteAccount = a;
//                }
//                else{
//                    System.out.println("Wrong Password");
//                    System.out.println("Resetting...");
//                }
//            }
//        }
//        company.getCostumers().remove(toDeleteAccount);
//    }
//

}
