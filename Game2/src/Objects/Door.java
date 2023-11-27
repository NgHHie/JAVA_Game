package Objects;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;
import entity.Entity;

public class Door extends SuperObject{
	private boolean win;
	private int cnt, tang, giam;
	private BufferedImage image0, image1, image2, image3, image4, image5;
	
	public Door(GamePanel gp) {
		super(gp);
		this.win = false;
		this.cnt = 0;
		this.name = "Door";
		this.tang = 1;
		this.giam = 0;
		try {
			this.image0 = ImageIO.read(getClass().getResourceAsStream("/object/doorlock1.png"));

		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			this.image1 = ImageIO.read(getClass().getResourceAsStream("/object/doorlock2.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.solidArea = new Rectangle(0, 0, 74, 96);
		this.solidAreaDefaultX = this.solidArea.x;
		this.solidAreaDefaultY = this.solidArea.y;
	}
	public void update(){		
		this.win = checkCollisionPlayer();
		if(this.win && gp.checkSound[3]) {
			gp.checkSound[3] = false;
			gp.playSE(3, 3);
		}
		
		if(cnt < 10)
			this.image = image0;
		else if(cnt < 20)
			this.image = image0;
		else if(cnt < 30)
			this.image = image1;
		else if(cnt < 40)
			this.image = image1;
		else if(cnt < 50)
			this.image = image1;
		else if(cnt < 60)
			this.image = image1;

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
	
	public void setWinStatus(boolean win) {
		this.win = win;
	}
	
	public boolean getWinStatus() {
		return this.win;
	}
	
	private boolean checkCollisionPlayer() {

		if(gp.player != null ) {
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
