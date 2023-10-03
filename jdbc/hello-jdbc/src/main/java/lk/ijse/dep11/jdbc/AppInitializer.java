package lk.ijse.dep11.jdbc;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep11.jdbc.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        try(var connection = DbConnection.getInstance().getConnection()) {
            launch(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("/view/CustomerForm.fxml"))));
        primaryStage.setTitle("Hello JDBC");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
