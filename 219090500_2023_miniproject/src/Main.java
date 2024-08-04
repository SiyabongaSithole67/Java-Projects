
import Userinterface.Pane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	
	private Pane pane; //declaring the user interface

	public static void main(String[] args) {
	
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		pane = new Pane();//creating an instance of the interface
		primaryStage.setTitle("Socioeconomic Problem Solved");//setting the header of the interface
		Scene scene = new Scene(pane,500,500);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}