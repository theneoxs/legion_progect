package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

/*
 * Класс-контроллер окна добавления соревнования
 */
public class ContrForCompAdd {

	@FXML
	private Button bEditComp;

	@FXML
	private TextField tfName;

	@FXML
	private TextField tfData;

	@FXML
	private ComboBox<String> cbReward;
	@FXML
	private ObservableList<String> cblReward;

	@FXML
	private TextField tfComp;

	@FXML
	private TextField tfWinner;

	@FXML
	private Button bWinner;

	@FXML
	private void initialize() {
		cblReward = FXCollections.observableArrayList(Reward.show_reward());
		cbReward.setItems(cblReward);
		cbReward.setValue(cblReward.get(0));
	}

	// добавить соревнование
	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@FXML
	void enterComp(ActionEvent event) {
		String errorMessage = "";
		try {
			df.parse(tfData.getText()).getTime();
		} catch (Exception e) {
			errorMessage += "Date, Not this format! (Need YYYY-MM-DD)\n";
		}
		if (errorMessage.length() == 0) {
			Competition.add_comp(tfName.getText(), tfData.getText(), cbReward.getValue());
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

//добавить победителя
	@FXML
	void enterWinner(ActionEvent event) {
		String errorMessage = "";
		try {
			Integer.parseInt(tfComp.getText());
		} catch (Exception e) {
			errorMessage += "Format Competition is not a number!\n";
		}
		try {
			Integer.parseInt(tfWinner.getText());
		} catch (Exception e) {
			errorMessage += "Format Winner is not a number!\n";
		}
		if (errorMessage.length() == 0) {
			Competition_has_Sportsman.add_user(Integer.parseInt(tfComp.getText()),
					Integer.parseInt(tfWinner.getText()));
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);
			alert.showAndWait();
		}
	}

}
