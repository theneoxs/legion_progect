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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/*
 * Класс-контроллер окна с тренировками. ОТвечает за управление содержимого training.fxml
 * Методы аналогичны методам из ContrForMain.java
 */
public class ContrForTraining {

	@FXML
	private TableView<Training> tvMain;

	@FXML
	private TableColumn<Training, String> tcName;

	@FXML
	private TableColumn<Training, String> tcDesc;

	@FXML
	private TableColumn<Training, String> tcDate;

	@FXML
	private TableColumn<Training, String> tcTime;

	@FXML
	private TableColumn<Training, Integer> tcNumGroup;

	@FXML
	private TableColumn<Training, Integer> tcId;

	@FXML
	private Button bSportsman;

	@FXML
	private Button bTrainer;

	@FXML
	private Button bCompetition;

	@FXML
	private TextField tfName;

	@FXML
	private TextArea tfDesc;

	@FXML
	private TextField tfDate;

	@FXML
	private TextField tfTime;

	@FXML
	private TextField tfNumGroup;

	@FXML
	private TextField tfTrainer;

	@FXML
	private ComboBox<String> cbId = new ComboBox<String>();
	@FXML
	private ObservableList<String> cblId;

	@FXML
	private ComboBox<String> cbIdGroup = new ComboBox<String>();
	@FXML
	private ObservableList<String> cblIdGroup;

	@FXML
	private Button bInform;

	@FXML
	private TextArea tfDescGroup;

	@FXML
	private Button bAddTraining;

	@FXML
	private Button bDelTraining;

	@FXML
	private Button bUpdTraining;

	@FXML
	private Button bAddGroup;

	@FXML
	private Button bDelGroup;

	@FXML
	private Button bUpdGroup;

	@FXML
	private Button bAddTrain;

	private Group group = null;
	private int lastId = -1;

