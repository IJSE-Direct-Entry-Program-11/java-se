import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Demo1 {
    public static void main(String[] args) {
        // IEEE-754
        double d1 = 0.3;
        double d2 = 0.2;
        double d3 = d1 - d2;
        System.out.println(d3);

        // BigDecimal is also immutable
        BigDecimal bd1 = BigDecimal.valueOf(0.3); //new BigDecimal("0.3");
        BigDecimal bd2 = BigDecimal.valueOf(0.2); //new BigDecimal("0.2");
        BigDecimal bd3 = bd1.subtract(bd2);
        System.out.println(bd3);

        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem("Mouse", 5, new BigDecimal("500.00")));
        cartItemList.add(new CartItem("KeyBoard", 2, new BigDecimal("900.00")));
        cartItemList.add(new CartItem("Monitors", 3, new BigDecimal("6500.00")));

        /*
            +--------------+---------+---------------+----------------+
            | NAME         | QTY.    | UNIT PRICE    | TOTAL          |
            +--------------+---------+---------------+----------------+
            | Mouse        | 5       | Rs.500.00     | Rs. 2,500.00   |
            | Keyboard     | 2       | Rs.900.00     | Rs. 1,800.00   |
            | Monitors     | 3       | Rs.6,500.00   | Rs. 19,500.00  |
            +--------------+---------+---------------+----------------+
            | NET TOTAL                              | Rs. xxxx.xx    |
            +--------------+---------+---------------+----------------+

         */

         final String LINE = String.format("+%s+%s+%s+%s+", 
                                                    "-".repeat(15), 
                                                    "-".repeat(10),
                                                    "-".repeat(15),
                                                    "-".repeat(15));

        System.out.println(LINE);
        System.out.printf("|%-15s|%10s|%15s|%15s|\n", 
                                "NAME", "QTY.", "UNIT PRICE", "TOTAL");
        System.out.println(LINE);
        //double total = 0.0;
        BigDecimal netTotal = BigDecimal.ZERO;
        for (CartItem cartItem : cartItemList) {
            BigDecimal total = new BigDecimal(cartItem.qty).multiply(cartItem.unitPrice);
            System.out.printf("|%-15s|%10s|%,15.2f|%,15.2f|\n", 
                                cartItem.name,
                                cartItem.qty,
                                cartItem.unitPrice,
                                total);
            netTotal = netTotal.add(total);
        }
        System.out.println(LINE);
        System.out.printf("|NET TOTAL:%,48.2f|\n", netTotal);
        System.out.printf("|DISCOUNT:%49s|\n", "10%");
        // discount = 10*100 = 10%
        //BigDecimal discount = new BigDecimal(10).divide(new BigDecimal(100));
        BigDecimal discount = new BigDecimal("0.1");
        // discountPrice = netTotal * 10%
        BigDecimal discountPrice = netTotal.multiply(discount);
        // Total = NetTotal - discountPrice;
        System.out.printf("|TOTAL:%,52.2f|\n", netTotal.subtract(discountPrice));
        System.out.printf("+%s+\n", "-".repeat(58));
    }
}

class CartItem {
    String name;
    int qty;
    BigDecimal unitPrice;

    public CartItem(String name, int qty, BigDecimal unitPrice){
        this.name = name;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }
}