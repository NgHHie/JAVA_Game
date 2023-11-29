package Objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import entity.Entity;

public class Button extends SuperObject{
	protected boolean put;
	private boolean checkSound  = false;
	private BufferedImage img1 , img2;
	
	public Button(GamePanel gp) {
		super(gp);
		this.put = false;
		this.name = "Button";
		
		
		try {
			img1 = ImageIO.read(getClass().getResourceAsStream("/object/press1.png"));
			img2 = ImageIO.read(getClass().getResourceAsStream("/object/press2.png"));
//			this.image = ImageIO.read(getClass().getResourceAsStream("/object/press1.png"));
			this.image = img1;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 0, 48, 50);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
	public void update() {
		this.put = checkCollisionPlayer();
		if(this.put == true) {
			this.image = img2;
		}
		else {
			this.image = img1;
		}
//		checkBox();
	}
	
	private boolean checkCollisionPlayer() {
		if(gp.player != null) {
			 this.collision = checkCollision(gp.player);
			 if(this.collision == true) {
					 if(checkSound == false) {
						 if(gp.soundOn) {
							 gp.playSE(4, 1);
							 checkSound = true;
						 }
					 }
				 return true;
			 }
			
		}
	
		
		for(int i=0; i<5; i++) {
			if(gp.clone[i] != null) {
				 this.collision = checkCollision(gp.clone[i]);
				 if(this.collision == true) {
					 if(checkSound == false) {
						 gp.playSE(4, 1);
						 checkSound = true;
					 }
					 
					 return true;
				 }
				 
			}
		}
		this.collision = false;
		if(checkSound == true) {
			checkSound = false;
			gp.stopSoundEffect(4);
		}
		
		
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
