import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Demo7 {
    public static void main(String[] args) {
        String endpoint1 = "https://photoslibrary.googleapis.com:443/v1/albums/10";
        String endpoint2 = "http://people.googleapis.com:80/v2/me/connections";

        String pattern = "(?<protocol>http[s]?)://(?<host>(?<service>[a-z]+)[.]googleapis.com):(?<port>\\d{1,5})/v(?<version>\\d+)/(?<path>.+)";
        System.out.println(endpoint1.matches(pattern));
        System.out.println(endpoint2.matches(pattern));

        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher1 = compiledPattern.matcher(endpoint1);

        matcher1.find();
        System.out.println("protocol = " + matcher1.group(1));
        System.out.println("host = " + matcher1.group(2));
        System.out.println("service = " + matcher1.group(3));
        System.out.println("port = " + matcher1.group(4));
        System.out.println("api version = " + matcher1.group(5));
        System.out.println("path = " + matcher1.group(6));

        System.out.println("host = " + matcher1.group("host"));
        System.out.println("port = " + matcher1.group("port"));
        System.out.println("service = " + matcher1.group("service"));
        System.out.println("path = " + matcher1.group("path"));        

        System.out.println("---------------------------------");
        Matcher matcher2 = compiledPattern.matcher(endpoint2);
        matcher2.find();

        System.out.println("protocol = " + matcher2.group("protocol"));
        System.out.println("host = " + matcher2.group("host"));
        System.out.println("port = " + matcher2.group("port"));
        System.out.println("api version = " + matcher2.group("version"));
        System.out.println("path = " + matcher2.group("path"));
    }
}
