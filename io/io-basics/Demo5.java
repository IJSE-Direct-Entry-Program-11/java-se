import java.io.File;
import java.io.FileOutputStream;

public class Demo5 {
    public static void main(String[] args) {
        String message = "Input Output Basics";

        try {
            File file = new File("README.txt");
            if (!file.exists()) file.createNewFile();

            byte[] bytes = message.getBytes();
            FileOutputStream fos = new FileOutputStream(file);
            try{
                for (byte b : bytes) {
                    fos.write(b);
                }
                System.out.println("Saved..!");
            }finally{
                fos.close();
            }
        }catch(Throwable t){
            t.printStackTrace();
        }
    }
}
