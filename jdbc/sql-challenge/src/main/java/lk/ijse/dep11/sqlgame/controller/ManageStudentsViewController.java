package lk.ijse.dep11.sqlgame.controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import lk.ijse.dep11.sqlgame.controller.tm.Student;
import lk.ijse.dep11.sqlgame.db.DbConnection;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Stream;

public class ManageStudentsViewController {
    public Button btnNewStudent;
    public Button btnLoadStudentsFromCsv;
    public TextField txtStudentId;
    public TextField txtStudentName;
    public TextField txtStudentCard;
    public Button btnSave;
    public Button btnDelete;
    public TableView<Student> tblStudents;
    public AnchorPane root;

    public void initialize() {
        tblStudents.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblStudents.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblStudents.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("card"));
        tblStudents.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("challengeStatus"));

        btnDelete.setDisable(true);

        tblStudents.getSelectionModel().selectedItemProperty().addListener((ov, prev, cur) -> {
            btnDelete.setDisable(cur == null);
            btnSave.setDisable(cur != null);

            if (cur != null) {
                txtStudentId.setText(cur.getId());
                txtStudentName.setText(cur.getName());
                txtStudentCard.setText(cur.getCard());
            }
        });

        loadAllStudents();
    }

    private void loadAllStudents(){
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            Statement stm = connection.createStatement();
            ResultSet rst = stm.executeQuery("SELECT * FROM student");
            while (rst.next()){
                String id = rst.getString("id");
                String name = rst.getString("name");
                String card = rst.getString("card");
                card = card.equalsIgnoreCase("null") ? "" : card;
                String status = rst.getBoolean("status") ? "FACED" : "YET TO FACE";
                tblStudents.getItems().add(new Student(id, name, card, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void btnNewStudentOnAction(ActionEvent actionEvent) {
        for (var txt : new TextField[]{txtStudentId, txtStudentName, txtStudentCard}) txt.clear();
        tblStudents.getSelectionModel().clearSelection();
        txtStudentId.requestFocus();
    }

    public void btnLoadStudentsFromCsvOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Import Student Database");
        fileChooser.getExtensionFilters().add(new FileChooser
                .ExtensionFilter("CSV Files (*.csv)", "*.csv"));
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file == null) return;
        importStudentFromCsvFile(file);
    }

    private void importStudentFromCsvFile(File file){
        try(FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr)) {
            String firstLine = br.readLine();

            int idField = -1;
            int nameField = -1;
            int cardFiled = -1;

            String[] fields = firstLine.split(",");
            for (int i = 0; i < fields.length; i++) {
                String field = fields[i];
                if (field.equalsIgnoreCase("id")) idField = i;
                if (field.equalsIgnoreCase("name")) nameField = i;
                if (field.equalsIgnoreCase("card") ||
                        field.equalsIgnoreCase("nick name")) cardFiled = i;
            }

            if (idField == -1 || nameField == -1) return;

            String line;
            int imported = 0;
            while ((line = br.readLine()) != null){
                try {
                    fields = line.split(",");
                    String id = fields[idField];
                    String name = fields[nameField];
                    String card = null;
                    if (cardFiled != -1 && (cardFiled < fields.length )) card = fields[cardFiled];
                    try {
                        boolean result = addNewStudent(id, name, card);
                        if (result) {
                            tblStudents.getItems()
                                    .add(new Student(id, name, card, "YET TO FACE"));
                            imported++;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }catch (ArrayIndexOutOfBoundsException e){}
            }

            if (imported > 0) new Alert(Alert.AlertType.INFORMATION, "Imported: " + imported).show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (!isDataValid()) return;

        String id = txtStudentId.getText().strip();
        String name = txtStudentName.getText().strip();
        String card = txtStudentCard.getText().strip();

        try {
            boolean result = addNewStudent(id, name, card.isBlank() ? null : card);
            if (!result){
                new Alert(Alert.AlertType.ERROR, "Student ID already exists").show();
                txtStudentId.requestFocus();
                txtStudentId.selectAll();
            }else{
                tblStudents.getItems().add(new Student(id, name, card, "YET TO FACE"));
                new Alert(Alert.AlertType.INFORMATION, "Saved").show();
                btnNewStudent.fire();
            }
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR, "Failed to save the student").show();
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Optional<ButtonType> optButton = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete the student?",
                ButtonType.YES, ButtonType.NO).showAndWait();
        if (optButton.get() == ButtonType.YES){
            Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
            try {
                deleteStudent(selectedStudent.getId());
                tblStudents.getItems().remove(selectedStudent);
                btnNewStudent.fire();
            }catch (Exception e){
                new Alert(Alert.AlertType.ERROR, "Failed to delete the student").show();
                e.printStackTrace();
            }
        }
    }

    private boolean isDataValid(){
        String id = txtStudentId.getText().strip();
        String name = txtStudentName.getText().strip();
        String card = txtStudentCard.getText().strip();

        if (!isValidDepStudentId(id)){
            txtStudentId.requestFocus();
            txtStudentId.selectAll();
            return false;
        }else if (!name.matches("[A-Za-z ]{2,}")){
            txtStudentName.requestFocus();
            txtStudentName.selectAll();
            return false;
        }else if (!card.isBlank() && !card.matches("[A-Za-z ]{2,}")){
            txtStudentCard.requestFocus();
            txtStudentCard.selectAll();
            return false;
        }
        return true;
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

    public void rootOnDragDropped(DragEvent dragEvent) {
            if (!dragEvent.getDragboard().getFiles().isEmpty()){
                File file = dragEvent.getDragboard().getFiles().get(0);
                if (!file.getAbsolutePath().endsWith("csv")) return;
                importStudentFromCsvFile(file);
            }
    }

    public void rootOnDragOver(DragEvent dragEvent) {
        dragEvent.acceptTransferModes(TransferMode.ANY);
    }
}
