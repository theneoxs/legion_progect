package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
 * Класс-контроллер основного окна со спортсменами. Происзодит управление главным окном
 */
public class ContrForMain {
	//инициализация таблицы
	@FXML
	private TableView<Sportsman> tvMain;

	@FXML
	private TableColumn<Sportsman, String> tcSurname;

	@FXML
	private TableColumn<Sportsman, String> tcName;

	@FXML
	private TableColumn<Sportsman, String> tcLastName;

	@FXML
	private TableColumn<Sportsman, Integer> tcBirthday;

	@FXML
	private TableColumn<Sportsman, Integer> tcWeight;

	@FXML
	private TableColumn<Sportsman, Integer> tcNumber;

	@FXML
	private Button bTraining;

	@FXML
	private Button bTrainers;

	@FXML
	private Button bCompetition;

	@FXML
	private TextField tfSurname;

	@FXML
	private TextField tfNum;

	@FXML
	private TextField tfWeight;

	@FXML
	private TextField tfBirthday;

	@FXML
	private TextField tfLastName;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfDateBuy;

	@FXML
	private TextField tfNumTrain;

	@FXML
	private TextField tfLastDay;

	@FXML
	private Button bInfoGr;

	@FXML
	private Button bLong;

	@FXML
	private Button bPick;

	@FXML
	private Button bComp;

	@FXML
	private Button bAddSp;

	@FXML
	private Button bDelSp;

	@FXML
	private Button bUpdSp;

	@FXML
	private Button bAddAb;

	@FXML
	private Button bDelAb;

	@FXML
	private Button bUpdAb;
	
	@FXML
    private ComboBox<String> cbIdGroup = new ComboBox<String>();
	@FXML 
	private ObservableList<String> cblIdGroup;
	
