import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo6 {
    public static void main(String[] args) {
        String text1 = "My customer id: C-123";
        String text2 = "C-450";

        Pattern pattern = Pattern.compile("\\bC-\\d{3}\\b");
        Matcher matcher1 = pattern.matcher(text1);
        Matcher matcher2 = pattern.matcher(text2);

        System.out.println(matcher1.matches()); // false
        matcher1.reset();
        System.out.println(matcher1.find());    // true

        System.out.println(matcher2.matches());     // true
        matcher2.reset();
        System.out.println(matcher2.find());        // true

        System.out.println("------------");
        System.out.println(Pattern.matches("\\bC-\\d{3}\\b", text1));
        System.out.println(Pattern.matches("\\bC-\\d{3}\\b", text2));

        System.out.println("------------");
        System.out.println(text1.matches("\\bC-\\d{3}\\b"));
        System.out.println(text2.matches("\\bC-\\d{3}\\b"));
    }
}
