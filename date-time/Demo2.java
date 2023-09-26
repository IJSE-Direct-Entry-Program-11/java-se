import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Demo2 {
    public static void main(String[] args) {
        Date date = Date.valueOf("2023-09-26");
        Time time = Time.valueOf("17:27:00");
        Timestamp timestamp = Timestamp.valueOf("2023-09-26 17:28:00");

        LocalDate localDate = date.toLocalDate();
        LocalTime localTime = time.toLocalTime();
        LocalDateTime localDateTime = timestamp.toLocalDateTime();

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);
    }
}
