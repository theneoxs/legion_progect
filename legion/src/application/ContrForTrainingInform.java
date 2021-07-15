package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/*
 * Класс-контроллер для дополнительного вида информации о тренировках
 */
public class ContrForTrainingInform {

    @FXML
    private Button bExit;

    @FXML
    private TextField tfName;

    @FXML
    private TextField tfSurname;

    @FXML
    private TextField tfLastname;

    @FXML
    private TextField tfNumber;

    @FXML
    private TextField tfAge;

    @FXML
    private TextField tfRank;
    Coach coach = null;
    @FXML
	private void initialize() {
    	coach = individualContract.show_coach_concr(ContrForTraining.idTrain);
    	tfName.setText(coach.getName());
    	tfSurname.setText(coach.getSurname());
    	tfLastname.setText(coach.getLastname());
    	tfNumber.setText(Integer.toString(coach.getTelephone()));
    	tfAge.setText(Integer.toString(coach.getAge()));
    	tfRank.setText(coach.getRank());
    }
    @FXML
    void exit(ActionEvent event) {
    	try {
   			Stage stage = (Stage) bExit.getScene().getWindow();
   		    stage.close();
   		} catch(Exception e) {
   			e.printStackTrace();
   		}
    }

}