import java.util.ArrayList;
import java.util.Collections;

public class CollectionsDemo2 {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(20);
        numbers.add(15);
        numbers.add(75);
        numbers.add(0);
        numbers.add(-3);

        ArrayList<String> names = new ArrayList<>();
        names.add("Kasun");
        names.add("Achini");
        names.add("John");
        names.add("Shehan");
        names.add("Ruwan");

        System.out.println(numbers);
        System.out.println(names);

        // Sorting Alogirthm = TimSort(Insertion Sort + Merge Sort) Hybrid Sorting Algorithm
        Collections.sort(numbers);  // Natural Order [0-9] [A-Z]
        Collections.sort(names);

        System.out.println(numbers);
        System.out.println(names);
    }
}
