
package UtilitiesClass;

import java.util.Scanner;

public class Input {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("ERROR: Invalid input. Please enter a number.");
            }
        }
    }

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }
    
    public static void pause() {
        System.out.print("\nPress [ENTER] to continue...");
        scanner.nextLine();
    }
}