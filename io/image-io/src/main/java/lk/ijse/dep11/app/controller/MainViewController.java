package lk.ijse.dep11.app.controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class MainViewController {
    public AnchorPane root;
    public ImageView imvDisplay;
    public Button btnOpen;
    public Button btnSave;
    public TextField txtURL;
    public Button btnLoadImage;

    public void btnOpenOnAction(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.jpeg", "*.jpg", "*.png", "*.gif", "*.bmp"));
        fileChooser.setTitle("Open image files");
        File openFile = fileChooser.showOpenDialog(root.getScene().getWindow());
        if (openFile == null) {
            imvDisplay.setImage(null);
            return;
        }
        BufferedImage bufImage = ImageIO.read(openFile);
        WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
        imvDisplay.setImage(fxImage);
    }

    public void btnSaveOnAction(ActionEvent actionEvent) {
        if (imvDisplay.getImage() == null) return;

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save the image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPEG Image (*.jpeg, *.jpg)", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG Image (*.png)", "*.png"),
                new FileChooser.ExtensionFilter("GIF Image (*.gif)", "*.gif"),
                new FileChooser.ExtensionFilter("BMP Image (*.bmp)", "*.bmp")
        );
        File file = fileChooser.showSaveDialog(root.getScene().getWindow());
        if (file == null) return;

        String format = "png";
        switch (fileChooser.getSelectedExtensionFilter().getExtensions().get(0)) {
            case "*.jpeg":
                format = "jpeg";
                break;
            case "*.png":
                format = "png";
                break;
            case "*.bmp":
                format = "bmp";
                break;
            case "*.gif":
                format = "gif";
                break;
        }

        Image fxImage = imvDisplay.getImage();
        BufferedImage bufImage = SwingFXUtils.fromFXImage(fxImage, null);

        // Since JDK 11
        boolean alphaChannel = format.equals("gif") || format.equals("png");
        BufferedImage tempImage = new BufferedImage(bufImage.getWidth(), bufImage.getHeight(),
                alphaChannel ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        if (alphaChannel) {
            tempImage.createGraphics().drawImage(bufImage, 0, 0, null);
        } else {
            tempImage.createGraphics().drawImage(bufImage, 0, 0, Color.WHITE, null);
        }

        try {
            File imageFile = file;
            if (!file.getAbsolutePath().endsWith(format)) {
                imageFile = new File(file.getAbsolutePath() + "." + format);
            }
            boolean saved = ImageIO.write(tempImage, format, imageFile);
            if (saved) {
                new Alert(Alert.AlertType.INFORMATION, "Image Saved!").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something went wrong, please try again").show();
            }
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the image").show();
            e.printStackTrace();
        }
    }

    public void btnLoadImageOnAction(ActionEvent actionEvent) {
        if (txtURL.getText().isBlank()) {
            txtURL.requestFocus();
            txtURL.selectAll();
            return;
        }

        try {
            URL imageUrl = new URL(txtURL.getText().strip());
            BufferedImage bufImage = ImageIO.read(imageUrl);
            WritableImage fxImage = SwingFXUtils.toFXImage(bufImage, null);
            imvDisplay.setImage(fxImage);
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to fetch the image, check your url").show();
            e.printStackTrace();
            imvDisplay.setImage(null);
            txtURL.requestFocus();
            txtURL.selectAll();
        }
    }
}
