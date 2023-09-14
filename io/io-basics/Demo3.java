import java.io.File;
import java.io.FileInputStream;

public class Demo3 {
    public static void main(String[] args) {
        File file = new File("README.txt");
        if (!file.exists())
            throw new RuntimeException("File does not exist");

        try {
            /* Do not use with large files */

            FileInputStream fis = new FileInputStream(file);
            try {
                long t1 = System.currentTimeMillis();
                //byte[] buffer = new byte[(int) file.length()];
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
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