	@FXML
	private void initialize() {
		cblId = FXCollections.observableArrayList(company.show_list_train());
		cbId.setItems(cblId);
		//cbId.setValue(cblId.get(0));
		cblIdGroup = FXCollections.observableArrayList(company.show_list_group());
		cbIdGroup.setItems(cblIdGroup);
		//cbIdGroup.setValue(cblIdGroup.get(0));
		tcName.setCellValueFactory(new PropertyValueFactory<Training, String>("name")); // 1 столбик
		tcDesc.setCellValueFactory(new PropertyValueFactory<Training, String>("descriptior")); // 2 столбик
		tcDate.setCellValueFactory(new PropertyValueFactory<Training, String>("date")); // 3 столбик
		tcTime.setCellValueFactory(new PropertyValueFactory<Training, String>("time")); // 4 столбик
		tcNumGroup.setCellValueFactory(new PropertyValueFactory<Training, Integer>("Group_idGroup"));
		tcId.setCellValueFactory(new PropertyValueFactory<Training, Integer>("idTrain"));
		
		tvMain.setItems(FXCollections.observableArrayList(Group_has_Train.show_train_list()));
		tvMain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				show(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}

	public static int idTrain = -1;
	private int lastIdGroup = -1;

	private void show(Training cl) throws IOException {
		if (cl != null) {
			tfName.setText(cl.getName());
			tfDesc.setText(cl.getDescriptior());
			cbId.setValue(Integer.toString(cl.getIdTrain())+" ");
			tfDate.setText(cl.getDate());
			tfTime.setText(cl.getTime());
			cbIdGroup.setValue(Integer.toString(cl.getGroup_idGroup())+" ");
			lastId = cl.getGroup_idGroup();

			group = company.show_group(lastId);
			tfNumGroup.setText(Integer.toString(group.getIdGroup()));
			tfDescGroup.setText(group.getDescription());
			tfTrainer.setText(Integer.toString(group.getCoach_idCoach()));
			idTrain = group.getCoach_idCoach();
			lastIdGroup = group.getIdGroup();
		} else {
			tfName.setText("");
			tfDesc.setText("");
			cbId.setPromptText("");
			tfDate.setText("");
			tfTime.setText("");
			cbIdGroup.setValue("");
			lastId = -1;
			idTrain = -1;
			lastIdGroup = -1;
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
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Trainers");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");
	@FXML
	void addTrain(ActionEvent event) {
		Train.add_train(tfName.getText(), tfDesc.getText());
		tfName.setText("");
		tfDesc.setText("");
		cblId = FXCollections.observableArrayList(company.show_list_train());
		cbId.setItems(cblId);
	}

	@FXML
	void addGroupHasTrain(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" ")));
		} catch (Exception e) {
			errorMessage += "Format Group is not a number!\n";
		}
		try {
			df.parse(tfDate.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Date, Not this format! (Need YYYY-MM-DD)\n";
		}
		try {
			time.parse(tfTime.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Time, Not this format! (Need HH:mm:ss)\n";
		}
		if (errorMessage.length() == 0) {
			Group_has_Train.add_group_has_train(
					Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" "))),
					Integer.parseInt(cbId.getValue().substring(0, cbId.getValue().indexOf(" "))), tfDate.getText(),
					tfTime.getText());
			cbIdGroup.setValue("");
			tfDate.setText("");
			tfTime.setText("");
			tvMain.setItems(FXCollections.observableArrayList(Group_has_Train.show_train_list()));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	void delGroupHasTrain(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" ")));
		} catch (Exception e) {
			errorMessage += "Format Group is not a number!\n";
		}
		try {
			Integer.parseInt(cbId.getValue().substring(0, cbId.getValue().indexOf(" ")));
		} catch (Exception e) {
			errorMessage += "Format ID is not a number!\n";
		}
		try {
			df.parse(tfDate.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Date, Not this format! (Need YYYY-MM-DD)\n";
		}
		try {
			time.parse(tfTime.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Time, Not this format! (Need HH:mm:ss)\n";
		}
		if (errorMessage.length() == 0) {
			Group_has_Train.delete_group_has_train(
					Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" "))),
					Integer.parseInt(cbId.getValue().substring(0, cbId.getValue().indexOf(" "))), tfDate.getText(), tfTime.getText());
			cbIdGroup.setValue("");
			tfDate.setText("");
			tfTime.setText("");
			tvMain.setItems(FXCollections.observableArrayList(Group_has_Train.show_train_list()));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	void addGroup(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNumGroup.getText());
		} catch (Exception e) {
			errorMessage += "Format Number Group is not a number!\n";
		}
		try {
			Integer.parseInt(tfTrainer.getText());
		} catch (Exception e) {
			errorMessage += "Format Trainer is not a number!\n";
		}
		if (errorMessage.length() == 0) {
			company.add_group(Integer.parseInt(tfNumGroup.getText()), tfDescGroup.getText(),
					Integer.parseInt(tfTrainer.getText()));
			tfNumGroup.setText("");
			tfDescGroup.setText("");
			tfTrainer.setText("");
			cblIdGroup = FXCollections.observableArrayList(company.show_list_group());
			cbIdGroup.setItems(cblIdGroup);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

	@FXML
	void delGroup(ActionEvent event) {
		if (lastIdGroup != -1) {
			company.delete_group(lastIdGroup);
			tfNumGroup.setText("");
			tfDescGroup.setText("");
			tfTrainer.setText("");
			cblIdGroup = FXCollections.observableArrayList(company.show_list_group());
			cbIdGroup.setItems(cblIdGroup);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("Choose group");
			alert.showAndWait();
		}
	}

	@FXML
	void updGroup(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNumGroup.getText());
		} catch (Exception e) {
			errorMessage += "Format Number Group is not a number!\n";
		}
		try {
			Integer.parseInt(tfTrainer.getText());
		} catch (Exception e) {
			errorMessage += "Format Trainer is not a number!\n";
		}
		if (lastIdGroup == -1) {
			errorMessage += "Choose group!\n";
		}
		if (errorMessage.length() == 0) {
			company.update_group(lastIdGroup, Integer.parseInt(tfNumGroup.getText()), tfDescGroup.getText(),
					Integer.parseInt(tfTrainer.getText()));
			tfNumGroup.setText("");
			tfDescGroup.setText("");
			tfTrainer.setText("");
			cblIdGroup = FXCollections.observableArrayList(company.show_list_group());
			cbIdGroup.setItems(cblIdGroup);
		} else {
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
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void trainers(ActionEvent event) {
		try {
			Stage stage = (Stage) bTrainer.getScene().getWindow();
			stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("trainers.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Trainers");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	void inform(ActionEvent event) {
		if (idTrain != -1) {
			try {
				Stage primaryStage = new Stage();
				URL url = getClass().getResource("trainingInform.fxml");
				System.out.println(url);
				AnchorPane root = (AnchorPane) FXMLLoader.load(url);
				Scene scene = new Scene(root, 512, 512);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Trainer");
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("Choose training");
			alert.showAndWait();
		}
	}
}
