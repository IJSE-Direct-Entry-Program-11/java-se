import java.io.*;

public class Demo2 {

    public static void main(String[] args) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        try {
            B b = new B();
            b.x = 10;
            b.y = 20;
            oos.writeObject(b);
            byte[] byteArray = baos.toByteArray();

            System.out.println("------------------------");

            ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
            ObjectInputStream ois = new ObjectInputStream(bais);
            try{
                b = (B) ois.readObject();
                System.out.println(b.x);
                System.out.println(b.y);
            }finally {
                ois.close();
            }
        }finally {
             oos.close();
        }
    }
}

class A {
    {
        System.out.println("A: Instance Initializer");
    }
    int x = 35;
}

class B extends A implements Serializable {
    {
        System.out.println("B: Instance Initializer");
    }
    int y = 45;
}

