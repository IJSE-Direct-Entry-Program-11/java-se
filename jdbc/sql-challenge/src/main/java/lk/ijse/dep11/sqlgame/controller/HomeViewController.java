package lk.ijse.dep11.sqlgame.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lk.ijse.dep11.sqlgame.controller.tm.Student;
import lk.ijse.dep11.sqlgame.db.DbConnection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HomeViewController {
    public Button btnManageStudents;
    public Label lblDisplay;
    public AnchorPane root;
    public Button btnStart;
    public Button btnFinish;

    public void initialize(){
        btnFinish.setDisable(true);
    }

    public void btnManageStudentsOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass()
                .getResource("/view/ManageStudentsView.fxml"))));
        stage.setTitle("SQL Challenge: Manage Students");
        stage.setResizable(false);
        stage.initOwner(root.getScene().getWindow());
        stage.initModality(Modality.WINDOW_MODAL);
        stage.show();
        stage.centerOnScreen();
    }

    public void btnStartOnAction(ActionEvent actionEvent) {
        btnStart.setText("PICK");
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM student WHERE status = FALSE");
            List<Student> studentList = new ArrayList<>();
            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String card = rst.getString("card");
                card = card.equalsIgnoreCase("null") ? "" : card;
                studentList.add(new Student(id, name, card, "YET TO FACE"));
            }
            Task<Void> animateStudentNames = new Task<>() {

                @Override
                protected Void call() throws Exception {
                    while(true){
                        for (Student student : studentList) {
                            if (!student.getCard().trim().isBlank()) {
                                Platform.runLater(()-> lblDisplay.setText(student.getName() + ":" + student.getCard()));
                            }else{
                                Platform.runLater(()-> lblDisplay.setText(student.getName()));
                            }
                            Thread.sleep(100);
                        }
                    }
                }
            };

            new Thread(animateStudentNames).start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnFinishOnAction(ActionEvent actionEvent) {
    }
}
