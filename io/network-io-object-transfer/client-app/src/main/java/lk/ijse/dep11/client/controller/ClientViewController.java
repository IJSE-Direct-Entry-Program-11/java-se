package lk.ijse.dep11.client.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import lk.ijse.dep11.shared.to.Customer;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientViewController {
    public TextField txtId;
    public TextField txtName;
    public TextField txtAddress;
    public Button btnSubmit;
    private ObjectOutputStream oos;

    public void initialize(){
        try {
            Socket remoteSocket = new Socket("localhost", 5050);
            OutputStream os = remoteSocket.getOutputStream();
            BufferedOutputStream bos = new BufferedOutputStream(os);
            oos = new ObjectOutputStream(bos);
            System.out.println("Connected...!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        if (!txtId.getText().strip().matches("C\\d{3}")){
            txtId.requestFocus();
            txtId.selectAll();
            return;
        }else if (!txtName.getText().strip().matches("[A-Za-z ]+")){
            txtName.requestFocus();
            txtName.selectAll();
            return;
        }else if (txtAddress.getText().isBlank() || txtAddress.getText().strip().length() < 3){
            txtAddress.requestFocus();
            txtAddress.selectAll();
            return;
        }

        var customer = new Customer(txtId.getText(), txtName.getText(), txtAddress.getText());
        try {
            oos.writeObject(customer);
            oos.flush();
            txtId.clear();
            txtName.clear();
            txtAddress.clear();
            txtId.requestFocus();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
