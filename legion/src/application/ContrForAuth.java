package application;
import java.net.URL;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.text.Text;

/*
 * Класс-контроллер для панельки аутентификации. Переводит при правильном введении логина и
 * пароля на новое окно
 */
public class ContrForAuth {

    @FXML
    private TextField tfLogin;

    @FXML
    private Button bEnter;

    @FXML
    private PasswordField tfPassword;
    
    @FXML
    private Text text;
    //Метод входа (по клику кнопки)
    @FXML
    void enter(ActionEvent event) {
    	//если верно введены логин и пароль
    	if (tfLogin.getText().equals("admin") && tfPassword.getText().equals("admin")) {
			try {
				//методы закрытия этого окна
				Stage stage = (Stage) bEnter.getScene().getWindow();
			    stage.close();
			    //методы открытия нового окна
				Stage primaryStage = new Stage();
				//изъятие файла, вернее пути к файлу
				URL url = getClass().getResource("main.fxml");
				System.out.println(url);
				//создание основного изображения
				AnchorPane root = (AnchorPane)FXMLLoader.load(url);
				Scene scene = new Scene(root,1200,600);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Main");
				//отображение
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		text.setText("Неверный логин/пароль");
    }

}

