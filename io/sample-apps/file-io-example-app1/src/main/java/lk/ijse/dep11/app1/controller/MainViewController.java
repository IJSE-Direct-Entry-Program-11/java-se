package lk.ijse.dep11.app1.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep11.app1.tm.Employee;

import java.util.List;

public class MainViewController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtContact;
    public Button btnSave;
    public Button btnDelete;
    public TableView<Employee> tblEmployee;
    public TextField txtSearch;
    public Button btnNew;

    public void initialize(){
        for (Control control : new Control[]{txtId, txtName, txtContact, btnSave, btnDelete}) {
            control.setDisable(true);
        }

        tblEmployee.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblEmployee.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployee.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        /* Data Validation */
        if (!isDataValid()) return;

        List<Employee> employeeList = getEmployeeList();

        /* Business Validation */
        for (Employee employee : employeeList) {
            if (employee.getContact().equals(txtContact.getText())){
                new Alert(Alert.AlertType.ERROR, "Contact number already exists").show();
                txtContact.requestFocus();
                txtContact.selectAll();
                return;
            }
        }

        Employee newEmployee = new Employee(txtId.getText(),
                txtName.getText().strip(), txtContact.getText().strip());
        employeeList.add(newEmployee);
        btnNew.fire();
    }

    private List<Employee> getEmployeeList(){
        return tblEmployee.getItems();
    }

    private boolean isDataValid(){
        if (!txtName.getText().strip().matches("^[A-Za-z ]+")){
            txtName.requestFocus();
            txtName.selectAll();
            return false;
        } else if (!txtContact.getText().strip().matches("\\d{3}-\\d{7}")){
            txtContact.requestFocus();
            txtContact.selectAll();
            return false;
        }
        return true;
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public void btnNewOnAction(ActionEvent actionEvent) {
        txtId.setText(getNewEmployeeId());
        for (Control control : new Control[]{txtName, txtContact, btnSave}) {
            if (control instanceof TextField) ((TextField) control).clear();
            control.setDisable(false);
        }
        txtName.requestFocus();
    }

    private String getNewEmployeeId(){
        if (getEmployeeList().isEmpty()) return "E-001";

        String lastEmployeeId = getEmployeeList().get(getEmployeeList().size() - 1).getId();
        int newEmployeeId = Integer.parseInt(lastEmployeeId.substring(2)) + 1 ;
        return String.format("E-%03d", newEmployeeId);
    }
}
