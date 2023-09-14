import java.io.File;
import java.io.FileInputStream;

public class Demo4 {
    public static void main(String[] args) {
        File file = new File("README.txt");
        if (!file.exists())
            throw new RuntimeException("File does not exist");

        try {
            /* Do not use with large files */

            FileInputStream fis = new FileInputStream(file);
            try {
                long t1 = System.currentTimeMillis();
                byte[] buffer = fis.readAllBytes();     // Since Java 9
                System.out.println(new String(buffer));
                long t2 = System.currentTimeMillis();
                System.out.println("\nDiff=" + (t2 - t1));
            } finally {
                fis.close();
            }
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
