package lk.ijse.dep11.ems.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardViewController {
    public AnchorPane root;
    public Button btnManageDepartments;
    public Button btnManageEmployees;

    public void btnManageDepartmentsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader
                .load(getClass().getResource("/view/ManageDepartmentView.fxml"))));
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(root.getScene().getWindow());
        stage.setTitle("EMS: Manage Departments");
        stage.setResizable(false);
        stage.show();
        stage.centerOnScreen();
    }

    public void btnManageEmployeesOnAction(ActionEvent actionEvent) {
    }
}
