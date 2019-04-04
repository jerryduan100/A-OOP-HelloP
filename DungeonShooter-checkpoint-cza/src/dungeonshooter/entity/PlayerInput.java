package dungeonshooter.entity;

import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import utility.InputAdapter;

public class PlayerInput extends InputAdapter<Canvas>{
	
	private double x,y;
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;
	private boolean up = false;
	private boolean leftClick, rightClick, middleClick = false;
	
	//private boolean <Property> space / shift = false;
	
	public boolean hasMoved() {
		return true;
	}
	
	public int leftOrRight() {
		return 0;
	}
	
	public int upOrDown() {
		return 0;
	}
	
	public boolean leftClicked() {
		return true;
	}
	
	public boolean rightClicked() {
		return true;
	}
	
	public boolean middleClicked() {
		return true;
	}
	public double x() {
		return x;
	}
	
	public double y() {
		return y;
	}
	
	protected void mousePressed(MouseEvent e) {
		
	}
	
	protected void mouseReleased(MouseEvent e) {
		
	}
	
	public void changeKeyStatus(KeyCode key, boolean isPressed) {
		
	}
	
	protected void keyPressed(KeyEvent key) {
		
	}
	
	protected void keyRealeased(KeyEvent key) {
		
	}
	
	protected void moved(double x, double y) {
		
	}
	
	protected void dragged(double x, double y) {
		
	}
	
	public boolean isSpace() {
		return true;
	}
	public boolean isShift() {
		return true;
	}
	
}
