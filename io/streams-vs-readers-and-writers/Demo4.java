import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class Demo4 {
    public static void main(String[] args) {
        // File file = new File("something-2.txt");
        // try {
        //     file.createNewFile();
        //     try(var fos = new FileOutputStream(file);
        //         var bos = new BufferedOutputStream(fos)){

        //         String content = "This is the content that I want to write in the file";
        //         bos.write(content.getBytes());
        //         bos.flush();
        //     }
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        File file = new File("something-2.txt");
        try {
            file.createNewFile();
            try(var fos = new FileWriter(file);
                var bos = new BufferedWriter(fos)){

                String content = "This is the content that I want to write in the file";
                bos.write(content);
                bos.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
