
package dungeonshooter.entity.property;

import dungeonshooter.entity.Entity; 
import dungeonshooter.entity.geometry.RectangleBounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import utility.Point;

public class HitBox implements Entity {

	private Point prev;
	private RectangleBounds bounds;
	private Sprite sprite;
	private double[][] points;
	private double[] results;


	public HitBox() {
		points = new double[2][4];
		results = new double[4];
		prev = new Point();
		sprite = new Sprite(){ 

			public void draw( GraphicsContext gc){ 
				gc.setStroke( getStroke()); 
				gc.setLineWidth( getWidth()); 
				gc.strokeRect( bounds.x(), bounds.y(), bounds.w(), bounds.h());
				} };
				
				
				sprite.setStroke( Color.RED).setWidth( 3);
	}

	public HitBox setBounds(RectangleBounds bounds) {
		this.bounds = bounds;
		return this;

	}
	public HitBox setBounds(double x, double y, double w, double h) {
		bounds = new RectangleBounds(x, y, w, h);
		this.setBounds(bounds);
		return this;

	}



	public HitBox translate(double dx, double dy) {

		this.bounds.translate(dx, dy);
		prev.move(bounds.startPos());
		return this;
	}

	public HitBox undoTranslate() {
		bounds.move(prev);
		return this;
	}

	public boolean containsBounds(HitBox box) {
		return bounds.contains(box.getBounds());

	}

	public boolean intersectBounds(HitBox box) {
		return bounds.intersects(box.getBounds());
	}


	public boolean intersectFull(HitBox box) {
		this.intersectFull(box.getPoints());
		return true;
	}

	protected boolean intersectFull(double[][] otherPoints) {
		return true;
	}

	protected boolean hasIntersectFull() {
		return true;
	}

	protected double[][] getPoints() {
		// TODO Auto-generated method stub
		return bounds.toArray(points);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean hashHitbox() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public HitBox getHitBox() { // TODO Auto-generated method stub
		return this;
	}
	@Override
	public boolean isDrawable() { // TODO Auto-generated method stub
		return true;
	}



	public Sprite getDrawable() {
		return sprite;
	}



	public String toString() {
		return null;
	}

	public RectangleBounds getBounds() {
		return bounds;
	}

	public Point getPrev() {
		return prev;
	}




}
