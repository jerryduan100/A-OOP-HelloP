package dungeonshooter;

import java.util.ArrayList;
import java.util.List;

import dungeonshooter.animator.Animator;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.PolyShape;
import dungeonshooter.entity.property.HitBox;
import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

public class CanvasMap {
	/**
	 * <p>
	 * create a {@link Canvas} object call board. it provides the tools to draw in
	 * JavaFX. this is also a {@link Node} which means can be added to our JavaFX
	 * application. the object needed to draw on a {@link Canvas} is
	 * {@link GraphicsContext} which is retrieved using
	 * {@link Canvas#getGraphicsContext2D()} method.
	 * </p>
	 */
	private Canvas map;

	/**
	 * create a {@link AbstractAnimator} called animator. {@link AnimationTimer}
	 * provides most common functionally needed to draw animations of ray casting.
	 */
	private Animator animator;
	private List<PolyShape> staticShapes;

	/**
	 * <p>
	 * create an {@link IntegerProperty} called rayCount to keep track of ray count
	 * changes.<br>
	 * this variable can be initialized with {@link SimpleIntegerProperty}
	 * </p>
	 *
	 * <pre>
	 * IntegerProperty i1 = new SimpleIntegerProperty(1);
	 * IntegerProperty i2 = new SimpleIntegerProperty();
	 * i1.bind(i2);
	 * i2.set(100);
	 * System.out.println(i1.get()); // prints 100
	 * </pre>
	 * <p>
	 * create a getter that returns {@link IntegerProperty and a method that returns
	 * {@link IntegerProperty#get()}
	 * </p>
	 */

	/**
	 * <p>
	 * create a set of {@link BooleanProperty}s to track some drawing options.<br>
	 * create: drawLightSource, drawIntersectPoint, drawShapeJoints, drawSectors,
	 * drawBounds, drawFPS<br>
	 * these variables can be initialized with {@link SimpleBooleanProperty}
	 * </p>
	 *
	 * <pre>
	 * BooleanProperty b1 = new SimpleBooleanProperty(false);
	 * BooleanProperty b2 = new SimpleBooleanProperty();
	 * b1.bind(b2);
	 * b2.set(true);
	 * System.out.println(b1.get()); // prints true
	 * </pre>
	 * <p>
	 * create a getter that returns {@link BooleanProperty and a method that returns
	 * {@link BooleanProperty#get()} for each BooleanProperty.
	 * </p>
	 */
	private BooleanProperty drawBounds;
	private BooleanProperty drawFPS;
	private List<Entity> players;
	private List<Entity> projectiles;
	private PolyShape border;
	private List<Entity> buffer;

	/**
	 * create a constructor and initialize all class variables.
	 */
	public CanvasMap() {
		buffer = new ArrayList<>(500);
		projectiles = new ArrayList<>(500);
		staticShapes = new ArrayList<>(50);
		players = new ArrayList<>(1);
		drawBounds = new SimpleBooleanProperty();
		drawFPS = new SimpleBooleanProperty();
		border = new PolyShape();
		border.getDrawable().setFill(new ImagePattern(new Image("file:assets/floor/pavin.png"), 0, 0, 256, 256, false));
	}

	//
//	    public CanvasMap() {
//	        this.map = new Canvas();
//	        this.animator = new Animator();
//	        this.staticShapes = new ArrayList<>();
//	        this.players = new ArrayList<>();
//	        this.projectiles = new ArrayList<>(500);
//	        this.border = new PolyShape();
//	        this.buffer = new ArrayList<>(500);
	//
//	    }

	/**
	 * create the property class variables functions here
	 */
	public BooleanProperty drawFPSProperty() {
		return drawFPS;
	}

	public boolean getDrawFPS() {
		return drawFPS.get();
	}

	public BooleanProperty drawBoundsProperty() {
		return drawBounds;
	}

	public boolean getDrawBounds() {
		return drawBounds.get();
	}

	public CanvasMap setDrawingCanvas(Canvas map) {
		if (map == null)
			throw new NullPointerException("Null pointer exception");

		this.map = map;

		ChangeListener<Number> change = (v, o, n) -> {
			border.setPoints(0, 0, w(), 0, w(), h(), 0, h());
		};

		this.map.widthProperty().addListener(change);

		this.map.heightProperty().addListener(change);

		return this;
	}

