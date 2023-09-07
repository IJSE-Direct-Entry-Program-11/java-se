import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo3 {
    public static void main(String[] args) {
        
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(80);
        numbers.add(90);
        numbers.add(30);
        numbers.add(20);
        numbers.add(50);

        ArrayList<String> names = new ArrayList<>();
        names.add("Kasun");
        names.add("Nuwan");
        names.add("Ruwan");
        names.add("Achala");
        names.add("Supuni");

        Collections.sort(numbers);
        System.out.println(numbers);
        int index = Collections.binarySearch(numbers, 80);
        System.out.println(index);

        Collections.sort(names);
        System.out.println(names);
        index = Collections.binarySearch(names, "Ruwan");
        System.out.println(index);
    }
}
