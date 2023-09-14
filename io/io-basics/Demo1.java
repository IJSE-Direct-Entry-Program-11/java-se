import java.io.File;
import java.io.FileInputStream;

public class Demo1 {
    public static void main(String[] args) {
        File file = new File("README.txt");
        if (!file.exists()) throw new RuntimeException("File does not exist");

        try {
            FileInputStream fis = new FileInputStream(file);
            try {
                long t1 = System.currentTimeMillis();
                 int read = -1;
                 while ((read = fis.read())!= -1){
                    System.out.print((char) read);
                 }
                 long t2 = System.currentTimeMillis();
                 System.out.println("\nDiff=" + (t2 - t1));
            }finally {
                fis.close();
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}