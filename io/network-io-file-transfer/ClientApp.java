import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 9090);
        System.out.println("Connected to the server");

        File file = new File("/home/ranjith-suranga/Pictures/Wallpapers/AwHEaL.jpg");

        FileInputStream fis = new FileInputStream(file);
        BufferedInputStream bis = new BufferedInputStream(fis);

        byte[] buffer = new byte[1024];
        int read = -1;

        while ((read = bis.read(buffer)) != -1){
            socket.getOutputStream().write(buffer, 0, read);
        }

        bis.close();

        System.out.println("File Uploaded!");
    }
}
