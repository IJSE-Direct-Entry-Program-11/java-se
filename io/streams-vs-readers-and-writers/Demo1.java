import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Demo1 {
    public static void main(String[] args) {
        // File file = new File("something.txt");
        // try(var fis = new FileInputStream(file)){

        //     int read = -1;
        //     while ((read = fis.read()) != -1){
        //         System.out.print((char) read);
        //     }
        //     System.out.println();

        // }catch(IOException e){
        //     e.printStackTrace();
        // }

        File file = new File("something.txt");
        try(var fis = new FileReader(file)){

            int read = -1;
            while ((read = fis.read()) != -1){
                System.out.print((char) read);
            }
            System.out.println();

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}