import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

public class Demo2 {
    public static void main(String[] args) {

        // byte[] buffer = {65, 66, 67, 68, 69, 70, 71, 72, 0, 0, 0, 0, 0};
        // for (byte b : buffer) {
        // System.out.print((char) b);
        // }
        // System.out.println(new String(buffer));
        // System.out.println(new String(buffer, 1, 3));

        File file = new File("README.txt");
        if (!file.exists())
            throw new RuntimeException("File does not exist");

        try {
            
            FileInputStream fis = new FileInputStream(file);
            try {
                long t1 = System.currentTimeMillis();
                int read = -1;
                byte[] buffer = new byte[1024]; // 1024 bytes = 1 Kb
                while ((read = fis.read(buffer)) != -1) {
                    System.out.print(new String(buffer, 0, read));
                }
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