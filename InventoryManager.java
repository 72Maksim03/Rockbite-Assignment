import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    public static void saveInventory(List<Item> items, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(items);
            System.out.println("Inventory saved to file.");
        }
    }

    public static ArrayList<Item> loadInventory(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (ArrayList<Item>) ois.readObject();
        }
    }
}
