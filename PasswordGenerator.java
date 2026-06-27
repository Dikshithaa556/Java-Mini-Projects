import java.util.*;

public class PasswordGenerator {

    static String generatePassword(int length) {

        String chars =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "abcdefghijklmnopqrstuvwxyz" +
                "0123456789" +
                "!@#$%^&*()_+";

        Random random = new Random();

        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {

            int index = random.nextInt(chars.length());

            password.append(chars.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter password length: ");
        int length = sc.nextInt();

        if (length < 4) {
            System.out.println("Password too short! Minimum 4 required.");
            return;
        }

        String password = generatePassword(length);

        System.out.println("Generated Password: " + password);
    }
}
