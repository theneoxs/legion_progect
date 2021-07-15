package application;
	
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/*
 * Точка входа в программу
 */
public class Main extends Application {
	public static String login = "root";
	public static String password = "qwerty";
	
	URL url = null;
	@Override
	public void start(Stage primaryStage) {
		try {
			url = getClass().getResource("authorization.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,512,512);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Login");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
