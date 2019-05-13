package GUI;
import java.awt.ScrollPane;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.*;
import javafx.scene.layout.Pane;

/**
 * This class controls the chart data to set up and put the data in the various charts
 * @author dhruv
 *
 */
public class ChartsController {

	@FXML
	private Pane paneLine;

	@FXML
	CategoryAxis xAxis = new CategoryAxis();
	
	@FXML
	NumberAxis yAxis = new NumberAxis();
	
	@FXML
	NumberAxis xLAxis = new NumberAxis();
	
	@FXML
	private BarChart<String, Number> graphBar = new BarChart<String,Number>(xAxis,yAxis);

	@FXML
	private LineChart<Number, Number> graphLine = new LineChart<Number,Number>(xLAxis,yAxis);

	@FXML
	private Pane paneBar;

	@FXML
	private Pane paneArea;

	@FXML
	private Pane panePie;

	@FXML
	private PieChart graphPie = new PieChart();

    ArrayList<ChartData> chartDat = new ArrayList<ChartData>();
    
    private Pane displayPane;
    
    @FXML
    private ScrollPane scrollPaneBar;

    /**
     * This function is called when the FXML file is initialized and sets up the view on the screen
     */
    @FXML
    private void initialize()
    {
    	assert graphLine != null : "fx:id=\"graphLine\" was not injected: check your FXML file 'Charts.fxml'.";
        assert graphBar != null : "fx:id=\"graphBar\" was not injected: check your FXML file 'Charts.fxml'.";
        assert paneBar != null : "fx:id=\"paneBar\" was not injected: check your FXML file 'Charts.fxml'.";
        assert paneLine != null : "fx:id=\"PaneLine\" was not injected: check your FXML file 'Charts.fxml'.";

    }
    
    /**
     * This function decides which chart needs to be created and sends the data to that chart
     * @param cd is an ArrayList of type Chart Data to hold the data
     * @param chartType is an Integer of the chart type
     * @param yLabel is the label for the Y-Axis
     * @param xLabel is the label for the X-Axis
     * @param displayPane is the main Stage for the UI
     */
    public void sendData(ArrayList<ChartData> cd, int chartType, String yLabel, String xLabel, Pane displayPane)
    {
    	this.chartDat = cd;
    	this.displayPane = displayPane;
    	switch(chartType)
    	{
    		case 1:
    		{
    			barChart(yLabel,xLabel);
    			break;
    		}
    		case 2:
    		{
    			lineChart(yLabel,xLabel);
    			break;
    		}
    		case 3:
    		{
    			pieChart(yLabel,xLabel);
    			break;
    		}
    	}
    	
    }
    /**
     * This function creates a Line Chart 
     * @param yLabel is the Y-axis Label
     * @param xLabel is the X-axis Label
     */
	private void lineChart(String yLabel, String xLabel)
	{
		// TODO Auto-generated method stub
		xLAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		graphLine.setTitle(yLabel+" per "+xLabel);
		
		ArrayList<XYChart.Series<Number, Number>> seriesMain = new ArrayList<XYChart.Series<Number,Number>>();
		String name="";
		int i=-1;
		System.out.println("Here");
		for(ChartData cd: chartDat)
		{
			if(!name.equals(cd.getName()))
			{
				ArrayList<XYChart.Data<Number, Number>> dataList= new ArrayList<XYChart.Data<Number,Number>>();
				i++;
				System.out.println("Here2");
				name = cd.getName();
				seriesMain.add(new XYChart.Series<Number, Number>());
				seriesMain.get(i).setName(name);
				seriesMain.get(i).getData().addAll(FXCollections.observableArrayList(dataList));
				System.out.println("Here3");
			}
			seriesMain.get(i).getData().add(new XYChart.Data<>(cd.getValue(),cd.getSecondValue()));
			System.out.println("Here4");
		}
		System.out.println("Here5");
		graphLine.getData().addAll(FXCollections.observableArrayList(seriesMain));
		System.out.println(graphLine.getData().toString());
		displayPane.getChildren().removeAll(displayPane.getChildren());
		displayPane.getChildren().add(this.paneLine);
		System.out.println("Here6");
	}
	
	/**
     * This function creates a Pie Chart 
     * @param yLabel is the Y-axis Label
     * @param xLabel is the X-axis Label
     */
	private void pieChart(String yLabel, String xLabel) 
	{
		// TODO Auto-generated method stub
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		graphPie.setTitle(yLabel+" per "+xLabel);
		
		if(chartDat.get(0).getSecondValue()==0)
		{
			ObservableList<PieChart.Data> seriesMain = FXCollections.observableArrayList();
			for(ChartData cd: chartDat)
			{
				String name = cd.getName();
				double num = cd.getValue();
				seriesMain.add(new PieChart.Data(name,num));
			}
			graphPie.getData().addAll(FXCollections.observableArrayList(seriesMain));
		}
		displayPane.getChildren().removeAll(displayPane.getChildren());
		displayPane.getChildren().add(this.panePie);
	}

	/**
     * This function creates a Bar Chart 
     * @param yLabel is the Y-axis Label
     * @param xLabel is the X-axis Label
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void barChart(String yLabel, String xLabel) 
	{
		// TODO Auto-generated method stub
		xAxis.setLabel(xLabel);
		yAxis.setLabel(yLabel);
		graphBar.setTitle(yLabel+" per "+xLabel);
		
		if(chartDat.get(0).getSecondValue()==0)
		{
			ArrayList<XYChart.Data<String,Number>> seriesMain = new ArrayList<XYChart.Data<String,Number>>();
			for(ChartData cd: chartDat)
			{
				String name = cd.getName();
				double num = cd.getValue();
				seriesMain.add(new XYChart.Data(name,num));
			}
			XYChart.Series<String, Number> series1 = new XYChart.Series<>();
			series1.getData().addAll(FXCollections.observableArrayList(seriesMain));
			graphBar.getData().addAll(series1);
			graphBar.setLegendVisible(false);
		}
		displayPane.getChildren().removeAll(displayPane.getChildren());
		displayPane.getChildren().add(this.paneBar);
	}
}
	
