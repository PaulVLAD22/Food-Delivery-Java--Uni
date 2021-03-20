package service;

import model.Company;
import model.Coordinate;
import model.account.Driver;
import model.account.User;
import model.local.Local;
import model.local.Product;
import model.order.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

import static java.lang.Integer.*;

public class UserService extends BasicService {
    public void displayMenu(User user, Company company) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1.View Menu");
            System.out.println("2.Order");
            System.out.println("3.Log Out");
            System.out.println("4.Delete account");
            choice = scanner.nextInt();

            ArrayList<Product> all_products = new ArrayList<>();
            Set<Local> locals = company.getLocals();
            for (Local local : locals) {
                all_products.addAll((local.getMenu().getProducts()));
            }

            switch (choice) {
                case 1:
                    for (Local local : locals) {
                        System.out.println("Local " + local.getName());
                        for (Product product : local.getMenu().getProducts()) {
                            System.out.println(product);
                        }
                    }
                    break;
                case 2:
                    ArrayList<Local> locals_arr = new ArrayList<>(locals);
                    System.out.println("Choose the local:");
                    for (Local local : locals_arr) {
                        System.out.println(locals_arr.indexOf(local));
                        System.out.println(local);
                    }
                    choice = scanner.nextInt();
                    Local chosenLocal = locals_arr.get(choice);
                    ArrayList<Product> localProducts = chosenLocal.getMenu().getProducts();
                    for (Product product : localProducts) {
                        System.out.println(localProducts.indexOf(product));
                        System.out.println(product);
                    }
                    HashMap<Product, Integer> order_products = new HashMap<Product, Integer>();
                    System.out.println("Enter number of products you want to buy \n (1:2) means 2 pieces of product number 1");
                    String input = "0:1";
                    String[] entry = input.split(",");
                    for (String s : entry) {
                        System.out.println(s);
                        String[] info = s.split(":");
                        int productNumber = parseInt(info[0]);
                        int productQuantity = parseInt(info[1]);
                        order_products.put(localProducts.get(productNumber), productQuantity);
                    }

                    Driver closestDriver = closestDriver(chosenLocal, company);
                    Order order = new Order(user, closestDriver, chosenLocal, order_products);
                    company.getOrders().add(order);
                    closestDriver.setCurrentOrder(order);
                    double totalDistance = calculateDistance(chosenLocal.getCoordinates(),closestDriver.getCoordinate())+
                            calculateDistance(chosenLocal.getCoordinates(), user.getCoordinate());
                    //unitati de masura pe 10;
                    System.out.println("The order will arrive in :"+totalDistance/10);
                    System.out.println("The driver will travel with speed of a 10 units per minute");
                    break;
                case 3:
                    displayMainMenu(company);
                    break;
                case 4:
                    System.out.println("Are you sure? (1-yes/0-no)");
                    choice = scanner.nextInt();
                    if (choice == 1) {
                        company.getUsers().remove(user);
                    } else {
                        displayMenu(user, company);
                    }
                default:
                    System.out.println("Choose a valid option");
            }
        }

    }

    private double calculateDistance(Coordinate c1, Coordinate c2) {
        return Math.sqrt(Math.pow(c2.getX() - c1.getX(), 2) +
                Math.pow(c2.getY() - c1.getY(), 2));
    }

    private Driver closestDriver(Local chosenLocal, Company company) {
        ArrayList<Driver> drivers = company.getDrivers();
        double minimum_distance = calculateDistance(drivers.get(0).getCoordinate(), chosenLocal.getCoordinates());
        int driver_index = 0;
        for (Driver driver : drivers) {
            double currentDistance = calculateDistance(driver.getCoordinate(), chosenLocal.getCoordinates());
            if (currentDistance < minimum_distance) {
                minimum_distance = currentDistance;
                driver_index=drivers.indexOf(driver);
            }
        }
        return drivers.get(driver_index);
    }

}