	/*
	 * public CanvasMap setAnimator(Animator newAnimator) { if(animator != null) {
	 * this.stop(); this.removeMouseEvents(); } animator = newAnimator;
	 * this.start(); this.registerMouseEvents(); return this; }
	 */

	/**
	 * create a method called start. start the animator.
	 * {@link AnimationTimer#start()}
	 */
	public void start() {
		animator.start();

	}

	/**
	 * create a method called stop. stop the animator. {@link AnimationTimer#stop()}
	 */
	public void stop() {
		animator.stop();
	}

	/**
	 * create a method called getCanvas. get the JavaFX {@link Canvas} node
	 *
	 * @return {@link Canvas} node
	 */
	public Canvas getCanvas() {
		return map;
	}

	/**
	 * create a method called gc. get the {@link GraphicsContext} of {@link Canvas}
	 * that allows direct drawing.
	 *
	 * @return {@link GraphicsContext} of {@link Canvas}
	 */
	public GraphicsContext gc() {
		return map.getGraphicsContext2D();
	}

	/**
	 * create a method called h. get the height of the map,
	 * {@link Canvas#getHeight()}
	 *
	 * @return height of canvas
	 */
	public double h() {
		return map.getHeight();
	}

	/**
	 * create a method called w. get the width of the map, {@link Canvas#getWidth()}
	 *
	 * @return width of canvas
	 */
	public double w() {
		return map.getWidth();
	}

	// shapes ArrayList method, return type shapes
	public List<PolyShape> staticShapes() {
		return staticShapes;

	}

	/*
	 * public List<Entity> players(){
	 *
	 * }
	 *
	 * public List<Entity> projectiles(){
	 *
	 * }
	 */

	// addSampleShapes method, creating 4 sample shapes with specified coordinators
	// as well as customized parameters, fill, width and stroke
	public CanvasMap addSampleShapes() {

		PolyShape polyShape1 = new PolyShape();
		polyShape1.setPoints(100, 100, 100, 130, 140, 130);
		/*
		 * polyShape1.setStroke(Color.BLACK); polyShape1.setFill(Color.BLUE);
		 * polyShape1.setWidth(0);
		 */

		PolyShape polyShape2 = new PolyShape();
		polyShape2.setPoints(new double[] { 500, 200, 600, 200, 620, 450, 470, 480 });
		/*
		 * polyShape2.setStroke(Color.BLACK); polyShape2.setFill(Color.BLUEVIOLET);
		 * polyShape2.setWidth(0);
		 */

		PolyShape polyShape3 = new PolyShape();
		polyShape3.setPoints(new double[] { 300, 300, 200, 400, 400, 450 });
		/*
		 * polyShape3.setStroke(Color.BLACK); polyShape3.setFill(Color.RED);
		 * polyShape3.setWidth(0);
		 */

		PolyShape polyShape4 = new PolyShape();
		polyShape4.setPoints(new double[] { 150, 100, 450, 20, 350, 150, 400, 250 });
		/*
		 * polyShape4.setStroke(Color.BLACK); polyShape4.setFill(Color.RED);
		 * polyShape4.setWidth(0);
		 */

		// add four sample shapes in the shapes ArrayList
		staticShapes.add(polyShape1);
		staticShapes.add(polyShape2);
		staticShapes.add(polyShape3);
		staticShapes.add(polyShape4);

		return this;
		// hapes.add(new PolyShape().randomize(100, 100, 200, 300, 400));

	}
	/*
	 * public void fireBullet(Bullet bullet) {
	 *
	 * }
	 */

	public void updateProjectilesList() {

	}

	public PolyShape getMapShape() {
		return border;
	}

	public boolean inMap(HitBox hitbox) {

		return border.getHitBox().containsBounds(hitbox);
	}

	public void setAnimator(Animator animator) {
		if(animator==null)
			throw new NullPointerException("animator cannot be null");
		this.animator = animator;
	}
}
