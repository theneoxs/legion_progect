package application;

import java.io.IOException;
import java.net.URL;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*
 * Класс-контроллер окна с тренерами. ОТвечает за управление содержимого trainers.fxml
 * Методы аналогичны методам из ContrForMain.java
 */
public class ContrForTrainers {

    @FXML
    private TableView<Coach> tvMain;

    @FXML
    private TableColumn<Coach, String> tcName;

    @FXML
    private TableColumn<Coach, String> tcSurname;

    @FXML
    private TableColumn<Coach, String> tcLastname;

    @FXML
    private TableColumn<Coach, Integer> tcNumber;

    @FXML
    private TableColumn<Coach, Integer> tcAge;

    @FXML
    private TableColumn<Coach, String> tcPower;

    @FXML
    private Button bSportsman;

    @FXML
    private Button bTraining;

    @FXML
    private Button bCompetition;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfPower;

    @FXML
    private Button bAddTrainer;

    @FXML
    private Button bDelTrainer;

    @FXML
    private Button bUpdTrainer;
    
	@FXML
	private void initialize() {
		tcName.setCellValueFactory(new PropertyValueFactory<Coach, String>("name")); // 1 столбик
		tcSurname.setCellValueFactory(new PropertyValueFactory<Coach, String>("surname")); // 2 столбик
		tcLastname.setCellValueFactory(new PropertyValueFactory<Coach, String>("lastname")); // 3 столбик
		tcNumber.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("telephone")); // 4 столбик
		tcAge.setCellValueFactory(new PropertyValueFactory<Coach, Integer>("age"));
		tcPower.setCellValueFactory(new PropertyValueFactory<Coach, String>("rank"));

		tvMain.setItems(FXCollections.observableArrayList(individualContract.show_coach_list()));
		tvMain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				show(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	int lastId = -1;
	private void show(Coach cl) throws IOException {
		if (cl != null) {
			tfName.setText(cl.getName());
			tfSurname.setText(cl.getSurname());
			tfLastname.setText(cl.getLastname());
			tfNumber.setText(Integer.toString(cl.getTelephone()));
			tfAge.setText(Integer.toString(cl.getAge()));
			tfPower.setText(cl.getRank());
			lastId = cl.getIdCoach();
		} else {
			tfName.setText("");
			tfSurname.setText("");
			tfLastname.setText("");
			tfNumber.setText("");
			tfAge.setText("");
			tfPower.setText("");
			lastId = -1;
		}
	}
	@FXML
    void addCoach(ActionEvent event) {
    	String errorMessage = "";
		try {
			Integer.parseInt(tfNumber.getText());
		} catch (Exception e) {
			errorMessage += "Format Number is not a number!\n";
		}
		try {
			Integer.parseInt(tfAge.getText());
		} catch (Exception e) {
			errorMessage += "Format Age is not a number!\n";
		}
		if (errorMessage.length() == 0) {
			individualContract.add_coach(tfName.getText(), tfSurname.getText(), tfLastname.getText(), Integer.parseInt(tfNumber.getText()), Integer.parseInt(tfAge.getText()), tfPower.getText());
			tfName.setText("");
			tfSurname.setText("");
			tfLastname.setText("");
			tfAge.setText("");
			tfPower.setText("");
			tvMain.setItems(FXCollections.observableArrayList(individualContract.show_coach_list()));
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
    }
	@FXML
    void delCoach(ActionEvent event) {
    	individualContract.delete_coach(lastId);
    	tfName.setText("");
		tfSurname.setText("");
		tfLastname.setText("");
		tfAge.setText("");
		tfPower.setText("");
		tvMain.setItems(FXCollections.observableArrayList(individualContract.show_coach_list()));
    }
	@FXML
    void updCoach(ActionEvent event) {
    	String errorMessage = "";
		try {
			Integer.parseInt(tfNumber.getText());
		} catch (Exception e) {
			errorMessage += "Format Number is not a number!\n";
		}
		try {
			Integer.parseInt(tfAge.getText());
		} catch (Exception e) {
			errorMessage += "Format Age is not a number!\n";
		}
		if (errorMessage.length() == 0) {
			individualContract.update_user(lastId, tfName.getText(), tfSurname.getText(), tfLastname.getText(), Integer.parseInt(tfNumber.getText()), Integer.parseInt(tfAge.getText()), tfPower.getText());
			tfName.setText("");
			tfSurname.setText("");
			tfLastname.setText("");
			tfAge.setText("");
			tfPower.setText("");
			tvMain.setItems(FXCollections.observableArrayList(individualContract.show_coach_list()));
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
    }
    @FXML
    void sportsman(ActionEvent event) {
    	try {		    
    		Stage stage = (Stage) bSportsman.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("main.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,1200,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void competition(ActionEvent event) {
    	try {	
    		Stage stage = (Stage) bCompetition.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("competition.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,1200,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Trainers");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    @FXML
    void training(ActionEvent event) {
    	try {		   
    		Stage stage = (Stage) bTraining.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("training.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,1200,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Training");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
