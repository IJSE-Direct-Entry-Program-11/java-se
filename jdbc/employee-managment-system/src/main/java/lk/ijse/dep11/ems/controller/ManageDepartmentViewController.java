package lk.ijse.dep11.ems.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep11.ems.db.DbConnection;
import lk.ijse.dep11.ems.tm.Department;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManageDepartmentViewController {
    public TextField txtDepartmentName;
    public Button btnNewDepartment;
    public Button btnSave;
    public Button btnDelete;
    public TableView<Department> tblEmployees;

    public void initialize(){
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("numOfEmployees"));
        btnDelete.setDisable(true);

        tblEmployees.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) -> {
            btnDelete.setDisable(cur == null);
            if (cur != null){
                txtDepartmentName.setText(cur.getName());
            }
        });

        loadAllDepartments();
    }

    private void loadAllDepartments(){
        Connection connection = DbConnection.getInstance().getConnection();
        ObservableList<Department> departmentList = tblEmployees.getItems();
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM department");
            while (rst.next()){
                int id = rst.getInt("id");
                String name = rst.getString("name");
                Department department = new Department(id, name, 0);
                departmentList.add(department);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewDepartmentOnAction(ActionEvent actionEvent) {
        txtDepartmentName.clear();
        tblEmployees.getSelectionModel().clearSelection();
        txtDepartmentName.requestFocus();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        String departmentName = txtDepartmentName.getText().strip();

        /* Data Validation */
        if (!departmentName.matches("[A-Za-z0-9 ]{3,}")){
            txtDepartmentName.requestFocus();
            txtDepartmentName.selectAll();
            return;
        }

        ObservableList<Department> departmentList = tblEmployees.getItems();

        /* Business Validation */
        if (departmentList.stream().anyMatch(d -> d.getName().equals(departmentName))){
            new Alert(Alert.AlertType.ERROR, "Department name already exists").show();
            txtDepartmentName.requestFocus();
            txtDepartmentName.selectAll();
            return;
        }

        Connection connection = DbConnection.getInstance().getConnection();
        try {
            Statement stm = connection.createStatement();
            String sql = String.format("INSERT INTO department (name) VALUES ('%s')", departmentName);
            int affectedRows = stm.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            if (affectedRows == 1){
                new Alert(Alert.AlertType.INFORMATION, "Added").show();
                ResultSet generatedKeys = stm.getGeneratedKeys();
                generatedKeys.next();
                int departmentId = generatedKeys.getInt(1);
                departmentList.add(new Department(departmentId, departmentName, 0));
                btnNewDepartment.fire();
            }else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong badly, try again").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
