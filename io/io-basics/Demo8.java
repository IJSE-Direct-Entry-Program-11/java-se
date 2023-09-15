import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class Demo8 {
    public static void main(String[] args) throws Exception {
        File file = new File("README.txt");

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);
        try {
            byte[] buffer = new byte[1024];
            int read = -1;

            while ((read = bis.read(buffer))!= -1){
                System.out.print(new String(buffer, 0, read));
            }
        } finally {
            bis.close();
        }
    }
}
