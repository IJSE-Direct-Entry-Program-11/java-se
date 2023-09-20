import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo4 {
    public static void main(String[] args) {
        // Pattern, Matcher
        // Pattern patter = Pattern.compile(regexp)
        // Matcher matcher = pattern.matcher(text)

        String text = "My nic is : 123456789v";
        Pattern pattern = Pattern.compile("\\d{9}[Vv]");
        Matcher matcher = pattern.matcher(text);

        System.out.println(matcher.find());
        System.out.println(matcher.start());
        System.out.println(matcher.end());
        System.out.println(text.substring(matcher.start(), matcher.end()));
    }
}
