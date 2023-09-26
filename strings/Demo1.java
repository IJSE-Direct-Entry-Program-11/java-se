public class Demo1 {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "Hello";
        System.out.println(str1 == str2);           // true
        System.out.println(str1.equals(str2));      // true
        String str3 = new String("Hello");
        System.out.println(str1 == str3);           // false
        System.out.println(str2 == str3);           // false
        System.out.println(str1.equals(str3));      // true

        System.out.println(str1 == str3.intern());  // true
        System.out.println(str2 == str3.intern());  // true

        String str4 = "Hello" + "DEP";
        String str5 = "Hello" + "DEP" + "Welcome" + "Born" + "To" + "Code";
    }
}