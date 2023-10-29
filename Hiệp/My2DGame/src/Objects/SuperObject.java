package Objects;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

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
	public String pastdirection;
	KeyHandler keyH;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		int screenX = worldX ;
		int screenY = worldY ;
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
	public void update(Player player) {
		System.out.println(slideOn);
		System.out.println(pastdirection);
		if(slideOn == false) return;
		
//		if(player.keyH.upPressed == false && player.keyH.downPressed == false &&
//				player.keyH.leftPressed == false && player.keyH.rightPressed == false) {
//			if(direction == "right" || direction == "left") {
//				spriteNum = 1;
//			}
//			return;
//		}
		
//		if(player.collisionOn2 == true) {
////			pastdirection = "up";
//			speedobj = player.speed;
//			return;
//		}
//		else if(player.collisionOn2 == true) {
////			pastdirection = "down";
//			speedobj = player.speed;
//			return;
//		}
//		else if(player.collisionOn2 == true) {
////			pastdirection = "left";	
//			speedobj = player.speed;
//			return;
//		}
//		else if(player.collisionOn2 == true) {
////			pastdirection = "right";
//			speedobj = player.speed;
//			return;
//		}	
		if(player.collisionOn2 == true && player.direction != "") return;
		float acc = (float) 0.5;

		switch(pastdirection) {
		case "up":
			if(speedobj > 0) {
				worldY -= speedobj;
				speedobj -= acc;
			}
			else {
				slideOn = false;
				pastdirection = "";
			}
			break;
		case "down":
			if(speedobj > 0) {
				worldY += speedobj;
				speedobj -= acc;
			}
			else {
				slideOn = false;
				pastdirection = "";
			}
			break;
		case "left":
			if(speedobj > 0) {
				worldX -= speedobj;
				speedobj -= acc;
			}
			else {
				slideOn = false;
				pastdirection = "";
			}
			break;
		case "right":
			if(speedobj > 0) {
				worldX += speedobj;
				speedobj -= acc;
			}
			else {
				slideOn = false;
				pastdirection = "";
			}
			break;
		}
	}

}
