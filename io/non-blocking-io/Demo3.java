import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {
    public static void main(String[] args) throws IOException {
        File file = new File("something-2.txt");
        file.createNewFile();
        try(var fos = new FileOutputStream(file);
            var bos = new BufferedOutputStream(fos)){
            String content = "I want to write this in something-2.txt file";
            bos.write(content.getBytes());
        }
    }
}
