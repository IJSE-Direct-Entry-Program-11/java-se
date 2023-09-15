import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class Demo9 {
    public static void main(String[] args) throws Exception {
        String message = "Basics of IO \nI want to use buffered streams to write this content";
        File file = new File("README.md");

        FileOutputStream fos = new FileOutputStream(file);
        BufferedOutputStream bos = new BufferedOutputStream(fos);

        try {
            
            bos.write(message.getBytes());
            // bos.flush();

        } finally {
            bos.close(); // When we close the buffer it flushes the buffer implicitly
        }
    }
}
