import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo8 {
    public static void main(String[] args) {
        String text = "NIC \n" 
                      +  "871234567V \n" 
                      +  "881234567V \n" 
                      +  "921234667v \n" 
                      +  "981454567V \n" 
                      +  "801234887v \n" 
                      +  "821234567V List" ;

        Pattern pattern = Pattern.compile("\\b\\d{9}[Vv]\\b");
        Matcher matcher = pattern.matcher(text);

        matcher.find();
        System.out.println(text.substring(matcher.start(), matcher.end()));

        matcher.find();
        System.out.println(text.substring(matcher.start(), matcher.end()));

        matcher.find();
        System.out.println(text.substring(matcher.start(), matcher.end()));
        
        matcher.reset();

        matcher.find();
        System.out.println(text.substring(matcher.start(), matcher.end()));        
    }
}
