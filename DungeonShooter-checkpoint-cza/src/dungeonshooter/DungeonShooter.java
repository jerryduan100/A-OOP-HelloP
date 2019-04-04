package dungeonshooter;


import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.ToolBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import dungeonshooter.animator.AbstractAnimator;
import dungeonshooter.animator.Animator;
//import dungeonshooter.entity.Player;
import dungeonshooter.entity.PlayerInput;

import java.util.ArrayList;

import dungeonshooter.CanvasMap;
import javafx.application.Application;

public class DungeonShooter extends Application {

    private double width = 700;
    private double height = 700;
    private Canvas canvas;
    private CanvasMap board;
    private BorderPane root;
    private PlayerInput input;

    private ObservableList<AbstractAnimator> animators;
    private ChoiceBox<AbstractAnimator> animatorsBox;

    @Override
    public void init() throws Exception {

        canvas = new Canvas();
        PlayerInput input = new PlayerInput();
        input.forceFocusWhenMouseEnters(canvas);
        input.registerMouseMovment(canvas);
        input.registerMouseClick(canvas);
        input.registerKey(canvas);

        board = new CanvasMap();

        //Player player = new Player();

        //player.setInput(Input);
        //player.setMap(board);

        Animator animator = new Animator();
        animator.setCanvas(board);
        board.setDrawingCanvas(canvas);
        board.setAnimator(animator);
        board.addSampleShapes();
//        board.players();
//
//
////        ArrayList<Player> players = new ArrayList<Player>();
////        players.add(player);
//
//
        //create two ToolBar objects and store createStatusBar() and createOptionsBar() in each
        ToolBar statusBar = createStatusBar();
        ToolBar optionsBar = createOptionsBar();
        //initialize root
        root = new BorderPane();
        //call setTop on it and pass to it the options bar
        root.setTop(optionsBar);
        //call setCenter on it and pass to it board.getCanvas()
        root.setCenter(board.getCanvas());
//        //call setBottom on it and pass to it the status bar
        root.setBottom(statusBar);
        //we need to bind the height and width of of canvas to root so if screen is resized board is resized as well.

//        //to bind the width get the canvas from board first then call widthProperty on it and then bind root.widthProperty to it
        board.getCanvas().widthProperty().bind(root.widthProperty());
        //getCanvas() observer, root.widthProperty() observable
////
////        //to bind the height it is almost the same process however the height of options and status bar need to be subtracted from
////
////        //root height. subtract can be done root.heightProperty().subtract( statusBar.heightProperty()).
////        //you also need to subtract optionsBar.heightProperty as well.
        board.getCanvas().heightProperty().bind(root.heightProperty()
                .subtract(statusBar.heightProperty()).subtract(optionsBar.heightProperty()));
        
//
//

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //scene holds all JavaFX components that need to be displayed in Stage
        Scene scene = new Scene(root, width, height, Color.BLUE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dungeon Shooter");

        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                primaryStage.hide();
            }
        });
        primaryStage.show();
        //select first index of animatorsBox as start,
        //this will also sets the new animator as the lambda we setup will be triggered

        //start the application with StaticShapes
