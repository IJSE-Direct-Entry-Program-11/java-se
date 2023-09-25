package lk.ijse.dep11.app.client.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public TextField txtName;
    public Button btnJoin;
    public AnchorPane root;

    public void btnJoinOnAction(ActionEvent actionEvent) throws IOException {
        Stage primaryStage = (Stage)root.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainForm.fxml"));
        Scene mainScene = new Scene(fxmlLoader.load());
        MainFormController controller = fxmlLoader.getController();
        controller.initData(txtName.getText());
        primaryStage.setScene(mainScene);
        primaryStage.centerOnScreen();
    }
}
