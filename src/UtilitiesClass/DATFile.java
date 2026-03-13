
package UtilitiesClass;

import adt.ListInterface;
import adt.SingleLinkedList;
import java.io.*;


public class DATFile {
    public static <T> void saveToFile(String filename, ListInterface<T> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.out.println("Error saving " + filename + ": " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> ListInterface<T> loadFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new SingleLinkedList<>();  // first run — no file yet
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            return (ListInterface<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading " + filename + ": " + e.getMessage());
            return new SingleLinkedList<>();
        }
    }
}