package service;

import config.Config;
import model.Local.Local;
import model.Local.Product;

import java.util.Scanner;
import java.util.Set;

public class LocalServices {
    public void addMenuProduct(Local local, Product product){
        local.getMenu().getProducts().add(product);
    }
    public void removeMenuProduct(Local local, Product product){
        local.getMenu().getProducts().remove(product);
    }
    public static void removeLocal(Set<Local> locals, String name){
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
