import java.util.Scanner;

public class Demo2 {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        while (true){
            System.out.print("Enter your contact number: ");
            String contact = SCANNER.nextLine().strip();
            // contact format: 077-1234567, +9477-1234567
            if (!(contact.length() == 11 || contact.length() == 13)){
                System.out.println("Invalid Contact");
                continue;
            }

            if (contact.length() == 11 && 
                (!contact.startsWith("0") ||
                !isDigits(contact.substring(1, 3)) ||
                contact.charAt(3) != '-' ||
                !isDigits(contact.substring(4)))){
                    System.out.println("Invalid Contact");
                    continue;
            }
            if (contact.length() == 13 && 
                (!contact.substring(0, 3).equals("+94") ||
                !isDigits(contact.substring(3, 5)) || 
                contact.charAt(5) != '-' ||
                !isDigits(contact.substring(6)))){
                    System.out.println("Invalid Contact");
                    continue;
            }
            System.out.println("Valid Contact");
        }
    }

    public static boolean isDigits(String input){
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }
}
