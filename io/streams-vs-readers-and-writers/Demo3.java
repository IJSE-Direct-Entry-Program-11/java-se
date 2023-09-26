import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

public class Demo3 {
    public static void main(String[] args) {
        // File file = new File("something.txt");
        // try(var fis = new FileInputStream(file);
        //     var bis = new BufferedInputStream(fis)){

        //     int read = -1;
        //     byte[] buffer = new byte[1024];
        //     while ((read = fis.read(buffer)) != -1){
        //         System.out.print(new String(buffer, 0, read));
        //     }
        //     System.out.println();

        // }catch(IOException e){
        //     e.printStackTrace();
        // }

        File file = new File("something.txt");
        try(var fis = new FileReader(file);
            var bis = new BufferedReader(fis)){

            String line = null;
            while ((line = bis.readLine())!= null){
                System.out.println(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }        
    }
}
