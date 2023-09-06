package lk.ijse.dep11.app.controller;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.*;

public class MainViewController {
    public TextField txtSource;
    public Button btnBrowseSource;
    public TextField txtTarget;
    public Button btnBrowseTarget;
    public Button btnCopy;
    public ProgressBar pbCopy;
    public Label lblStatus;
    public Label lblProgress;
    public AnchorPane root;

    public void initialize() {
        btnCopy.setDisable(true);
    }

    public void btnBrowseSourceOnAction(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select the source file");
        File file = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (file != null) {
            txtSource.setText(file.getAbsolutePath());
        } else {
            txtSource.clear();
        }
        btnCopy.setDisable(txtSource.getText().isBlank() || txtTarget.getText().isBlank());
    }

    public void btnBrowseTargetOnAction(ActionEvent actionEvent) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select the target folder");
        File file = directoryChooser.showDialog(root.getScene().getWindow());
        if (file != null) {
            txtTarget.setText(file.getAbsolutePath());
        } else {
            txtTarget.clear();
        }
        btnCopy.setDisable(txtSource.getText().isBlank() || txtTarget.getText().isBlank());
    }

    public void btnCopyOnAction(ActionEvent actionEvent) throws FileNotFoundException {
        btnCopy.setDisable(true);
        Task<Void> copyTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                copy();
                Platform.runLater(() -> btnCopy.setDisable(false));
                return null;
            }
        };
        new Thread(copyTask).start();
    }

    void copy() throws FileNotFoundException {
        File sourceFile = new File(txtSource.getText());
        //File targetFile = new File(txtTarget.getText() + "/" + sourceFile.getName());
        File targetFile = new File(txtTarget.getText(), sourceFile.getName());

        try {
            targetFile.createNewFile();
        } catch (IOException e) {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Failed to copy").show());
            e.printStackTrace();
            return;
        }

        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetFile);
        try {

            int read = 0;
            int copied = 0;
            while ((read = fis.read()) != -1) {
                fos.write(read);
                copied++;
                updateProgress(copied, sourceFile.length());
                // For smooth UI rendering, let's give a break to our thread after each kilobyte
                if (copied % 1024 == 0) Thread.sleep(25);
            }

            fos.close();
            fis.close();

            Platform.runLater(() -> new Alert(Alert.AlertType.INFORMATION, "Copied successfully").show());
        } catch (IOException e) {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Failed to copy, try again!").show());
            e.printStackTrace();
        } catch (InterruptedException e) {
            Platform.runLater(() -> new Alert(Alert.AlertType.ERROR, "Failed to copy, try again!").show());
            e.printStackTrace();
        }
    }

    void updateProgress(long copied, long total) {
        Platform.runLater(() -> {
            lblProgress.setText(String.format("%.2f", (copied / (total * 1.0)) * 100).concat("%"));
            lblStatus.setText(String.format("Copied %d/%d Bytes", copied, total));
            pbCopy.setProgress(copied / (total * 1.0));
        });
    }
}
