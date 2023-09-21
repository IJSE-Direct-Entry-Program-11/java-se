import java.io.*;

public class Demo {

    public static void main(String[] args) throws Exception {
        Employee employee = new Employee(1,
                new Name("Kasun", "Sampath"),
                "077-1234567");
        File file = new File("employee.db");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        try {
            oos.writeObject(employee);
        }finally {
            oos.close();
        }

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try{
            employee = (Employee) ois.readObject();
            System.out.println(employee.id);
            System.out.println(employee.name.firstName);
            System.out.println(employee.name.lastName);
            System.out.println(employee.contact);
        }finally {
            ois.close();
        }
    }
}

class Employee implements Serializable {
    int id;
    transient Name name = new Name("Prasad", "Waduge");
    String contact;

    public Employee() {
        System.out.println("Constructor()");
    }

    public Employee(int id, Name name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

//    @Override
//    public void writeExternal(ObjectOutput out) throws IOException {
//        // Serialization
//        out.writeObject(id);
//        out.writeObject(name.firstName);
//        out.writeObject(name.lastName);
//        out.writeObject(contact);
//    }
//
//    @Override
//    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
//        // Deserialization
//        id = (int) in.readObject();
//        name = new Name((String) in.readObject(), (String) in.readObject());
//        contact = (String) in.readObject();
//    }
}

class Name{
    String firstName;
    String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
