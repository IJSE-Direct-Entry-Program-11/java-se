import db.SingleConnectionDataSource;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class EnableAutomaticSchemeGeneration {

    public static void main(String[] args) {
        try (var connection = SingleConnectionDataSource.getInstance().getConnection()) {

            URI resourceURI = EnableAutomaticSchemeGeneration.class.getResource("/schema.sql").toURI();
            Path path = Paths.get(resourceURI);
            List<String> lines = Files.readAllLines(path);
            String script = lines.stream().reduce((prev, curr) -> prev + curr + "\n").get();

            Statement stm = connection.createStatement();
            boolean result = stm.execute(script);

            System.out.println(result);     // false

            System.out.println(stm.getUpdateCount());   // 3
            System.out.println(stm.getMoreResults());   // true
            ResultSet rst = stm.getResultSet();

            while (rst.next()) {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                System.out.printf("id=%s, name=%s, address=%s \n", id, name, address);
            }

            System.out.println(stm.getMoreResults());   // false
            System.out.println(stm.getUpdateCount());  // 1

            System.out.println(stm.getMoreResults());   // true
            rst = stm.getResultSet();
            if (rst.next()) {
                System.out.println(rst.getInt(1));      // 2
            }
            System.out.println(stm.getMoreResults());   // true
            rst = stm.getResultSet();
            while (rst.next()) {
                int id = rst.getInt("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                System.out.printf("id=%s, name=%s, address=%s \n", id, name, address);
            }
        } catch (SQLException | IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }
}
