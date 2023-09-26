import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Demo3 {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        LocalDateTime now2 = LocalDateTime.now();

        LocalDate date = LocalDate.of(2023, 5, 28);
        LocalTime time = LocalTime.of(15, 5);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        // plusXXX(), minusXXX()
        LocalDate newDate = today.plusDays(5);
        System.out.println(newDate);
        LocalDate date2 = newDate.minusYears(2);
        System.out.println(date2);
    }
}
