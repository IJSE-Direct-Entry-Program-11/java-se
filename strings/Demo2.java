public class Demo2 {
    public static void main(String[] args) {
        // Builder Design Pattern (Creational Design Pattern)
        // 1. Creational    (Builder)
        // 2. Behavioral    (Observerable)
        // 3. Structural

        var sb = new StringBuilder();   // StringBuffer
        String result = sb.append("Hello")
                    .append("DEP")
                    .append("Welcome")
                    .append("to")
                    .append("IJSE")
                    .deleteCharAt(5)
                    .append("abc")
                    .toString();
        // sb.append("Hello");
        // sb.append("DEP");
        // sb.append("Welcome");
        // sb.append("Hello").append("DEP").append("Welcome");

        // Hello                    c   sb
        // DEP                      c   sb
        // Welcome                  c   sb
        // to                       c   sb
        // IJSE                     c   sb
        // HelloDEP                 c
        // HelloDEPWelcome          c
        // HelloDEPWelcometo        c
        // HeloDEPWelcometoIJSE     c   sb
    }
}
