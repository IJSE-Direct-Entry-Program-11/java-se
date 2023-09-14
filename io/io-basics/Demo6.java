import java.io.File;
import java.io.FileOutputStream;

public class Demo6 {
    public static void main(String[] args) {
        String message = "Input Output Basics (I/O) \n Welcome to DEP-10";

        try {
            File file = new File("README.txt");
            if (!file.exists()) file.createNewFile();

            byte[] bytes = message.getBytes();
            FileOutputStream fos = new FileOutputStream(file);
            try{
                fos.write(bytes);
                System.out.println("Saved..!");
            }finally{
                fos.close();
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
