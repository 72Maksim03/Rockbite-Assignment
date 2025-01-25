import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Inventory inventory;
        Scanner sc = new Scanner(System.in);

        System.out.println("Do you want to load the inventory or create new one? [l-load]/[n-new]");
        char invChoice = sc.nextLine().charAt(0);
        if (invChoice == 'l'){
            try{
                inventory = new Inventory(InventoryManager.loadInventory("Inventory.dat"));
            } catch (Exception e){
                System.out.println("Error with loading inventory. Creating new one");
                inventory = new Inventory();
            }
        } else {
            inventory = new Inventory();
        }
        System.out.println("Inventory created");

        while(true){
            System.out.println("Inventory: ");
            System.out.println(inventory);

            System.out.println("What do you want to do? (Choose the index)");
            System.out.println("1. Add an item");
            System.out.println("2. Upgrade item by merging 3 items");
            System.out.println("3. Add item with random rarity");
            System.out.println("4. Drop an item");
            System.out.println("5. Save the inventory");
            System.out.println("6. Save the inventory and exit");
            System.out.println("7. Exit");

            int choice = 0;
            try{
                choice = sc.nextInt();
                sc.nextLine();
            } catch (InputMismatchException e){
                System.out.println("Please enter the number of choice you want to do");
                sc.nextLine();
                continue;
            }

            switch (choice){
                case 1:
                    addItem(inventory);
                    break;
                case 2:
                    upgradeItem(inventory);
                    break;
                case 3:
                    addItemRand(inventory);
                    break;
                case 4:
                    dropItem(inventory);
                    break;
                case 5:
                    saveInventory(inventory, "inventory.dat");
                    break;
                case 6:
                    saveInventory(inventory, "inventory.dat");
                    System.exit(0);
                case 7:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    public static void addItem(Inventory inventory){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter item name:");
        String itemName = sc.nextLine();
        System.out.println("Enter item rarity (COMMON, GREAT, RARE, EPIC, LEGENDARY):");
        String rarity = sc.nextLine();
        inventory.add(itemName, rarity);
    }

    public static void addItemRand(Inventory inventory){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter item name:");
        String itemName = sc.nextLine();
        inventory.addRandom(itemName);
    }

    public static void upgradeItem(Inventory inventory){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter indexes of 3 elements. (index2 and index3 will be merged to upgrade index1)");
        int index1 = 0, index2 = 0, index3 = 0;
        try{
            index1 = sc.nextInt() - 1;
            index2 = sc.nextInt() - 1;
            index3 = sc.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter three integer values.");
            sc.nextLine();
            return;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return;
        }

        try{
            inventory.upgrade(index1, index2, index3);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void dropItem(Inventory inventory){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter index of item to drop:");
        int index = 0;
        try{
            index = sc.nextInt() - 1;
        } catch (InputMismatchException e) {
            System.out.println("Error: Please enter an integer value.");
            sc.nextLine();
            return;
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            return;
        }

        try{
            inventory.drop(index);
        } catch (IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }

    public static void saveInventory(Inventory inventory, String filename){
        try{
            InventoryManager.saveInventory(inventory.getItems(), filename);
        } catch (IOException e){
            System.out.println("Problems with saving inventory");
            System.exit(0);
        }
    }


}
