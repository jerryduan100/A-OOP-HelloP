package dungeonshooter.animator;

import java.util.Iterator;

import dungeonshooter.CanvasMap;
import dungeonshooter.entity.Entity;
import dungeonshooter.entity.property.HitBox;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Animator extends AbstractAnimator {
		private Color background = Color.ANTIQUEWHITE;

		@Override
		protected void handle(GraphicsContext gc, long now) {
			// TODO Auto-generated method stub
			this.updateEntities();
			this.clearAndFill(gc, background);
			this.drawEntities(gc);
			
		}
		
		public void updateEntities() {
			CanvasMap map = new CanvasMap();
			map.updateProjectilesList();
			//map.players();
			//map.projectiles();
			map.staticShapes();
			
			map.staticShapes().forEach(m -> m.update());
			
		    //this.processEntityList(bullets.iterator(),staticShape.getHitBox()).forEach();
			//this.preocessEntityList(players.iterator(), staticShape.getHitBox()).forEach();
			
			
		}
		
		public void processEntityList(Iterator<Entity> iterator, HitBox shapeHitBox) {
			while(iterator.hasNext()) {
				iterator.next().getHitBox();
				
			/*
			 * if(!map.inMap(bounds)) {
			 * 
			 * }
			 */
				
				if(map.getDrawBounds()) {
					shapeHitBox.getDrawable().setStroke(Color.BLUEVIOLET);
				}
				
			}
			
			
		}
		
}
