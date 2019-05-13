package GUI;

import javafx.event.ActionEvent;
import java.util.ArrayList;
import Code.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class TableFunctions {
	
	ObservableList<Country> C = FXCollections.observableArrayList();
	
	@FXML
	private TableView<Country> tableDis = new TableView<Country>();
	
	@FXML
	private Button btnDone;
	
	@FXML
	private TableColumn<Country,String> Name = new TableColumn<Country, String>();
	
	@FXML
	private TableColumn<Country,Integer> Year = new TableColumn<Country, Integer>();
	
	@FXML
	private TableColumn<Country,String> Age = new TableColumn<Country, String>();
	
	@FXML
	private TableColumn<Country,String> Sex = new TableColumn<Country, String>();
	
	@FXML
	private TableColumn<Country,Integer> Suicide = new TableColumn<Country, Integer>();
	
	@FXML
	private TableColumn<Country,Integer> Population = new TableColumn<Country, Integer>();
	
	@FXML
	private TableColumn<Country,Double> GDP_per_year = new TableColumn<Country, Double>();
	
	@FXML
	private TableColumn<Country,Double> GDP_per_capita = new TableColumn<Country, Double>();
	
	@FXML
	private TableColumn<Country,String> Generation = new TableColumn<Country, String>();
	
    /**
     * This function is called when the FXML file is initialized and sets up the view on the screen
     */
	@SuppressWarnings("unchecked")
	public void initialize() 
	{
		Name.setCellValueFactory(new PropertyValueFactory<Country, String>("Name"));
		Year.setCellValueFactory(new PropertyValueFactory<Country, Integer>("Year"));
		Age.setCellValueFactory(new PropertyValueFactory<Country, String>("Age"));
		Sex.setCellValueFactory(new PropertyValueFactory<Country, String>("Sex"));
		Suicide.setCellValueFactory(new PropertyValueFactory<Country, Integer>("Suicide"));
		Population.setCellValueFactory(new PropertyValueFactory<Country, Integer>("Population"));
		GDP_per_year.setCellValueFactory(new PropertyValueFactory<Country, Double>("GDP_per_year"));
		GDP_per_capita.setCellValueFactory(new PropertyValueFactory<Country, Double>("GDP_per_capita"));
		Generation.setCellValueFactory(new PropertyValueFactory<Country, String>("Generation"));
		this.tableDis.getColumns().addAll(Name, Year, Age, Sex, Suicide, Population, GDP_per_year, GDP_per_capita,Generation);
	}

	public void sendData(ArrayList<Country> countrys) {
		// TODO Auto-generated method stub
		this.C = FXCollections.<Country>observableArrayList(countrys);
		tableDis.setPlaceholder(new Label("My table is empty message"));
		this.tableDis.setItems(this.C);
	}
	
	@FXML
	public void btnDoneClose(ActionEvent event)
	{
		Stage stage = (Stage) btnDone.getScene().getWindow();
	    stage.close();
	}

}
