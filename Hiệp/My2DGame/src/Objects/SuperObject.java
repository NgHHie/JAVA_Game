package Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.LayoutStyle;
import javax.swing.plaf.basic.BasicComboBoxUI.KeyHandler;

import MainGame.GamePanel;
import entity.Entity;
import entity.Player;

public class SuperObject {
	
	public BufferedImage image;
	public String name;
	public boolean collision = false;
	public boolean slideOn = false;
	public int worldX, worldY;
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX = 0;
	public int solidAreaDefaultY = 0;
	public float speedobj;
	public String direction;
	public String directionY;
	public String pastdirection = "";
	KeyHandler keyH;
	
	public boolean collisionOn, collisionOnY, onGround, inAir;
	public float speedY;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX ;
		int screenY = worldY ;
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	public void update(GamePanel gp) {
		int k = gp.sttobj;
		
		if(gp.obj[k].speedY < 0) {
			gp.obj[k].directionY = "up";
		}
		else if(gp.obj[k].speedY > 0) gp.obj[k].directionY = "down";
		gp.obj[k].collisionOn = false;
		gp.obj[k].collisionOnY = false;
		gp.cChecker.checkTileObject(gp.obj[k]);

		//IF COLLISION IS FALSE, PLAYER CAN MOVE
		if(gp.obj[k].collisionOn == false) {
			switch(gp.obj[k].direction) {
			case "left":
				gp.obj[k].worldX -= (int)gp.obj[k].speedobj;
				break;
			case "right":
				gp.obj[k].worldX += gp.obj[k].speedobj;
				break;
			}
		}
		if(gp.obj[k].collisionOnY == false) {
			gp.obj[k].inAir = true;
			switch(gp.obj[k].directionY) {
			case "up":
				gp.obj[k].worldY += (int)gp.obj[k].speedY;
				break;
			case "down":
				gp.obj[k].worldY += (int)gp.obj[k].speedY;
				break;
			}
		}
		
		if(gp.obj[k].inAir) {
			gp.obj[k].speedY += 1;
		}
	
		if(gp.player != null) {
			if(gp.obj[k].inAir == true) {
				if(gp.player.worldY >= gp.obj[k].worldY
						&& gp.player.worldY <= gp.obj[k].worldY + 16) {
					if(gp.player.worldX >= gp.obj[k].worldX - 40
							&& gp.player.worldX <= gp.obj[k].worldX + 40) {
						gp.player.worldY = gp.obj[k].worldY + 33;	
						gp.player.speedY = gp.obj[k].speedY;
						gp.player.directionY = "down";
						if(gp.player.onGround == true) {
							gp.player = null;
						}
					}
				}
			}
		}
		if(gp.player != null) {
			if(gp.player.worldY >= gp.obj[k].worldY
					&& gp.player.worldY <= gp.obj[k].worldY + 16) {
				if(gp.player.worldX >= gp.obj[k].worldX
						&& gp.player.worldX <= gp.obj[k].worldX + 40
						&& (gp.player.direction.compareTo("left") == 0
						|| gp.player.direction == ""))
					gp.player.worldX = gp.obj[k].worldX + 41;
				else if(gp.player.worldX <= gp.obj[k].worldX
						&& gp.player.worldX >= gp.obj[k].worldX - 41
						&& (
							gp.player.direction.compareTo("right") == 0 ||
						gp.player.direction == ""))
					gp.player.worldX = gp.obj[k].worldX - 40;	
			}
		}

		for(int i=0; i<5; i++) {
			if(gp.clone[i] != null) {
				if(gp.obj[k].inAir == true) {
					if(gp.clone[i].worldY >= gp.obj[k].worldY
							&& gp.clone[i].worldY <= gp.obj[k].worldY + 16) {
						if(gp.clone[i].worldX >= gp.obj[k].worldX - 40
								&& gp.clone[i].worldX <= gp.obj[k].worldX + 40) {
							gp.clone[i].worldY = gp.obj[k].worldY + 33;	
							gp.clone[i].speedY = gp.obj[k].speedY;
							gp.clone[i].directionY = "down";
							System.out.println(gp.clone[i].onGround);
							if(gp.clone[i].onGround == true) {
								gp.clone[i] = null;
							}
						}
					}
				}
			}
			if(gp.clone[i] != null) {
				if(gp.clone[i].worldY >= gp.obj[k].worldY
						&& gp.clone[i].worldY <= gp.obj[k].worldY + 16) {
					if(gp.clone[i].worldX >= gp.obj[k].worldX
							&& gp.clone[i].worldX <= gp.obj[k].worldX + 40
							&& (gp.clone[i].direction.compareTo("left") == 0
							|| gp.clone[i].direction == "")) {
						gp.clone[i].worldX = gp.obj[k].worldX + 41;
					}
					else if(gp.clone[i].worldX <= gp.obj[k].worldX
							&& gp.clone[i].worldX >= gp.obj[k].worldX - 40
							&& (gp.clone[i].direction.compareTo("right") == 0
							|| gp.clone[i].direction == "")) {
						gp.clone[i].worldX = gp.obj[k].worldX - 41;
					}		
				}
			}
		}
		

		for(int i=0; i<5; i++) {
			if(gp.obj[i] != null && i != k) {
				if(gp.obj[i].worldY > gp.obj[k].worldY - 48
						&& gp.obj[i].worldY < gp.obj[k].worldY + 48) {
					if(gp.obj[i].worldX > gp.obj[k].worldX
							&& gp.obj[i].worldX < gp.obj[k].worldX + 48) {
						gp.obj[i].worldX = gp.obj[k].worldX + 48;
					}
					else if(gp.obj[i].worldX < gp.obj[k].worldX
							&& gp.obj[i].worldX > gp.obj[k].worldX - 48) {
						gp.obj[i].worldX = gp.obj[k].worldX - 48;
					}	
				}
			}
		}
		

		
	}

}
