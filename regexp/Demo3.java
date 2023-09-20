public class Demo3 {
    public static void main(String[] args) {
        // 12345    - true
        // 12345a   - false
        // aaaa     - false
        // -----------------
        // +12345, -12345
        // 123456789123456789
        var input = "123456789123456789";
        System.out.println(isDigits1(input));
        System.out.println(isDigits2(input));
    }

    public static boolean isDigits1(String input){
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }

    public static boolean isDigits2(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
