package lk.ijse.dep11.sqlgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.ijse.dep11.sqlgame.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        try (Connection connection = DbConnection.getInstance().getConnection()) {
            launch(args);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("/view/HomeView.fxml"))));
        primaryStage.setTitle("SQL Challenge");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}

/*
* 1. Create a new resource bundle called "application.properties" to store
*                        database server connection properties
* 2. Create a singleton class called "DbConnection" to store a singleton connection instance
* 3. Establish the database connection via the singleton connection at the app startup
* 4. Close the database connection when the jvm shutdowns
* */



