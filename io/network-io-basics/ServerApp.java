import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);
        System.out.println("Server is up and running at 5050");
        while (true){
            System.out.println("Waiting for an incoming connection");
            Socket localSocket = serverSocket.accept();
            System.out.println("Incoming Connection: " + localSocket);
            new Thread(()-> {
                
                try{
                    InputStream is = localSocket.getInputStream();
                    byte[] buffer = new byte[1024];
                    int read = -1;

                    while ((read = is.read(buffer)) != -1){
                        System.out.println(new String(buffer, 0, read));
                    }

                }catch(IOException e){
                    e.printStackTrace();
                }

            }).start();
        }
    }
}