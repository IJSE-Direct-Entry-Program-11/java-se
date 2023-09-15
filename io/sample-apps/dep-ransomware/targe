import java.util.Arrays;
import java.util.Scanner;

public class InsertionAlgo {
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int[] nums = {-10, -5, 1, 3, 7, 9};

        do{
            System.out.print("Enter a number to insert: ");
            int insertion = scanner.nextInt();
            scanner.nextLine();

            int insertionIndex = 0;
            if (insertion > nums[nums.length - 1]){
                insertionIndex = nums.length;
            }else{
                for (int i = 0; i < nums.length; i++) {
                    if (insertion <= nums[i]){
                        insertionIndex  = i;
                        break;
                    }
                }
            }  
            
            int[] temp = new int[nums.length + 1];

            for (int i = 0; i < insertionIndex; i++) {
                temp[i] = nums[i];
            }

            temp[insertionIndex] = insertion;

            for (int i = insertionIndex + 1; i < temp.length; i++) {
                temp[i] = nums[i - 1];
            }            

            System.out.println(Arrays.toString(temp));
            nums = temp;
        }while(true);
    }
}
