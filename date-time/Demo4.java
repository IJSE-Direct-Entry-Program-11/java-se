import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

public class Demo4 {
    public static void main(String[] args) {
        LocalDate dob = LocalDate.of(2005, 2, 10);
        LocalDate today = LocalDate.now();

        Period between = Period.between(dob, today);
        System.out.println("Years=" + between.getYears());
        System.out.println("Months=" + between.getMonths());
        System.out.println("Days=" + between.getDays());

        System.out.println("Months=" + ChronoUnit.MONTHS.between(dob, today));
        System.out.println("Years=" + ChronoUnit.YEARS.between(dob, today));
        System.out.println("Days=" + ChronoUnit.DAYS.between(dob, today));
        System.out.println("Weeks=" + ChronoUnit.WEEKS.between(dob, today));
    }
}
