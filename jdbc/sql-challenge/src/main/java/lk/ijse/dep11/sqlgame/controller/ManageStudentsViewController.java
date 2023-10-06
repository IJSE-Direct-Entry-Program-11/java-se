package lk.ijse.dep11.sqlgame.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import lk.ijse.dep11.sqlgame.controller.tm.Student;
import lk.ijse.dep11.sqlgame.db.DbConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManageStudentsViewController {
    public Button btnNewStudent;
    public Button btnLoadStudentsFromCsv;
    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtStudentCard;
    public Button btnSave;
    public Button btnDelete;
    public TableView<Student> tblStudents;

    public void initialize() {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("card"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("challengeStatus"));

        btnDelete.setDisable(true);

        tblStudents.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) -> {
            btnDelete.setDisable(cur == null);
            if (cur != null) {
                txtStudentId.setText(cur.getId());
                txtStudentName.setText(cur.getName());
                txtStudentCard.setText(cur.getCard());
            }
        });
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        for (var txt : new TextField[]{txtStudentId, txtStudentName, txtStudentCard}) txt.clear();
        tblStudents.getSelectionModel().clearSelection();
        txtStudentId.requestFocus();
    }

    public void btnLoadStudentsFromCsvOnAction(ActionEvent actionEvent) {
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
    }

    public boolean addNewStudent(String id, String name, String card) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();
            Statement stmQuery = connection.createStatement();
            String sql = String.format("SELECT id FROM student WHERE id='%s'", id);
            ResultSet rst = stmQuery.executeQuery(sql);
            if (rst.next()) {
                return false;
            }

            Statement stmInsert = connection.createStatement();
            sql = String.format("INSERT INTO student (id, name, card) VALUES ('%s','%s','%s')",
                    id, name, card);
            int affectedRows = stmInsert.executeUpdate(sql);
            if (affectedRows == 1) {
                return true;
            } else {
                throw new RuntimeException("Something went wrong badly");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteStudent(String id) {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            Statement stmDelete = connection.createStatement();
            String sql = String.format("DELETE FROM student WHERE id='%s'", id);
            int affectedRows = stmDelete.executeUpdate(sql);
            return (affectedRows == 1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidDepStudentId(String id) {
        if (!id.matches("\\d{2}03\\d{2}1\\d{3}")) return false;

        int year = Integer.parseInt(id.substring(0, 2));
        int currentYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yy")));
        if (!(year >= 18 && year <= currentYear)) return false;

        int batch = Integer.parseInt(id.substring(4, 6));
        int currentBatch = Integer.parseInt(System.getProperty("dep.batch", "11"));
        if (!(batch > 0 && batch <= currentBatch)) return false;

        int studentNumber = Integer.parseInt(id.substring(id.length() - 3));
        return (studentNumber > 0 && studentNumber < 80);
    }
}
