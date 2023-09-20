import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo5 {
    public static void main(String[] args) {
        String text = "My contact: 077-1234567 \n" +
                      "Her contact: 033-1234567 \n" + 
                      "His contact: 023-1234567. That's all \n";
        Pattern pattern = Pattern.compile("\\b\\d{3}-\\d{7}\\b");
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()){
            //System.out.println(matcher.start());
            //System.out.println(matcher.end());
            System.out.println(text.substring(matcher.start(), matcher.end()));
        }      
    }
}
