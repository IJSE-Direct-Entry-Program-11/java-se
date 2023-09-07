import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo4 {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<>();
        names.add("Nuwan");
        names.add("Ruwan");
        names.add("Supun");
        System.out.println(names);
        Collections.fill(names, "Kasun");
        System.out.println(names);

        ArrayList<Integer> numberList = new ArrayList<>(15);
        System.out.println(numberList);
        System.out.println(numberList.size());
    }
}
