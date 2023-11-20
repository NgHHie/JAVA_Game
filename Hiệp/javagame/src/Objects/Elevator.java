package Objects;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import MainGame.GamePanel;

public class Elevator extends SuperObject{
	GamePanel gp;
	public boolean on;
	public int maxHeight;
	public int minHeight;
	public int maxWidth;
	public int minWidth;
	private int speed;
	public boolean horizontal; //ngang
	public boolean vertical; //doc
	
	public Elevator(GamePanel gp) {
		this.name = "Elevator";
		this.gp = gp;
		this.on = false;
		this.speed = 1;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/object/elevator.png"));
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		solidArea = new Rectangle();
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width = 48;
		solidArea.height = 24;
	}
	
	public void update() {
		this.minHeight = this.defaultWorldY;
		this.maxHeight = this.defaultWorldY - 3 * gp.tileSize;
		this.minWidth = this.defaultWorldX;
		this.maxWidth = this.defaultWorldX + 3 * gp.tileSize;
		Button tmp = (Button) gp.obj[2];
		if(tmp.put == true) {
			if(this.vertical == true && this.worldY > this.maxHeight) {
				this.worldY -= this.speed;
				
				if(gp.player != null) {
					 gp.setSolid(gp.player, this);
					 if(gp.player.solidArea.intersects(this.solidArea)) {
						 gp.player.worldY -= this.speed;
					 }
					 gp.setDefaultSolid(gp.player, this); 
				}
				for(int i=0; i<5; i++) {
					if(gp.clone[i] != null) {
						 gp.setSolid(gp.clone[i], this);
						 if(gp.clone[i].solidArea.intersects(this.solidArea)) {
							 gp.clone[i].worldY -= this.speed;
						 }
						 gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}
			if(this.horizontal == true && this.worldX < this.maxWidth) {
				this.worldX += this.speed;
				
				if(gp.player != null) {
					gp.setSolid(gp.player, this);
					gp.player.solidArea.y += 5;
					 
					 if(gp.player.solidArea.intersects(this.solidArea)) {
						 gp.player.worldX += this.speed;
					 }
					 gp.setDefaultSolid(gp.player, this);
				}
				for(int i=0; i<5; i++) {
					if(gp.clone[i] != null) {
						gp.setSolid(gp.clone[i], this);
						gp.clone[i].solidArea.y += 5;

						 if(gp.clone[i].solidArea.intersects(this.solidArea)) {
							 gp.clone[i].worldX += this.speed;
						 }
						 gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}
		}
		else {
			if(this.vertical == true && this.worldY < this.minHeight) 
				this.worldY += this.speed;
			else if(this.horizontal == true && this.worldX > this.minWidth) {
				this.worldX -= this.speed;
				if(gp.player != null) {
					gp.setSolid(gp.player, this);
					gp.player.solidArea.y += 5;
					 
					 if(gp.player.solidArea.intersects(this.solidArea)) {
						 gp.player.worldX -= this.speed;
					 }
					 gp.setDefaultSolid(gp.player, this);
				}
				for(int i=0; i<5; i++) {
					if(gp.clone[i] != null) {
						gp.setSolid(gp.clone[i], this);
						gp.clone[i].solidArea.y += 5;
						 
						 if(gp.clone[i].solidArea.intersects(this.solidArea)) {
							 gp.clone[i].worldX -= this.speed;
						 }
						 gp.setDefaultSolid(gp.clone[i], this);
					}
				}
			}

		}
	}
	

}
