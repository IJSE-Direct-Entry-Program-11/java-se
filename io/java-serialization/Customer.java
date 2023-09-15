import java.io.Serializable;

public class Customer implements Serializable{
    int id;
    String name;
    transient String address;

    //private static final long serialVersionUID = 4719891896785215928L;

    public Customer(int id, String name, String address) {
        System.out.println("Constructor()");
        this.id = id;
        this.name = name;
        this.address = address;
    }

}