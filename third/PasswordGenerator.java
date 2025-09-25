package third;

import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String DIGITS = "0123456789";
    private static final String SPECIALS = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    private static final String ALL_CHARS = LOWERCASE + UPPERCASE + DIGITS + SPECIALS;

    private static final Random random = new Random();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SECURE PASSWORD GENERATOR ===");
        System.out.println("Password will contain lowercase letters, uppercase letters, digits, and special characters.");
        System.out.println("Recommended: use 12 characters for maximum security.");

        boolean generateAgain = true;

        while (generateAgain) {
            int length = 0;
            while (true) {
                System.out.print("\nEnter password length (8-12 characters): ");
                try {
                    length = Integer.parseInt(scanner.nextLine());
                    if (length >= 8 && length <= 12) {
                        break;
                    } else {
                        System.out.println("Error: Length must be between 8 and 12 characters!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Please enter a valid number!");
                }
            }

            String password = generatePassword(length);

            System.out.println("\n=== YOUR PASSWORD ===");
            System.out.println(password);

            System.out.print("\nDo you want to generate another password? (Y/N): ");
            String answer = scanner.nextLine().trim().toUpperCase();
            generateAgain = answer.equals("Y");
        }

        scanner.close();
    }

    private static String generatePassword(int length) {
        char[] password = new char[length];

        password[0] = getRandomChar(LOWERCASE);
        password[1] = getRandomChar(UPPERCASE);
        password[2] = getRandomChar(DIGITS);
        password[3] = getRandomChar(SPECIALS);

        for (int i = 4; i < length; i++) {
            password[i] = getRandomChar(ALL_CHARS);
        }

        return shufflePassword(password);
    }

    private static char getRandomChar(String charSet) {
        int index = random.nextInt(charSet.length());
        return charSet.charAt(index);
    }

    private static String shufflePassword(char[] password) {
        for (int i = password.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = password[i];
            password[i] = password[j];
            password[j] = temp;
        }
        return new String(password);
    }
}
