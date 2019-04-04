/**
 * http://tutorials.jenkov.com/javafx/menubutton.html
 */
package yueli.hello;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class MyFxApp extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
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
			System.out.println("Option 2 selected by Lambda!");
		});

		FileInputStream input = new FileInputStream("resources/images/b_3.png");
		Image image = new Image(input);
		ImageView imageView = new ImageView(image);

		MenuButton menuButton = new MenuButton("Options", imageView, menuItem1, menuItem2, menuItem3);

		HBox hbox = new HBox(menuButton);

		Scene scene = new Scene(hbox, 200, 160);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		Application.launch(args);

	}

}
