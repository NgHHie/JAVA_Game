package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import entity.Entity;

public class Door extends SuperObject{
	private boolean win;
	private int cnt, tang, giam;
	
	public Door(GamePanel gp) {
		super(gp);
		this.cnt = 0;
		this.name = "Door";
		this.tang = 1;
		this.giam = 0;
		try {
			this.image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		this.solidArea = new Rectangle(0, 0, 96, 96);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
	public void update(){		
		this.win = checkCollisionPlayer();
		try {
			if(cnt < 10)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door.png"));
			else if(cnt < 20)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door1.png"));
			else if(cnt < 30)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door2.png"));
			else if(cnt < 40)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door3.png"));
			else if(cnt < 50)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door4.png"));
			else if(cnt < 60)
				this.image = ImageIO.read(getClass().getResourceAsStream("/object/door5.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}

		if(cnt == 60) {
			tang = 0;
			giam = 1;
		}
		else if(cnt == 0) {
			giam = 0;
			tang = 1;
		}
		if(tang == 1) cnt ++;
		else cnt --;
//		checkBox();
	}
	
	private boolean checkCollisionPlayer() {
		if(gp.player != null) {
			 this.collision = checkCollision(gp.player);
			 if(this.collision == true) return true;
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
