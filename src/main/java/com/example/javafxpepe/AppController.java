package com.example.javafxpepe;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventTarget;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

	@FXML
	public RadioButton modifiedImgRadioButton;

	@FXML
	public void handleExitMenuClick(ActionEvent event) {
		Platform.exit();
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		modifiedImgRadioButton.setSelected(true);
	}
}