//http://tutorials.jenkov.com/javafx/button.html

package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
	        primaryStage.setTitle("HBox Experiment 1");

	        Button button1 = new Button("My Button");
	        //button1.setMaxSize(100, 50);
	        //button1.setText("Click me if you dare!");
	        Button button2 = new Button("Button 2");
	        Button button3 = new Button("Button 3");
	        Button button4 = new Button("Button 4");
	        
	        button1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent actionEvent) {
	                //... do something in here.
	                System.out.println("button 1 is selected");
	            }
	        });

	        HBox hbox = new HBox(button1,button2,button3,button4);
	        Scene scene = new Scene(hbox, 800, 400);
	        primaryStage.setScene(scene);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
