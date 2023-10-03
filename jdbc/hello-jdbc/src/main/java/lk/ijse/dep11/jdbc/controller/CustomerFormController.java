package lk.ijse.dep11.jdbc.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep11.jdbc.db.DbConnection;
import lk.ijse.dep11.jdbc.tm.Customer;

import java.sql.*;

public class CustomerFormController {
    public Button btnNewCustomer;
    public TextField txtID;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSave;
    public TableView<Customer> tblCustomers;

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        tblCustomers.getSelectionModel().selectedItemProperty().addListener((o, prev, cur) -> {
            btnSave.setDisable(cur != null);
            if (cur != null) {
                txtID.setText(cur.getId());
                txtName.setText(cur.getName());
                txtAddress.setText(cur.getAddress());
            }
        });

        loadAllCustomers();
    }

    private void loadAllCustomers(){
        // protocol://ipaddress:port/path
        // https://google.lk:443/search
        // http://ijse.lk:80/dep
        ObservableList<Customer> customerList = tblCustomers.getItems();

        try {
            Statement stm = DbConnection.getInstance().getConnection().createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM customer");
            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String address = rst.getString("address");
                Customer customer = new Customer(id, name, address);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnNewCustomerOnAction(ActionEvent actionEvent) {
        txtID.clear();
        txtName.clear();
        txtAddress.clear();
        txtID.requestFocus();
        tblCustomers.getSelectionModel().clearSelection();
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!isDataValid()) return;

        String id = txtID.getText().strip();
        String name = txtName.getText().strip();
        String address = txtAddress.getText().strip();
        ObservableList<Customer> customerList = tblCustomers.getItems();

        // Business Validation
        if (customerList.stream().anyMatch(c -> c.getId().equals(id))){
            new Alert(Alert.AlertType.ERROR, "Id already exists").show();
            txtID.requestFocus();
            txtID.selectAll();
            return;
        }

        try {
            Statement stm = DbConnection.getInstance().getConnection().createStatement();
            String sql = "INSERT INTO customer (id, name, address) VALUES ('%s','%s', '%s')";
            sql = String.format(sql, id, name, address);
            int affectedRows = stm.executeUpdate(sql);
            if (affectedRows == 1){
                new Alert(Alert.AlertType.INFORMATION, "Added").show();
                btnNewCustomer.fire();
                customerList.add(new Customer(id, name, address));
            }else{
                new Alert(Alert.AlertType.ERROR, "Something went wrong badly, try again").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean isDataValid(){
        String id = txtID.getText().strip();
        String name = txtName.getText().strip();
        String address = txtAddress.getText().strip();

        if (!id.matches("C\\d{3}")){
            txtID.requestFocus();
            txtID.selectAll();
            return false;
        }else if (!name.matches("[A-Za-z ]{2,}")){
            txtName.requestFocus();
            txtName.selectAll();
            return false;
        }else if (!address.matches(".{3,}")){
            txtAddress.requestFocus();
            txtAddress.selectAll();
            return false;
        }
        return true;
    }
}
