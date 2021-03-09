package services;

import config.Config;
import models.Local.Local;
import models.Local.Product;
import models.User.Driver;

import java.util.ArrayList;
import java.util.Scanner;

public class LocalServices {
    public static void addMenuProduct(Local local, Product product){
        local.getMenu().getProducts().add(product);
    }
    public static void removeMenuProduct(Local local, Product product){
        local.getMenu().getProducts().remove(product);
    }
    public static void deleteAccount(ArrayList<Local> locals,String name){
        Local toDeleteLocal=null;
        for (Local local : locals){
            if (local.getName().equals(name)){
                System.out.println("Enter Admin Password:");
                Scanner scanner = new Scanner(System.in);
                String password = scanner.next();
                if (password.equals(Config.adminPassword)){
                    toDeleteLocal=local;
                }
                else{
                    System.out.println("Wrong Password");
                    System.out.println("Resetting...");
                }
            }
        }
        locals.remove(toDeleteLocal);
    }
}
