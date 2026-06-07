import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import java.io.Console;

public class LoginSystem {

    static final String FILE_NAME = "users.txt";

    // Encrypt Password
    static String encryptPassword(String password) {
        return Base64.getEncoder().encodeToString(password.getBytes());
    }

    // Check if username exists
    static boolean userExists(String username) {

        try {

            File file = new File(FILE_NAME);

            if (!file.exists()) {
                return false;
            }

            BufferedReader br =
                    new BufferedReader(
                    new FileReader(FILE_NAME));

            String line;

            while ((line = br.readLine()) != null) {

                String[] userData = line.split(",");

                if (userData.length == 2 &&
                    userData[0].equals(username)) {

                    br.close();
                    return true;
                }
            }

            br.close();

        } catch (IOException e) {

            System.out.println(
                    "Error checking username.");
        }

        return false;
    }

    // Register User
    static void registerUser(Scanner sc) {

        System.out.print("Enter username: ");
        String username = sc.next().trim();

        if (username.isEmpty()) {

            System.out.println(
                    "Username cannot be empty!");
            return;
        }

        if (userExists(username)) {

            System.out.println(
                    "Username already exists!");
            return;
        }

        String password;

        while (true) {

            Console console = System.console();

            if (console != null) {

                char[] passArray =
                        console.readPassword(
                        "Enter password: ");

                password = new String(passArray);

            } else {

                System.out.print(
                        "Enter password: ");

                password = sc.next();
            }

            password = password.trim();

            if (password.isEmpty()) {

                System.out.println(
                        "Password cannot be empty!");
            }

            else if (password.length() < 4) {

                System.out.println(
                        "Password must contain at least 4 characters!");
            }

            else {
                break;
            }
        }

        String encryptedPassword =
                encryptPassword(password);

        try {

            FileWriter fw =
                    new FileWriter(FILE_NAME, true);

            fw.write(username + "," +
                     encryptedPassword + "\n");

            fw.close();

            System.out.println(
                    "Registration Successful!");

        } catch (IOException e) {

            System.out.println(
                    "Error writing file.");
        }
    }

    // Login User
    static void loginUser(Scanner sc) {

        System.out.print("Enter username: ");
        String username = sc.next().trim();

        String password;

        Console console = System.console();

        if (console != null) {

            char[] passArray =
                    console.readPassword(
                    "Enter password: ");

            password = new String(passArray);

        } else {

            System.out.print(
                    "Enter password: ");

            password = sc.next();
        }

        String encryptedPassword =
                encryptPassword(password);

        boolean found = false;

        try {

            BufferedReader br =
                    new BufferedReader(
                    new FileReader(FILE_NAME));

            String line;

            while ((line = br.readLine()) != null) {

                String[] userData =
                        line.split(",");

                if (userData.length == 2 &&
                    username.equals(userData[0]) &&
                    encryptedPassword.equals(userData[1])) {

                    found = true;
                    break;
                }
            }

            br.close();

            if (found) {

                System.out.println(
                        "Login Successful!");
            }

            else {

                System.out.println(
                        "Invalid Username or Password!");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error reading file.");
        }
    }

    // Reset Password
    static void forgotPassword(Scanner sc) {

        System.out.print(
                "Enter username: ");

        String username =
                sc.next().trim();

        File inputFile =
                new File(FILE_NAME);

        File tempFile =
                new File("temp.txt");

        boolean found = false;

        try {

            BufferedReader br =
                    new BufferedReader(
                    new FileReader(inputFile));

            BufferedWriter bw =
                    new BufferedWriter(
                    new FileWriter(tempFile));

            String line;

            while ((line = br.readLine()) != null) {

                String[] userData =
                        line.split(",");

                if (userData.length != 2) {

                    bw.write(line);
                    bw.newLine();
                    continue;
                }

                String storedUsername =
                        userData[0];

                if (storedUsername.equals(username)) {

                    found = true;

                    String newPassword;

                    while (true) {

                        Console console =
                                System.console();

                        if (console != null) {

                            char[] passArray =
                                    console.readPassword(
                                    "Enter new password: ");

                            newPassword =
                                    new String(passArray);

                        } else {

                            System.out.print(
                                    "Enter new password: ");

                            newPassword =
                                    sc.next();
                        }

                        newPassword =
                                newPassword.trim();

                        if (newPassword.isEmpty()) {

                            System.out.println(
                                    "Password cannot be empty!");
                        }

                        else if (newPassword.length() < 4) {

                            System.out.println(
                                    "Password must contain at least 4 characters!");
                        }

                        else {
                            break;
                        }
                    }

                    String encryptedPassword =
                            encryptPassword(
                            newPassword);

                    bw.write(storedUsername +
                             "," +
                             encryptedPassword);

                    bw.newLine();

                    System.out.println(
                            "Password updated successfully!");
                }

                else {

                    bw.write(line);
                    bw.newLine();
                }
            }

            br.close();
            bw.close();

            if (found) {

                inputFile.delete();
                tempFile.renameTo(inputFile);
            }

            else {

                tempFile.delete();

                System.out.println(
                        "Username not found!");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error updating password.");
        }
    }

    // Main Method
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {

            System.out.println(
                    "\n===== LOGIN SYSTEM =====");

            System.out.println(
                    "1. Register");

            System.out.println(
                    "2. Login");

            System.out.println(
                    "3. Forgot Password");

            System.out.println(
                    "4. Exit");

            System.out.print(
                    "Enter choice: ");

            if (sc.hasNextInt()) {

                choice = sc.nextInt();

            } else {

                System.out.println(
                        "Invalid input! Enter numbers only.");

                sc.next();
                continue;
            }

            switch (choice) {

                case 1:
                    registerUser(sc);
                    break;

                case 2:
                    loginUser(sc);
                    break;

                case 3:
                    forgotPassword(sc);
                    break;

                case 4:
                    System.out.println(
                            "Exiting...");
                    break;

                default:
                    System.out.println(
                            "Invalid choice! Enter 1-4.");
            }

        } while (choice != 4);

        sc.close();
    }
}