import java.util.List;

public class ReducerDemo {

    public static void main(String[] args) {
        List<Integer> numbers = List.of(10, 20, 30, 40);
        int val = numbers.stream().reduce((prev, curr) -> {
            System.out.printf("%s: %s \n", prev, curr);
            return  prev + curr;
        }).get();
        System.out.println(val);
    }
}
