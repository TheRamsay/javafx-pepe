package com.example.javafxpepe;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

	private File currentFile;

	@FXML
	public RadioButton modifiedImgRadioButton;

	@FXML
	public MenuBar menuBar;

	@FXML
	public ImageView imageView;

	@FXML
	public void handleExitMenuClick(ActionEvent event) {
		Platform.exit();
	}

	@FXML
	public void handleFileUpload (ActionEvent event) throws FileNotFoundException {
		Stage tempStage = new Stage();

		FileChooser fileChooser = new FileChooser();
		File selectedFile = fileChooser.showOpenDialog(tempStage);
		currentFile = selectedFile;
		InputStream stream = new FileInputStream(String.valueOf(selectedFile));
		Image image = new Image(stream);

		imageView.setImage(image);

		imageView.setFitWidth(650);
		imageView.setFitHeight(650);
	}

	@FXML
	public void handleFileSave(ActionEvent event) {
		Stage mainStage = (Stage) menuBar.getScene().getWindow();
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

	@FXML
	public void generateImage() {
		BufferedImage image = makeColoredImage();
		imageView.setImage(SwingFXUtils.toFXImage(image, null));

		currentFile = new File("./unknown.png");
		imageView.setFitWidth(650);
		imageView.setFitHeight(650);
	}

	public BufferedImage makeColoredImage(){
		BufferedImage bImage = new BufferedImage(600, 600, BufferedImage.TYPE_3BYTE_BGR);
		for (int x = 0; x < bImage.getWidth(); x++){
			for (int y = 0; y < bImage.getHeight(); y++){
				bImage.setRGB(x, y, (new Color((x+10)%(255),(x*20)%(255),(x*y)%255).getRGB()));
			}
		}
		return bImage;
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		modifiedImgRadioButton.setSelected(true);
	}

	@FXML
	public void applyNegative () {
		try {
			BufferedImage originalImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
			BufferedImage filteredImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());
			for (int x = 0; x < originalImage.getWidth(); x++) {
				for (int y = 0; y < originalImage.getHeight(); y++) {
					int rgbOrig = originalImage.getRGB(x, y);
					Color c = new Color(rgbOrig);
					int r = 255 - c.getRed();
					int g = 255 - c.getGreen();
					int b = 255 - c.getBlue();
					Color nc = new Color(r, g, b);
					filteredImage.setRGB(x, y, nc.getRGB());
				}
			}
			imageView.setImage(SwingFXUtils.toFXImage(filteredImage, null));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

