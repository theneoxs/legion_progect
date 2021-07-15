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
 * �����-���������� ��� �������� ��������������. ��������� ��� ���������� �������� ������ �
 * ������ �� ����� ����
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
    //����� ����� (�� ����� ������)
    @FXML
    void enter(ActionEvent event) {
    	//���� ����� ������� ����� � ������
    	if (tfLogin.getText().equals("admin") && tfPassword.getText().equals("admin")) {
			try {
				//������ �������� ����� ����
				Stage stage = (Stage) bEnter.getScene().getWindow();
			    stage.close();
			    //������ �������� ������ ����
				Stage primaryStage = new Stage();
				//������� �����, ������ ���� � �����
				URL url = getClass().getResource("main.fxml");
				System.out.println(url);
				//�������� ��������� �����������
				AnchorPane root = (AnchorPane)FXMLLoader.load(url);
				Scene scene = new Scene(root,1200,600);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Main");
				//�����������
				primaryStage.show();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		text.setText("�������� �����/������");
    }

}

