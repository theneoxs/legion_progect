package application;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/*
 * Класс-контроллер для дополнительного вида информации о соревнованиях спортсмена
 */
public class ContrForCompInform {

    @FXML
    private Button bExit;

    @FXML
    private TableView<Comp> tvMain;

    @FXML
    private TableColumn<Comp, String> tcName;

    @FXML
    private TableColumn<Comp, String> tcDate;

    @FXML
    private TableColumn<Comp, String> tcReward;

    @FXML
	private void initialize() {
    	tcName.setCellValueFactory(new PropertyValueFactory<Comp, String>("name")); // 1 столбик
    	tcDate.setCellValueFactory(new PropertyValueFactory<Comp, String>("date")); // 2 столбик
    	tcReward.setCellValueFactory(new PropertyValueFactory<Comp, String>("Reward_name")); // 3 столбик
    	tvMain.setItems(FXCollections.observableArrayList(Competition.show_comp_list_concr(ContrForMain.lastId)));
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