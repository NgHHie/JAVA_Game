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
	
	public void update() {
//		System.out.println(pastdirection);
//		if(slideOn == false || pastdirection == null || collision == true) return;
//		System.out.println(speedobj);
//
////		if(player.direction != "" && player.collisionOn2 == true) return;
//
////		System.out.println(1);
//		float acc = (float) 0.5;
//
//		switch(pastdirection) {
//		case "up":
//			if(speedobj > 0) {
//				worldY -= speedobj;
//				speedobj -= acc;
//			}
//			else {
//				slideOn = false;
//				pastdirection = "";
//			}
//			break;
//		case "down":
//			if(speedobj > 0) {
//				worldY += speedobj;
//				speedobj -= acc;
//			}
//			else {
//				slideOn = false;
//				pastdirection = "";
//			}
//			break;
//		case "left":
//			if(speedobj > 0) {
//				worldX -= (int)speedobj;
//				speedobj -= acc;
//			}
//			else {
//				slideOn = false;
//				pastdirection = "";
//			}
//			break;
//		case "right":
//			if(speedobj > 0) {
//				worldX += speedobj;
//				speedobj -= acc;
//			}
//			else {
//				slideOn = false;
//				pastdirection = "";
//			}
//			break;
//		}
	}

}
