import java.io.FileInputStream;
import java.io.ObjectInputStream;
import adt.*;
import entity.*; // Change to Student or Booking if needed

public class ReadDatFileTest {
    public static void main(String[] args) {
        try {
            // 1. Open the file
            FileInputStream fileIn = new FileInputStream("src/Data/bookings.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            
            // 2. Read the binary data back into your custom List
            ListInterface<Booking> recoveredList = (ListInterface<Booking>) objectIn.readObject();
            
            objectIn.close();
            fileIn.close();
            
            // 3. Print what was inside!
            System.out.println("--- CONTENTS OF .DAT FILE ---");
            for (int i = 0; i < recoveredList.size(); i++) {
                System.out.println(recoveredList.get(i).toString());
            }
            
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}