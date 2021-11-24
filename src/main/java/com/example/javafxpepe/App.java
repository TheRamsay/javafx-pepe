package com.example.javafxpepe;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.io.IOException;

public class App extends Application {
	private SwingFXUtils SwingFXUTils;

	@Override
	public void start(Stage stage) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("app.fxml"));
		Scene scene = new Scene(fxmlLoader.load(), 1200, 750);
		stage.setTitle("Hello!");
		stage.setScene(scene);
		stage.setMinHeight(600);
		stage.setMinWidth(1050);
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}
}