import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Demo2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        File file = new File("customer.dep");
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
            Customer customer1 = (Customer) ois.readObject();
            System.out.println(customer1.id);
            System.out.println(customer1.name);
            System.out.println(customer1.address);
            //System.out.println(customer1.contact);

            Customer customer2 = (Customer) ois.readObject();
            System.out.println(customer2.id);
            System.out.println(customer2.name);
            System.out.println(customer2.address);
            //System.out.println(customer2.contact);
        }finally {
            ois.close();
        }
    }
}
