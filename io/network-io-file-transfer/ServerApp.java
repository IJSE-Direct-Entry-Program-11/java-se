import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);
        System.out.println("Server is up and running at 9090");

        while (true){
            System.out.println("Waiting for an incomming connection");
            Socket localSocket = serverSocket.accept();
            System.out.println("Incoming connection: " + localSocket);
            new Thread(()-> {

                try {

                    // (UUID) Universally Unique ID = GUID
                    File newFile = new File("uploads", UUID.randomUUID().toString());
                    newFile.createNewFile();
                    FileOutputStream fos = new FileOutputStream(newFile);
                    BufferedOutputStream bos = new BufferedOutputStream(fos);

                    InputStream is = localSocket.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    int read = -1;
                    byte[] buffer = new byte[1024];
                    while ((read = bis.read(buffer)) != -1){
                        bos.write(buffer, 0, read);
                    }

                    bos.close();

                }catch(IOException e){
                    e.printStackTrace();
                }

            }).start();
        }
    }
}