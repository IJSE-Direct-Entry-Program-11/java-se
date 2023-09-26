import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File("something.txt");
        
        try(var fis = new FileInputStream(file);
            var bis = new BufferedInputStream(fis)){

            byte[] buffer = new byte[1024];
            int read = -1;
            while ((read = bis.read(buffer)) != -1){
                System.out.println(new String(buffer, 0, read));
            }
        }
    }
}