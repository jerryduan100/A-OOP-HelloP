package dungeonshooter.entity;

import dungeonshooter.entity.property.Drawable;
import dungeonshooter.entity.property.HitBox;
import dungeonshooter.entity.property.Sprite;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import dungeonshooter.entity.geometry.RectangleBounds;

public class PolyShape implements Entity {
    //declare variables
    private int pointCount;
    private double[][] points;
    private double minX;
    private double minY;
    private double maxX;
    private double maxY;
    private HitBox hitbox;
    private Sprite sprite;


    //constructor
    public PolyShape() {
        hitbox = new HitBox() {
            protected boolean hasIntersectFull() {
                return true;
            }

            protected double[][] getPoints() {
                return points;
            }
        };
        sprite = new Sprite() {
            {
                setFill(new ImagePattern(new Image("file:assets/concrete/dsc_1621.png")));
            }

            public void draw(GraphicsContext gc) {
                gc.setLineWidth(getWidth());
                if (getStroke() != null) {
                    gc.setStroke(getStroke());
                    gc.strokePolygon(points[0], points[1], pointCount);
                }
                if (getFill() != null) {
                    gc.setFill(getFill());
                    gc.fillPolygon(points[0], points[1], pointCount);
                }
            }
        };

    }

    public PolyShape randomize(double centerX, double centerY, double size, int minPoints, int maxPoints) {


        /*
         * Point center = new Point(centerX, centerY); pointCount =
         * RandUtil.getInt(minPoints, maxPoints); Point[] tempPoints = new
         * Point[pointCount]; for(int i=0;i<pointCount;i++) { tempPoints[i]
         * =center.randomPoint(size) ; }
         */

        return null;
    }

    //create a polygon defined by 2-D vertices, return a polyshape object
    public PolyShape setPoints(double... nums) {
        minX = maxX = nums[0]; //First X
        minY = maxY = nums[1]; //First Y

        //Initialize points, pointsCount is half of nums.length
        pointCount = nums.length / 2;
        points = new double[2][pointCount];
        for (int i = 0, j = 0; i < nums.length; i += 2, j++) {
            updateMinMax(nums[i], nums[i + 1]);
            points[0][j] = nums[i];
            points[1][j] = nums[i + 1];
        }
        hitbox.setBounds(minX, minY, maxX-minX,maxY-minY );
        return this;
    }

    private void updateMinMax(double x, double y) {
        if (x < minX) {
            minX = x;
        }
        if (x > maxX) {
            maxX = x;
        }
        if (y < minY) {
            minY = y;
        }
        if (y > maxY) {
            maxY = y;
        }

    }

    public int pointCount() {
        return points.length;
    }

    public double[][] points() {
        return points;
    }

    public double pX(int index) {
        return points[0][index];
    }

    public double pY(int index) {
        return points[1][index];
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
    public Sprite getDrawable() {
        // TODO Auto-generated method stub
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
        return hitbox;
    }
		

		
	
}