//        animatorsBox.getSelectionModel().select(1);
    }

    @Override
    public void stop() throws Exception {
        board.stop();
    }

    public ToolBar createOptionsBar() {
        //use the createButton method and create a start button with lambda that calls board.start()
        Button startButton = createButton("Start", e -> board.start());
        //use the createButton method and create a start button with lambda that calls board.stop()
        Button stopButton = createButton("Stop", e -> board.stop());
        //create 2 Pane object called filler1 and filler2
        Pane filler1 = new Pane();
//        Pane filler2 = new Pane();
        //Pane class is a super class of all layout mangers. by itself it has no rules.
        //call the static method setHgrow from Hbox with Filler1, Priority.ALWAYS
        HBox.setHgrow(filler1, Priority.ALWAYS);
        ;
        //call the static method setHgrow from Hbox with Filler2, Priority.ALWAYS
//        HBox.setHgrow(filler2, Priority.ALWAYS);
        //this will allow the filler to expand and fill the space between nodes

        //create a Spinner object called rayCount with generic type of Integer
        //in the constructor pass to it 1 as min, Integer.MAX_VALUE as max and 360*3 as current
        Spinner<Integer> rayCount = new Spinner<>(1, Integer.MAX_VALUE, 360 * 3);
        //call setEditable on it and set to true so the counter can be changed by typing in it.
        rayCount.setEditable(true);
        //call setMaxWidth on it and set 100, as default size it too big
        rayCount.setMaxWidth(100);
        //get rayCount property from CanvasMap and bind it to rayCount.valueProperty().
        //board.rayCountProperty().bind(rayCount.valueProperty());
        //rayCount.valueProperty() should be passed as argument

        //create a MenuButton with argument "Options", null and all of created CheckMenuItem.
        //call createCheckBox 6 times and use following names:
        //FPS, Intersects. Lights, Joints, Bounds, Sectors
        //only FPS is selected the rest are false.
        //as last argument get the equivalent property from CanvasMap
        MenuButton mb = new MenuButton("Options", null, createCheckedBox("FPS", true, board.drawFPSProperty()),
                createCheckedBox("Bounds", false, board.drawBoundsProperty()));


        //Initialize animatorsBox with the animators list
//        animatorsBox = new ChoiceBox<>(animators);
        //call getSelectionModel on animatorsBox then call selectedItemProperty and then call addListener.
        //animatorsBox.getSelectionModel().selectedItemProperty().addListener((observable,oldValue,newValue) ->{board.setAnimator(newValue);});
        //finally as argument for addListener pass a lambda that sets the new animator for CanvasMap.
        //this Lambda is called ChangeListener and it takes 3 arguments:
        //ObservableValue<? extends T> observable, it is an object that represent the observable data
        //T oldValue, it is the old value before the change
        //T newValue, it is the new value after the change
        //this lambda will only use the newValue argument which passed to setAnimator of CanvasMap


        // create a new ToolBar and as arguments of its constructor pass the create nodes.
        // there should be 8:
        // startButton, stopButton, filler1, rayCount,
        // options, filler2, new Label( "Animators "), animatorsBox
        // return the created ToolBar

        HBox.setHgrow(filler1, Priority.ALWAYS);


        return new ToolBar(startButton, stopButton, filler1,
                mb);
    }

    /**
     * create a {@link ToolBar} that will represent the status bar of the application.
     *
     * @return customized {@link ToolBar}
     */
    public ToolBar createStatusBar() {
        // create two Label objects and with value of "(0,0)".
        Label mouseCoordLabel = new Label("(0,0)");
        Label dragCoordLabel = new Label("(0,0)");

        // one label keeps track of mouse movement and other mouse drag.

        // call addEventHandler on canvas for MouseEvent.MOUSE_MOVED event and pass a lambda to
        // it that will update the text in one of the labels with the new coordinate of the mouse.
        // the lambda will take one argument of type MouseEvent and two methods
        // getX and getY from MouseEvnet can be used to get position of the mouse
        //board.addEventHandler(MouseEvent.MOUSE_MOVED,e -> mouseCoordLabel.setText("(" + e.getX() + " , " + e.getY() + ")"));
        // call addEventHandler on canvas for MouseEvent.MOUSE_DRAGGED event and pass a lambda to
        // it that will update the text in one of the labels with the new coordinate of the mouse.
        // the lambda will take one argument of type MouseEvent and two methods
        // getX and getY from MouseEvnet can be used to get position of the mouse
        //board.addEventHandler(MouseEvent.MOUSE_DRAGGED,e -> dragCoordLabel.setText("(" + e.getX() + " , " + e.getY() + ")") );
        // create a new ToolBar and as arguments of its constructor pass the create labels to it.
        // there should be 4 labels: new Label( "Mouse: "), mouseCoordLabel, new Label( "Drag: "), dragCoordLabel
        // return the created ToolBar
        ToolBar tb = new ToolBar(new Label("Move: "), mouseCoordLabel, new Label("Drag: "), dragCoordLabel);

        return tb;
    }

    public CheckMenuItem createCheckedBox(String text, boolean isSelected, BooleanProperty binding) {
        // create a new CheckMenuItem object with given text.
        CheckMenuItem button1 = new CheckMenuItem(text);
        // call bind on binding and pass to it check.selectedProperty(),
        binding.bind(button1.selectedProperty());
        // this will notify the binding object every time check.selectedProperty() is changed.
        // call setSelected and pass to it isSelected.
        button1.setSelected(isSelected);
        // return the created CheckMenuItem.
        return button1;
    }

    public Button createButton(String text, EventHandler<ActionEvent> action) {
        // create a new Button object with given text
        Button bt = new Button(text);
        // call setOnAction on created button and give it action
        bt.setOnAction(action);
        // return the created button
        return bt;
    }

    public static void main(String[] args) {
        // launch( args); method starts the javaFX application.
        // some IDEs are capable of starting JavaFX without this method.
        launch(args);
    }

}
