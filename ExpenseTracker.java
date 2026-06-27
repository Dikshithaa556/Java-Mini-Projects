import java.io.*;
import java.util.Scanner;
import java.time.LocalDate;

public class ExpenseTracker {

    static final String FILE_NAME = "expenses.txt";

    // Add Expense
    static void addExpense(Scanner sc) {

        System.out.print("Enter category (Food/Travel/Shopping): ");
        String category = sc.next().trim();

        System.out.print("Enter amount: ");

        if (!sc.hasNextDouble()) {
            System.out.println("Invalid amount!");
            sc.next();
            return;
        }

        double amount = sc.nextDouble();

        String date = LocalDate.now().toString();

        try {

            FileWriter fw =
                    new FileWriter(FILE_NAME, true);

            fw.write(category + "," + amount + "," + date + "\n");

            fw.close();

            System.out.println("Expense added successfully!");

        } catch (Exception e) {

            System.out.println("Error writing file.");
        }
    }

    // View Expenses (Monthly + Summary)
    static void viewExpenses() {

        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("No expenses found!");
            return;
        }

        double total = 0;

        double foodTotal = 0;
        double travelTotal = 0;
        double shoppingTotal = 0;

        String currentMonth =
                LocalDate.now().toString().substring(0, 7);

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(file));

            String line;

            System.out.println("\n--- ALL EXPENSES (Current Month) ---");

            while ((line = br.readLine()) != null) {

                String[] data = line.split(",");

                if (data.length < 3) continue;

                String category = data[0];
                double amount = Double.parseDouble(data[1]);
                String date = data[2];

                // Monthly filter
                if (!date.startsWith(currentMonth)) {
                    continue;
                }

                System.out.println(category + " - " + amount + " - " + date);

                total += amount;

                switch (category.toLowerCase()) {

                    case "food":
                        foodTotal += amount;
                        break;

                    case "travel":
                        travelTotal += amount;
                        break;

                    case "shopping":
                        shoppingTotal += amount;
                        break;
                }
            }

            br.close();

            System.out.println("\n--- SUMMARY ---");
            System.out.println("Food Total: " + foodTotal);
            System.out.println("Travel Total: " + travelTotal);
            System.out.println("Shopping Total: " + shoppingTotal);
            System.out.println("----------------");
            System.out.println("Total Spending: " + total);

        } catch (Exception e) {

            System.out.println("Error reading file.");
        }
    }

    // Main Menu
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = 0;

        do {

            System.out.println("\n===== EXPENSE TRACKER =====");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Exit");

            System.out.print("Enter choice: ");

            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Invalid input! Enter number only.");
                sc.next();
                continue;
            }

            switch (choice) {

                case 1:
                    addExpense(sc);
                    break;

                case 2:
                    viewExpenses();
                    break;

                case 3:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice! (1-3 only)");
            }

        } while (choice != 3);

        sc.close();
    }
}
