import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerAppInitializer {

    private static List<Socket> clientList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5050);
        while (true){
            System.out.println("Waiting for an incoming connection");
            Socket localSocket = serverSocket.accept();
            new Thread(()->{
                clientList.add(localSocket);
                try {
                    InputStream is = localSocket.getInputStream();
                    BufferedInputStream bis = new BufferedInputStream(is);
                    while (true) {
                        byte[] buffer = new byte[1024];
                        int read = bis.read(buffer);
                        if (read == -1) throw new EOFException();
                    }
                }catch (EOFException e){
                    clientList.remove(localSocket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }
    }
}
