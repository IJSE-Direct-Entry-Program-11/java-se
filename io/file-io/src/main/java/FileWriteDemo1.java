import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileWriteDemo1 {

    public static void main(String[] args) throws FileNotFoundException {
        File fileRef = new File("/home/ranjith-suranga/Desktop/dep11.txt");
        try {
            fileRef.createNewFile();
        } catch (IOException e) {
            System.out.println("Failed to create the file");
            e.printStackTrace();
            return;
        }

        FileOutputStream fos = new FileOutputStream(fileRef);
        try {
            String something = "Hello! This is my first experience with byte streams";
            byte[] bytes = something.getBytes();

            for (byte b : bytes) {
                fos.write(b);
            }

            fos.close();
        } catch (IOException e) {
            System.out.println("Failed to complete the writing process");
            e.printStackTrace();
        }
    }
}
