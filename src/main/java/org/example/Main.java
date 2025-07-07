package org.example;
import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static Scanner s = new Scanner(System.in);
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Map<String, Integer> groceryInventory = new HashMap<>();
        int productQuantity;
        String productName;

        boolean exit = false;
        while (!exit) {
            System.out.println("--- Grocery Inventory Menu ---");
            System.out.println("1. View Inventory ");
            System.out.println("2. Add Product");
            System.out.println("3. Check Product");
            System.out.println("4. Update Stock");
            System.out.println("5. Remove Stock");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = s.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("\nCurrent Inventory");
                    viewInventory(groceryInventory);
                    break;
                case 2:
                    System.out.print("Enter product name:");
                    productName = s.next();
                    System.out.print("Enter quantity: ");
                    productQuantity = s.nextInt();

                    addProduct(groceryInventory,productName, productQuantity);
                    System.out.println("Product Added!");
                    System.out.println();
                    break;

                case 3:
                    System.out.print("Enter product name to check: ");
                    productName = s.next();
                    checkProduct(groceryInventory,productName);
                    break;

                case 4:
                    System.out.print("Enter product name to update:");
                    productName = s.next();
                    System.out.print("Enter new stock quantity: ");
                    productQuantity = s.nextInt();
                    updateStock(groceryInventory,productName, productQuantity);

                    System.out.println("Stock updated!");
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Enter product name to remove: ");
                    productName = s.next();
                    removeProduct(groceryInventory,productName);
                    System.out.println("Product removed.");
                    System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting system...");
                    exit = true;
                    break;
                default:
                    System.out.print("Enter a valid option (1-6)");
            }

        }
    }

    private static void updateStock(Map<String, Integer> groceryInventory, String productName, int productQuantity) {
        if (groceryInventory.containsKey(productName)){
            groceryInventory.put(productName,productQuantity);
        }else{
            System.out.println(productName + " is not in stock.");
        }
    }

    private static void checkProduct(Map<String, Integer> groceryInventory, String productName) {
        if (groceryInventory.containsKey(productName)){
            System.out.println(productName + " is in stock: " + groceryInventory.get(productName));
            System.out.println();
        }else{
            System.out.println(productName + " is not in stock.");
            System.out.println();
        }

    }

    private static void removeProduct(Map<String, Integer> groceryInventory, String productName) {
        if (groceryInventory.containsKey(productName)){
            groceryInventory.remove(productName);
        }else{
            System.out.println(productName + " is not in stock.");
        }
    }


    private static void viewInventory(Map<String, Integer> groceryInventory) {
        if (groceryInventory.isEmpty()) {
            System.out.println("Nothing in inventory yet.");
        }else{
            groceryInventory.forEach((productName,productQuantity) -> {
                System.out.println(productName + " - " + productQuantity + " pcs");
            });
        }
        System.out.println();
    }

    private static void addProduct(Map<String, Integer> groceryInventory, String productName, int productQuantity) {
        while(productQuantity <= 0){
            System.out.println("Quantity must be positive");
            System.out.print("Quantity: ");
            productQuantity = s.nextInt();
        }

        groceryInventory.put(productName, productQuantity);
    }
}
