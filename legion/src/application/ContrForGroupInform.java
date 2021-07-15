package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
/*
 * Класс-контроллер для дополнительного вида информации о группе спортсмена
 */
public class ContrForGroupInform {

    @FXML
    private Button bExit;

    @FXML
    private TextField tfNum;

    @FXML
    private TextField tfTrainer;

    @FXML
    private TextArea tfDesc;

    Group gr = null;
    
    @FXML
	private void initialize() {
    	gr = company.show_group(ContrForMain.groupid);
    	tfNum.setText(Integer.toString(gr.getNumber()));
    	tfTrainer.setText(Integer.toString(gr.getCoach_idCoach()));
    	tfDesc.setText(gr.getDescription());
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