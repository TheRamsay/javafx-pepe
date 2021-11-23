package com.example.javafxpepe;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

	private File currentFile;

	@FXML
	public RadioButton modifiedImgRadioButton;

	@FXML
	public ImageView imageView;

	@FXML
	public void handleExitMenuClick(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void handleFileUpload (ActionEvent event) throws FileNotFoundException {
		Stage mainStage = (Stage)((Button)event.getSource()).getScene().getWindow();

		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(mainStage);
		currentFile = selectedFile;
		InputStream stream = new FileInputStream(String.valueOf(selectedFile));
		Image image = new Image(stream);

		imageView.setImage(image);

		imageView.setFitWidth(720);
		imageView.setFitHeight(620);
	}

	@FXML
	public void handleFileSave(ActionEvent event) {
		Stage mainStage = (Stage)((Button)event.getSource()).getScene().getWindow();
		if (currentFile != null) {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setInitialFileName(currentFile.getName());
			File selectedFile = fileChooser.showSaveDialog(mainStage);
			if (selectedFile != null) {

				try {
					ImageIO.write(SwingFXUtils.fromFXImage(imageView.getImage(), null), "png", selectedFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		modifiedImgRadioButton.setSelected(true);
	}

}