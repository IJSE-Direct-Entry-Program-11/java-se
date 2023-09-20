import java.util.Scanner;

public class Demo1 {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        outer:
        while (true){
            System.out.print("Enter your nic: ");
            String nic = SCANNER.nextLine().strip();
            // nic format: 123456789v
            if (nic.length() != 10){
                System.out.println("Invalid NIC");
                continue;
            }

            for (int i = 0; i < 9; i++) {
                if (!Character.isDigit(nic.charAt(i))) {
                    System.out.println("Invalid NIC");
                    continue outer;
                }
            }

            if (!(nic.endsWith("v") || nic.endsWith("V"))){
                System.out.println("Invalid NIC");
                continue;
            }

            System.out.println("Valid NIC");
        }
    }


}