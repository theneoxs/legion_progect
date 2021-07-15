package application;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/*
 * Класс-контроллер окна с соревнованиями. ОТвечает за управление содержимого competition.fxml
 * Методы аналогичны методам из ContrForMain.java
 */
public class ContrForCompetition {

    @FXML
    private TableView<Comp> tvMain;

    @FXML
    private TableColumn<Comp, String> tcName;

    @FXML
    private TableColumn<Comp, String> tcDate;

    @FXML
    private TableColumn<Comp, String> tcReward;

    @FXML
    private Button bSportsman;

    @FXML
    private Button bTraining;

    @FXML
    private Button bTrainers;

    @FXML
    private TextField tfNameComp;

    @FXML
    private TextField tfDate;

    @FXML
    private ComboBox<String> cbReward;
    @FXML 
	private ObservableList<String> cblReward;

    @FXML
    private TableView<Sportsman> tvWinner;

    @FXML
    private TableColumn<Sportsman, String> tcSurname;

    @FXML
    private TableColumn<Sportsman, String> tcNameW;

    @FXML
    private TableColumn<Sportsman, String> tcLastName;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfLastName;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfWeight;

    @FXML
    private Button bAddComp;

    @FXML
    private Button bDelComp;

    @FXML
    private Button bUpdComp;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    @FXML
	private void initialize() {
    	cblReward = FXCollections.observableArrayList(Reward.show_reward());
    	cbReward.setItems(cblReward);
		tcName.setCellValueFactory(new PropertyValueFactory<Comp, String>("name")); // 1 столбик
		tcDate.setCellValueFactory(new PropertyValueFactory<Comp, String>("date")); // 2 столбик
		tcReward.setCellValueFactory(new PropertyValueFactory<Comp, String>("Reward_name")); // 3 столбик

		tvMain.setItems(FXCollections.observableArrayList(Competition.show_comp_list()));
		tvMain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				show(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
    int lastId = -1;
	private void show(Comp cl) throws IOException {
		if (cl != null) {
			tfNameComp.setText(cl.getName());
			tfDate.setText(cl.getDate());
			cbReward.setValue(cl.getReward_name()+" ");
			
			lastId = cl.getIdCompetition();
			
			tcSurname.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("name")); // 1 столбик
			tcNameW.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("surname")); // 2 столбик
			tcLastName.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("lastname")); // 3 столбик

			tvWinner.setItems(FXCollections.observableArrayList(companyWorker.show_sportwinner_list(cl.getIdCompetition())));
			tvWinner.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
				try {
					showWinner(newValue);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		} else {
			tfNameComp.setText("");
			tfDate.setText("");
			cbReward.setValue("");
			tvWinner.setItems(null);
			lastId = -1;
		}
	}
	private void showWinner(Sportsman cl) throws IOException {
		if (cl != null) {
			tfName.setText(cl.getName());
			tfSurname.setText(cl.getSurname());
			tfLastName.setText(cl.getLastname());
			tfAge.setText(Integer.toString(cl.getAge()));
			tfWeight.setText(Integer.toString(cl.getWeight()));
			
		} else {
			tfName.setText("");
			tfSurname.setText("");
			tfLastName.setText("");
			tfAge.setText("");
			tfWeight.setText("");
		}
	}
	@FXML
    void deleteComp(ActionEvent event) {
		if (lastId != -1) {
			Competition.delete_comp(lastId);
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("Choose competition");
			alert.showAndWait();
		}
    }
	@FXML
    void updateComp(ActionEvent event) {
		String errorMessage = "";
		try {
			df.parse(tfDate.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Date, Not this format! (Need YYYY-MM-DD)\n";
		} 
		if (lastId == -1) {
			errorMessage += "Choose competiton";
		}
		if (errorMessage.length() == 0) {
		Competition.update_comp(lastId, tfNameComp.getText(), tfDate.getText(), cbReward.getValue());
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
    void trainers(ActionEvent event) {
    	try {	
    		Stage stage = (Stage) bTrainers.getScene().getWindow();
		    stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("trainers.fxml");
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
    @FXML
    void addComp(ActionEvent event) {
    	try {		   
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("compAdd.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane)FXMLLoader.load(url);
			Scene scene = new Scene(root,512,512);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Add Competition");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
    }
}