	//последний выбранный спортсмен (для следующих окон)
	public static int lastId = -1;
	private Abonement abonement;
	//последняя выбранная группа (для следующих окон)
	public static int groupid = -1;
	//метод инициализации (запускается при вызове методов создания окна)
	@FXML
	private void initialize() {
		cblIdGroup = FXCollections.observableArrayList(company.show_list_group());
		cbIdGroup.setItems(cblIdGroup);
		cbIdGroup.setValue(cblIdGroup.get(0));
		//заполнение таблицы данными
		tcSurname.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("surname")); // 1 столбик
		tcName.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("name")); // 2 столбик
		tcLastName.setCellValueFactory(new PropertyValueFactory<Sportsman, String>("lastname")); // 3 столбик
		tcBirthday.setCellValueFactory(new PropertyValueFactory<Sportsman, Integer>("age")); // 4 столбик
		tcWeight.setCellValueFactory(new PropertyValueFactory<Sportsman, Integer>("weight"));
		tcNumber.setCellValueFactory(new PropertyValueFactory<Sportsman, Integer>("telephone"));
		tvMain.setItems(FXCollections.observableArrayList(companyWorker.show_sportabon_list()));
		//бинд события на клик по строчке
		tvMain.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			try {
				
				show(newValue);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	int lastidabon = -1;
	//метод демонстрации содержимого строки построчно
	private void show(Sportsman cl) throws IOException {
		if (cl != null) {
			tfSurname.setText(cl.getSurname());
			tfName.setText(cl.getName());
			tfLastName.setText(cl.getLastname());
			tfBirthday.setText(Integer.toString(cl.getAge()));
			tfWeight.setText(Integer.toString(cl.getWeight()));
			tfNum.setText(Integer.toString(cl.getTelephone()));
			lastId = cl.getIdSportsman();
			abonement = controller.show_abon_list(lastId);
			if (abonement == null) {
				tfDateBuy.setText("");
				tfLastDay.setText("");
				tfNumTrain.setText("");
				cbIdGroup.setValue("");
				lastidabon = -1;
			}
			tfDateBuy.setText(abonement.getDate());
			tfLastDay.setText(abonement.getLastDate());
			tfNumTrain.setText(Integer.toString(abonement.getTrainCount()));
			cbIdGroup.setValue(Integer.toString(abonement.getGroup_idGroup())+" ");
			lastidabon = abonement.getIdAbonement();
			groupid = abonement.getGroup_idGroup();
		} else {
			tfSurname.setText("");
			tfName.setText("");
			tfLastName.setText("");
			tfBirthday.setText("");
			tfWeight.setText("");
			tfNum.setText("");
			lastId = -1;
			tfDateBuy.setText("");
			tfLastDay.setText("");
			tfNumTrain.setText("");
			cbIdGroup.setValue("");
			lastidabon = -1;
			groupid = -1;
		}
	}

	//кнопка добавить нового 
	@FXML
	void addSportsman(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNum.getText());
		} catch (Exception e) {
			errorMessage += "Format Telephone is not a number!\n";
		}
		try {
			Integer.parseInt(tfBirthday.getText());
		} catch (Exception e) {
			errorMessage += "Format Age is not a number!\n";
		}
		try {
			Integer.parseInt(tfWeight.getText());
		} catch (Exception e) {
			errorMessage += "Format Weight is not a number!\n";
		}
		if (errorMessage.length() == 0) {
			companyWorker.add_sportsman(tfName.getText(), tfSurname.getText(), tfLastName.getText(),
					Integer.parseInt(tfNum.getText()), Integer.parseInt(tfBirthday.getText()),
					Integer.parseInt(tfWeight.getText()));
			tvMain.setItems(FXCollections.observableArrayList(companyWorker.show_sportabon_list()));
			tfSurname.setText("");
			tfName.setText("");
			tfLastName.setText("");
			tfBirthday.setText("");
			tfWeight.setText("");
			tfNum.setText("");
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	//кнопка удалить спортсмена
	@FXML
	void delSportsman(ActionEvent event) {
		if (lastId != -1) {
			companyWorker.delete_sportsman(lastId);
			tvMain.setItems(FXCollections.observableArrayList(companyWorker.show_sportabon_list()));
			tfSurname.setText("");
			tfName.setText("");
			tfLastName.setText("");
			tfBirthday.setText("");
			tfWeight.setText("");
			tfNum.setText("");
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Choose");
			alert.setHeaderText("Please choose a sportsman");
			alert.showAndWait();
		}
	}
	//кнопка обновить данные у спортсмена
	@FXML
	void updSportsman(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNum.getText());
		} catch (Exception e) {
			errorMessage += "Format Telephone is not a number!\n";
		}
		try {
			Integer.parseInt(tfBirthday.getText());
		} catch (Exception e) {
			errorMessage += "Format Age is not a number!\n";
		}
		try {
			Integer.parseInt(tfWeight.getText());
		} catch (Exception e) {
			errorMessage += "Format Weight is not a number!\n";
		}
		if (lastId == -1) {
			errorMessage += "You don't choose sportsman!\n";
		}
		if (errorMessage.length() == 0) {
			companyWorker.update_sportsman(lastId, tfName.getText(), tfSurname.getText(), tfLastName.getText(),
					Integer.parseInt(tfNum.getText()), Integer.parseInt(tfBirthday.getText()),
					Integer.parseInt(tfWeight.getText()));
			tvMain.setItems(FXCollections.observableArrayList(companyWorker.show_sportabon_list()));
			tfSurname.setText("");
			tfName.setText("");
			tfLastName.setText("");
			tfBirthday.setText("");
			tfWeight.setText("");
			tfNum.setText("");
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	//Добавить абонемент
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	@FXML
	void addAbon(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNumTrain.getText());
		} catch (Exception e) {
			errorMessage += "Format Telephone is not a number!\n";
		}
		try {
			df.parse(tfDateBuy.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Buy date, Not this format! (Need YYYY-MM-DD)\n";
		}
		try {
			df.parse(tfLastDay.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Last date, Not this format! (Need YYYY-MM-DD)\n";
		}
		if (errorMessage.length() == 0) {
			try {
				if (df.parse(tfLastDay.getText()).getTime() < df.parse(tfDateBuy.getText()).getTime()) {
					errorMessage += "Last date less than buy date!\n";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (errorMessage.length() == 0) {
			controller.add_abon(tfDateBuy.getText(), tfLastDay.getText(), Integer.parseInt(tfNumTrain.getText()), Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" "))), lastId);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	//удалить абонемент
	@FXML
	void delAbon(ActionEvent event) {
		if (lastidabon != -1) {
			controller.delete_abon(lastidabon);
			tfDateBuy.setText("");
			tfLastDay.setText("");
			tfNumTrain.setText("");
			cbIdGroup.setValue("");
			groupid = -1;
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Choose");
			alert.setHeaderText("Please choose a sportsman");
			alert.showAndWait();
		}
	}
	//обновить абонемент
	@FXML
	void updAbon(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfNumTrain.getText());
		} catch (Exception e) {
			errorMessage += "Format Telephone is not a number!\n";
		}
		try {
			df.parse(tfDateBuy.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Buy date, Not this format! (Need YYYY-MM-DD)\n";
		}
		try {
			df.parse(tfLastDay.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Last date, Not this format! (Need YYYY-MM-DD)\n";
		}
		if (errorMessage.length() == 0) {
			try {
				if (df.parse(tfLastDay.getText()).getTime() < df.parse(tfDateBuy.getText()).getTime()) {
					errorMessage += "Last date less than buy date!\n";
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if (lastidabon == -1 || lastId == -1) {
			errorMessage += "You don't choose sportsman!\n";
		}
		if (errorMessage.length() == 0) {
			controller.update_abon(lastidabon, tfDateBuy.getText(), tfLastDay.getText(), Integer.parseInt(tfNumTrain.getText()), Integer.parseInt(cbIdGroup.getValue().substring(0, cbIdGroup.getValue().indexOf(" "))), lastId);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}
	//кнопка отметить посещение
	@FXML
	void pick(ActionEvent event) {
		if (lastidabon != -1) {
			controller.pick(lastidabon);
			abonement = controller.show_abon_list(lastId);
			tfDateBuy.setText(abonement.getDate());
			tfLastDay.setText(abonement.getLastDate());
			tfNumTrain.setText(Integer.toString(abonement.getTrainCount()));
			cbIdGroup.setValue(Integer.toString(abonement.getGroup_idGroup()));
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("You don't choose sportsman");
			alert.showAndWait();
		}
	}
	//кнопка просмотра соревнований
	@FXML
	void competiton(ActionEvent event) {
		try {
			Stage stage = (Stage) bCompetition.getScene().getWindow();
			stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("competition.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Competition");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//кнопка просмотра тренеров
	@FXML
	void trainers(ActionEvent event) {
		try {
			Stage stage = (Stage) bTrainers.getScene().getWindow();
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
	//кнопка просмотра тренировок
	@FXML
	void training(ActionEvent event) {
		try {
			Stage stage = (Stage) bTraining.getScene().getWindow();
			stage.close();
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("training.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 1200, 600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Training");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//кнопка информации о группе спортсмена
	@FXML
	void groupInform(ActionEvent event) {
		if (lastId != -1) {
		try {
			Stage primaryStage = new Stage();
			URL url = getClass().getResource("groupInform.fxml");
			System.out.println(url);
			AnchorPane root = (AnchorPane) FXMLLoader.load(url);
			Scene scene = new Scene(root, 512, 512);
			primaryStage.setScene(scene);
			primaryStage.setTitle("Group");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("You don't choose sportsman");
			alert.showAndWait();
		}
	}
	//кнопка просмотра соревнований спортсмена
	@FXML
	void compInform(ActionEvent event) {
		if (lastId != -1) {
			try {
				Stage primaryStage = new Stage();
				URL url = getClass().getResource("compInform.fxml");
				System.out.println(url);
				AnchorPane root = (AnchorPane) FXMLLoader.load(url);
				Scene scene = new Scene(root, 512, 512);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Competition");
				primaryStage.show();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText("You don't choose sportsman");
			alert.showAndWait();
		}
	}
}
