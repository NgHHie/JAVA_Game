package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import entity.Entity;

public class Button extends SuperObject{
	protected boolean put;
	
	public Button(GamePanel gp) {
		super(gp);
		this.put = false;
		this.name = "Button";
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/object/button.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 0, 48, 48);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
	public void update() {
		this.put = checkCollisionPlayer();
//		checkBox();
	}
	
	private boolean checkCollisionPlayer() {
		if(gp.player != null) {
			 this.collision = checkCollision(gp.player);
			 if(this.collision == true) return true;
		}
		for(int i=0; i<5; i++) {
			if(gp.clone[i] != null) {
				 this.collision = checkCollision(gp.clone[i]);
				 if(this.collision == true) return true;
			}
		}
		this.collision = false;
		return false;
	}
	
	private boolean checkCollision(Entity entity) {
		 gp.setSolid(entity, this);
		 
		 if(entity.solidArea.intersects(this.solidArea)) {
			 this.collision = true;
		 }
		 else this.collision = false;
		 
		 gp.setDefaultSolid(entity, this);
 
		 return this.collision;
	}
}
