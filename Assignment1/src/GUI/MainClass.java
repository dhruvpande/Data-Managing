package GUI;
import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author dhruv
 *The main class to run everything
 */

public class MainClass extends Application{

	@SuppressWarnings("deprecation")
	public void start(Stage primaryStage) throws Exception {
		
    	URL fxmlUrl = new File("src/GUI/Page3.fxml").toURL();
    	Pane mainPane = FXMLLoader.<Pane>load(fxmlUrl);
        primaryStage.setTitle("Assignment");
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();        
    }
	
	/**
	 * Main method just calls other class methods and moves data around
	 * @param args
	 */
    public static void main(String[] args) 
	{
		Application.launch(args);
	}

}
