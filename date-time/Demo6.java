import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class Demo6 {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter pattern = DateTimeFormatter
                        .ofPattern("yyyy LLLL dd (EE) hh:mm:ss");
        String formattedText = pattern.format(dateTime);
        System.out.println(formattedText);

        // String -> LocalDate, LocalTime, LocalDateTime
        TemporalAccessor parse = pattern.parse(formattedText);
        LocalDate date = LocalDate.parse(formattedText, pattern);
        LocalTime time = LocalTime.parse("15:12:25", 
            DateTimeFormatter.ofPattern("HH:mm:ss"));
        // LocalDateTime dateTime2 = LocalDateTime.parse(formattedText, pattern);
        System.out.println(parse);
        System.out.println(date);
        System.out.println(time);
        // System.out.println(dateTime2);
    }
}
