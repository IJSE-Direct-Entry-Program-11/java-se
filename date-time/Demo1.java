import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;

public class Demo1 {
    public static void main(String[] args) {
        // Local Date, Local Time

        // java.util.Date

        // java.sql.Date, java.sql.Time, java.sql.TimeStamp
        // Calendar 

        // Since Java 8
        // LocalDate, LocalTime, LocalDateTime

        Date utilDate = new Date();
        System.out.println(utilDate);

        Date utilDate2 = Calendar.getInstance().getTime();
        System.out.println(utilDate2);

        LocalDate localDate = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime localTime = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalTime();
        LocalDateTime localDateTime = utilDate.toInstant()
                        .atZone(ZoneId.systemDefault()).toLocalDateTime();

        System.out.println(localDate);                        
        System.out.println(localTime);                        
        System.out.println(localDateTime);                        
    }
}