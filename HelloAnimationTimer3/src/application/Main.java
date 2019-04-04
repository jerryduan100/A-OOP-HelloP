package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
//			BorderPane root = new BorderPane();
//			Scene scene = new Scene(root,400,400);
//			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//			primaryStage.setScene(scene);
//			primaryStage.show();
			
			
		    primaryStage.setTitle( "Timeline Example" );
		    
		    Group root = new Group();
		    Scene theScene = new Scene( root );
		    primaryStage.setScene( theScene );
		 
		    Canvas canvas = new Canvas( 512, 512 );
		    root.getChildren().add( canvas );
		 
		    GraphicsContext gc = canvas.getGraphicsContext2D();
		 
		    Image earth = new Image( "earth.png" );
		    Image sun   = new Image( "sun.png" );
		    Image space = new Image( "space.png" );
		 
		    final long startNanoTime = System.nanoTime();
		 
		    new AnimationTimer()
		    {
		        public void handle(long currentNanoTime)
		        {
		            double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
		 
		            double x = 232 + 128 * Math.cos(t);
		            double y = 232 + 128 * Math.sin(t);
		 
		            // background image clears canvas
		            gc.drawImage( space, 0, 0 );
		            gc.drawImage( earth, x, y );
		            gc.drawImage( sun, 196, 196 );
		        }
		    }.start();
		 
		    primaryStage.show();			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
