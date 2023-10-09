package lk.ijse.dep11.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep11.app.db.SingleConnectionDataSource;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.sql.*;

public class LoginFormController {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Button btnLogin;
    public AnchorPane root;
    private PreparedStatement stm;

    public void initialize(){
        Connection connection = SingleConnectionDataSource.getInstance().getConnection();
        try {
            stm = connection.prepareStatement("SELECT * FROM user WHERE username=? AND password=?");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText().strip();
        String password = txtPassword.getText().strip();
//        Connection connection = SingleConnectionDataSource.getInstance().getConnection();
        try {
//            Statement stm = connection.createStatement();
//            ResultSet rst = stm.executeQuery(String.format
//                    ("SELECT * FROM user WHERE username='%s' AND password='%s'", username, password));

            stm.setString(1, username);
            stm.setString(2, DigestUtils.sha256Hex(password));
            ResultSet rst = stm.executeQuery();

            if (rst.next()) {
                navigateToApp();
            } else {
                new Alert(Alert.AlertType.ERROR, "Access Denied: Invalid Login Credentials").show();
                txtUsername.requestFocus();
                txtUsername.selectAll();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    private void navigateToApp() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("/form/AppForm.fxml"))));
        stage.setTitle("My App (SQL Injections)");
        stage.show();
        stage.setMaximized(true);
        ((Stage) (root.getScene().getWindow())).close();
    }
}
