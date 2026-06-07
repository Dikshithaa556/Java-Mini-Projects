import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.awt.Toolkit;

public class AlarmClock {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== DIGITAL ALARM CLOCK =====");

        System.out.print("Set alarm time (HH:mm:ss): ");
        String alarmInput = sc.nextLine();

        LocalTime alarmTime;

        try {

            alarmTime = LocalTime.parse(alarmInput);

        } catch (Exception e) {

            System.out.println("Invalid time format!");
            sc.close();
            return;
        }

        System.out.println("Alarm set for: " + alarmTime);

        while (true) {

            LocalTime currentTime = LocalTime.now();

            System.out.print(
                    "\rCurrent Time: " +
                    currentTime.format(
                    DateTimeFormatter.ofPattern("HH:mm:ss")));

            if (currentTime.getHour() == alarmTime.getHour()
                    && currentTime.getMinute() == alarmTime.getMinute()
                    && currentTime.getSecond() == alarmTime.getSecond()) {

                System.out.println("\n\n⏰ ALARM RINGING!");

                // Beep 10 times
                for (int i = 0; i < 10; i++) {

                    Toolkit.getDefaultToolkit().beep();

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                break;
            }

            try {

                Thread.sleep(1000);

            } catch (InterruptedException e) {

                e.printStackTrace();
            }
        }

        sc.close();
    }
}
