import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Demo1 {
    public static void main(String[] args) throws IOException {
        Customer customer1 = new Customer(1, "Kasun", "Galle");
        Customer customer2 = new Customer(2, "Nuwan", "Matara");

        File file = new File("customer.dep");
        file.createNewFile();
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        try {
            oos.writeObject(customer1);
            oos.writeObject(customer2);
        } finally {
            oos.close();
        }
    }
}
