import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class Demo5 {
    public static void main(String[] args) {
        LocalTime noon = LocalTime.of(12, 0);
        LocalTime now = LocalTime.now();

        Duration between = Duration.between(noon, now);
        System.out.println("Seconds=" + between.getSeconds());
        System.out.println("Nano Seconds=" + between.getNano());

        System.out.println("Hours=" + ChronoUnit.HOURS.between(noon, now));
        System.out.println("Minutes=" + ChronoUnit.MINUTES.between(noon, now));
    }
}
