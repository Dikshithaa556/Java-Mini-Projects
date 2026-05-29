import java.util.Scanner;

public class BusReservation {

    static int totalSeats = 10;
    static boolean[] seats = new boolean[totalSeats];

    // Show all seats
    static void showSeats() {
        System.out.println("\n--- Seat Status ---");

        for (int i = 0; i < totalSeats; i++) {
            if (seats[i]) {
                System.out.println("Seat " + (i + 1) + " : Booked");
            } else {
                System.out.println("Seat " + (i + 1) + " : Available");
            }
        }
    }

    // Book seat
    static void bookSeat(Scanner sc) {
        System.out.print("Enter seat number to book (1-10): ");
        int seatNumber = sc.nextInt();

        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (seats[seatNumber - 1]) {
            System.out.println("Seat already booked!");
        } else {
            seats[seatNumber - 1] = true;
            System.out.println("Seat booked successfully!");
        }
    }

    // Cancel booking
    static void cancelSeat(Scanner sc) {
        System.out.print("Enter seat number to cancel: ");
        int seatNumber = sc.nextInt();

        if (seatNumber < 1 || seatNumber > totalSeats) {
            System.out.println("Invalid seat number!");
            return;
        }

        if (!seats[seatNumber - 1]) {
            System.out.println("Seat is already empty!");
        } else {
            seats[seatNumber - 1] = false;
            System.out.println("Booking cancelled successfully!");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== BUS RESERVATION SYSTEM =====");
            System.out.println("1. View Seats");
            System.out.println("2. Book Seat");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    showSeats();
                    break;

                case 2:
                    bookSeat(sc);
                    break;

                case 3:
                    cancelSeat(sc);
                    break;

                case 4:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 4);

        sc.close();
    }
}