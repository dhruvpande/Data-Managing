package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;

import Code.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AssignmentUI {
	
	private final String [] attribute = {"Please Select","Country","Year","Sex","Age","Suicide","Population","GDP per Year","GDP per Capita","Generation"};
	private final String [] action = {"Please Select","Mean","Median","Mode","Max","Min","Sort","Group by","Search","Bar Chart","Line Chart","Pie Chart"};
	ObservableList<DataHolding> attributes = FXCollections.observableArrayList();
	ObservableList<DataHolding> actions = FXCollections.observableArrayList();
	ObservableList<DataHolding> order = FXCollections.observableArrayList();
	static ArrayList<Country> Countrys;
	double ans;
	
    @FXML
    private ChoiceBox<DataHolding> lstAttribute;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnSubmit;

    @FXML
    private ChoiceBox<DataHolding> lstAction;
    
    @FXML
    private Button btnLoad;
    
    @FXML
    private Button btnView;
    
    @FXML
    private Button btnClose;
    
    /**
     * This function is called when the FXML file is initialized and sets up the view on the screen
     */
    @FXML
    private void initialize()
    {
    	order.add(new DataHolding(1,"Descending"));
    	order.add(new DataHolding(2,"Ascending"));
    	int i=0;
    	for(String s:attribute)
    	{
    		attributes.add(new DataHolding(i,s));
    		i++;
    	}
    	
    	i=0;
    	for(String s:action)
    	{
    		actions.add(new DataHolding(i,s));
    		i++;
    	}
    	lstAttribute.setValue(attributes.get(0));
    	lstAttribute.setItems(attributes);
    	lstAction.setValue(actions.get(0));
    	lstAction.setItems(FXCollections.observableArrayList(actions));
    }
    
    
    /**
     * This method is called when the button Submit is clicked and executes the action that the user selected
     * based on the attributes they selected
     * @param event is the ActionEvent of button click
     */
    @FXML
    public void btnSubmit(ActionEvent event) 
    {
    	if (Countrys==null)
    	{
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setTitle("Error");
    		alert.setHeaderText(null);
    		alert.setContentText("Please Load the data first");
    		alert.showAndWait();
    	}
    	else
    	{
    		if((lstAttribute.getValue().getID()==0)||(lstAction.getValue().getID()==0))
    		{
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Error");
        		alert.setHeaderText(null);
        		alert.setContentText("Please Attribute and/or Action");
        		alert.showAndWait();
    		}
    		else if(lstAction.getValue().getID()==8)
    		{
    			TextInputDialog dialog = new TextInputDialog();
    			dialog.setTitle("Search");
    			dialog.setHeaderText(null);
    			dialog.setContentText("Please enter the item you wish to search for:");

    			// Traditional way to get the response value.
    			Optional<String> result = dialog.showAndWait();
    			if (result.isPresent())
    			{
    				Countrys = WorkerClass.actionSelection(lstAttribute.getValue().getID(), Countrys, lstAction.getValue().getID(),result.get());
    			}
    			try {
					btnViewing(new ActionEvent());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    		}
    		else if((lstAction.getValue().getID()==6)||(lstAction.getValue().getID()==7)||(lstAction.getValue().getID()>=9))
    		{
    			ChoiceDialog<DataHolding> dialog = new ChoiceDialog<>(attributes.get(0),attributes);
    			dialog.setTitle("Next Parameter");
    			dialog.setHeaderText(null);
    			dialog.setContentText("Please choose second parameter to sort by or press ok to continue:");

    			Optional<DataHolding> result = dialog.showAndWait();
    			Optional<DataHolding> result1;
    			if (result.isPresent())
    			{
    				ChoiceDialog<DataHolding> dialog2 = new ChoiceDialog<>(order.get(0),order);
        			dialog2.setTitle("Sorting Style");
        			dialog2.setHeaderText(null);
        			dialog2.setContentText("Please choose an order to sort by or press ok if you want to continue:");
        			result1 = dialog2.showAndWait();
        			if (result1.isPresent()&&(lstAction.getValue().getID()<9))
        			{
        				Countrys = WorkerClass.actionSelection(lstAttribute.getValue().getID(), Countrys, lstAction.getValue().getID(), result.get().getID(), result1.get().getID());
        				try {
							btnViewing(new ActionEvent());
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
        			
        		}
    			if((lstAction.getValue().getID()==9)||(lstAction.getValue().getID()==11))
    			{
    				ArrayList<ChartData> cd = WorkerClass.barChartDataCreating(Countrys, lstAttribute.getValue().getID(), result.get().getID());
    				try {
						sendChartData(cd,lstAction.getValue().getID()-8,attribute[lstAttribute.getValue().getID()],attribute[result.get().getID()]);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    			}
    			else if(lstAction.getValue().getID()==10)
    			{
    				ChoiceDialog<DataHolding> dialog2 = new ChoiceDialog<>(attributes.get(0),attributes);
        			dialog2.setTitle("Sorting Style");
        			dialog2.setHeaderText(null);
        			dialog2.setContentText("Please choose the third paramenter to graph:");
        			result1 = dialog2.showAndWait();
        			if (result1.isPresent())
        			{
        				ArrayList<ChartData> cd = WorkerClass.lineChartDataCreating(Countrys,lstAttribute.getValue().getID(), result.get().getID(), result1.get().getID());
        				try {
							sendChartData(cd,lstAction.getValue().getID()-8,attribute[result.get().getID()],attribute[result1.get().getID()]);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
        			}
    			}
    		}
    		else
    		{
    			ans = WorkerClass.actionSelection(lstAttribute.getValue().getID(), Countrys, lstAction.getValue().getID());
    			Alert alert = new Alert(AlertType.INFORMATION);
        		alert.setTitle("Result");
        		alert.setHeaderText(null);
        		alert.setContentText("The "+lstAction.getValue().getTxt()+" of "+lstAttribute.getValue().getTxt()+" is : "+ans);
        		alert.showAndWait();
    		}
    	}
    }
    

    /**
     * This method is called when the Clear button is clicked and resets all the values on the screen
     * @param event is the ActionEvent of button click
     */
    @FXML
    void BtnClear(ActionEvent event) 
    {
    	lstAttribute.setValue(attributes.get(0));
    	lstAction.setValue(actions.get(0));
    	Countrys = null;
    	Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Cleared");
		alert.setHeaderText(null);
		alert.setContentText("Success! Everything has been reset");
		alert.showAndWait();
    }
    
    /**
     * This function is called when the Load button is clicked and loads the data chosen by the user in FileChooser
     * into the ArrayList of type Country 
     * @param event is the ActionEvent of button click
     */
    public void btnLoading(ActionEvent event) 
    {
    	Countrys = new ArrayList<Country>();
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	Stage stage = (Stage) btnLoad.getScene().getWindow();
    	fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Comma Seperated Values", "*.csv*")
            );
    	fileChooser.setInitialDirectory(new File("data"));
    	
    	try 
    	{
    		File selectedFile = fileChooser.showOpenDialog(stage);
    		Countrys = Reader.reading(selectedFile);
    		Countrys.get(0); 
	    	Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(null);
			alert.setContentText("Data has been loaded successfully");
			alert.showAndWait();
    	}catch (Exception e)
    	{
    		System.out.println(e.getMessage());
    		Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Success");
			alert.setHeaderText(null);
			alert.setContentText("Data has not been loaded successfully");
			alert.showAndWait();
    	}
    }
    
    /**
     * This method called when the View Button is clicked. It sends the current class attribute Countrys, 
     * an ArrayList of type Country to the TableFunctions Class to create
     * and display a table of the given data
     * @param event is the ActionEvent of button click
     * @throws IOException as the FXML file location is known 
     */
    @SuppressWarnings("deprecation")
	public void btnViewing(ActionEvent event) throws IOException
    {
    	URL fxmlUrlD = new File("src/GUI/TableDis.fxml").toURL();
        FXMLLoader loader = new FXMLLoader(fxmlUrlD);
    	Pane displayPane = loader.load();
        TableFunctions tbControl = loader.<TableFunctions>getController();
        tbControl.sendData(Countrys);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Table");
        primaryStage.setScene(new Scene(displayPane));
        primaryStage.show();
    }
    
    /**
     * This function is used when the Close button is pressed
     * @param event is the button click action
     */
    public void btnClosing(ActionEvent event)
    {
    	Stage stage = (Stage) btnClose.getScene().getWindow();
	    stage.close();
    }
    
    /**
     * This method send the chart data to the ChartsController class to be used in the relevant chart
     * @param cd is an ArrayList of type ChartData that contains the chart data to be sent 
     * @param chartType is the type of chart to be created
     * @param ylabel is the label of the Y-axis
     * @param xlabel is the label of the X-axis
     * @throws IOException as the location of the FXML file is known
     */
    @SuppressWarnings("deprecation")
	public void sendChartData(ArrayList<ChartData> cd, int chartType, String ylabel, String xlabel) throws IOException
    {
    	URL fxmlUrlD = new File("src/GUI/Charts.fxml").toURL();
    	FXMLLoader loader = new FXMLLoader(fxmlUrlD);
        Pane displayPane = loader.load();
        ChartsController tbControl = loader.getController();
        Stage primaryStage = new Stage();
        tbControl.sendData(cd,chartType,ylabel,xlabel,displayPane);
        primaryStage.setTitle("Table");
        primaryStage.setScene(new Scene(displayPane));
        primaryStage.show();
    }

}
