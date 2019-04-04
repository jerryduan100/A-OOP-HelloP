//http://tutorials.jenkov.com/javafx/menubutton.html

package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

import java.io.FileInputStream;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		

        primaryStage.setTitle("ImageView Experiment 1");


        MenuItem menuItem1 = new MenuItem("Option 1");
        MenuItem menuItem2 = new MenuItem("Option 2");
        MenuItem menuItem3 = new MenuItem("Option 3");

        menuItem3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Option 3 selected");
            }
        });
        
        menuItem2.setOnAction(event -> {
            System.out.println("Option 2 selected via Lambda");
        });
        
        MenuButton menuButton = new MenuButton("Options", null, menuItem1, menuItem2, menuItem3);

        HBox hbox = new HBox(menuButton);

        Scene scene = new Scene(hbox, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
