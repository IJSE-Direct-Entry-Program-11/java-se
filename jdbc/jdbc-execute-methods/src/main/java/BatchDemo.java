import db.SingleConnectionDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchDemo {

    public static void main(String[] args) {

        try (Connection connection = SingleConnectionDataSource.getInstance().getConnection()) {
            Statement stm = connection.createStatement();
            stm.addBatch("INSERT INTO customer (name, address) VALUES ('Kasun', 'Galle')");
            stm.addBatch("INSERT INTO customer (name, address) VALUES ('Nuwan', 'Galle')");
            stm.addBatch("INSERT INTO customer (name, address) VALUES ('Upul', 'Galle')");
            stm.addBatch("INSERT INTO customer (name, address) VALUES ('Supun', 'Galle')");
            stm.addBatch("DELETE FROM customer WHERE address='Galle'");
            int[] affectedRows = stm.executeBatch();
            for (int affectedRow : affectedRows) {
                System.out.println(affectedRow);    // 1,1,1,1,4
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
