import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExistsDemo {

    public static void main(String[] args) {
        File fileRef = new File("/home/ranjith-suranga/Desktop/abc.txt");
        System.out.println(fileRef.exists());

        Path pathRef = Paths.get("/home/ranjith-suranga/Desktop/abc.txt");
        System.out.println(Files.exists(pathRef));
    }
}
