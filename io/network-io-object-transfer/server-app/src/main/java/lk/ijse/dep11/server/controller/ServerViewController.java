package lk.ijse.dep11.server.controller;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.dep11.shared.to.Customer;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerViewController {
    public TableView<Customer> tblCustomers;

    public void initialize(){
        tblCustomers.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomers.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomers.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));

        new Thread(this::startServer).start();
    }

    private void startServer(){
        try {
            ServerSocket serverSocket = new ServerSocket(5050);
            while (true){
                System.out.println("Waiting for client connection");
                Socket localSocket = serverSocket.accept();
                System.out.println("Client connected: " + localSocket);
                new Thread(()->{
                    try {
                        InputStream is = localSocket.getInputStream();
                        BufferedInputStream bis = new BufferedInputStream(is);
                        ObjectInputStream ois = new ObjectInputStream(bis);
                        while (true) {
                            Customer customer = (Customer) ois.readObject();
                            Platform.runLater(()-> tblCustomers.getItems().add(customer));
                        }
                    }catch (IOException e){
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
