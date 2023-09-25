package lk.ijse.dep11.video.controller;

import com.github.sarxos.webcam.Webcam;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MainViewController {
    public AnchorPane camContainer;
    public ImageView imvCam;
    public AnchorPane imvCamWrapperWrapper;
    public FlowPane imvCamWrapper;
    private double xPos;
    private double yPos;

    public void initialize(){
        imvCamWrapperWrapper.prefWidthProperty().bind(imvCamWrapper.prefWidthProperty());
        imvCamWrapperWrapper.prefHeightProperty().bind(imvCamWrapper.prefHeightProperty());
        imvCamWrapper.prefWidthProperty().bind(imvCam.fitWidthProperty());
        imvCamWrapper.prefHeightProperty().bind(imvCam.fitHeightProperty());
        Webcam camera = Webcam.getDefault();
        camera.open();
        Thread t1 = new Thread(() -> {
            while (true) {
                if (!camera.isOpen()) return;
                BufferedImage img = camera.getImage();
                WritableImage fxImage = SwingFXUtils.toFXImage(img, null);
                Platform.runLater(() -> {
                    imvCam.setImage(fxImage);
                });
            }
        });
        t1.setDaemon(true);
        t1.start();
    }

    public void camOnMousePressed(MouseEvent mouseEvent) {
        xPos = mouseEvent.getX();
        yPos = mouseEvent.getY();
    }

    public void camOnMouseDragged(MouseEvent mouseEvent) {
        imvCamWrapperWrapper.setLayoutX(mouseEvent.getSceneX() - xPos);
        imvCamWrapperWrapper.setLayoutY(mouseEvent.getSceneY() - yPos);
    }
}
