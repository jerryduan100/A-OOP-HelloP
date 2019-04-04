package dungeonshooter.entity;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import utility.Point;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;

/**
 * this is a simple frames-per-seond (FPS) counting utility class
 * 
 * @author 
 * @version March 15, 2019
 */

public class FpsCounter implements Entity {

    public double ONE_SECOND = 1000000000L;
    public double HALF_SECOND = ONE_SECOND / 2F;

    private Font fpsFont;
    private String fpsDisplay;
    private int frameCount;
    private double lastTime;
    private Sprite sprite;
    private double x, y;

    // constructor
    public FpsCounter(double x, double y) {
        setPos(x, y);
        setFont(Font.font(Font.getDefault().getFamily(), FontWeight.BLACK, 24));
        sprite = new Sprite() {
        	public void draw(GraphicsContext gc) {
        		Font font = gc.getFont();
                gc.setFont(fpsFont);
                gc.setFill(getFill());
                gc.fillText(fpsDisplay, x, y);
                gc.setStroke(getStroke());
                gc.setLineWidth(getWidth());
                gc.strokeText(fpsDisplay, x, y);
                gc.setFont(font);
        	}
        };
    }


    //calculateFPS method to update the frame count
    public void calculateFPS(long now) {
        if ((now - lastTime) > HALF_SECOND) {
            fpsDisplay = String.valueOf(frameCount * 2);
            frameCount = 0;
            lastTime = now;
        }
        frameCount++;

    }

    public FpsCounter setFont(Font font) {
        fpsFont = font;
        return this;
    }

    public FpsCounter setPos(double x, double y) {
        this.x = x;
        this.y = y;

        return this;
    }


    @Override
    public void update() {
        // TODO Auto-generated method stub

    }


    @Override
    public boolean hashHitbox() {
        // TODO Auto-generated method stub
        return false;
    }


    @Override
    public Sprite getDrawable() {
        return sprite;

    }


    @Override
    public boolean isDrawable() {
        // TODO Auto-generated method stub
        return true;
    }


    @Override
    public HitBox getHitBox() {
        // TODO Auto-generated method stub
        return null;
    }





}
