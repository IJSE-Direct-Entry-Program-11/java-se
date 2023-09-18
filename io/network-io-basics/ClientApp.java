import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClientApp {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) throws UnknownHostException, IOException {
        Socket socket = new Socket("127.0.0.1", 5050);
        System.out.println("Connected to the server");
        while (true){
            System.out.print("Enter a message to sent: ");
            String message = SCANNER.nextLine();

            socket.getOutputStream().write(message.getBytes());
        }
    }
}
