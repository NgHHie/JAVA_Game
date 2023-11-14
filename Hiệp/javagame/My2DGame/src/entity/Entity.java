package entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	public int worldX, worldY;
	public float speed;
	
	public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
	public String direction;
	public String directionY;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	public boolean collisionOn = false;
	public boolean[] collisionOn2 = new boolean[5];
	
	
	public int solidAreaDefaultX, solidAreaDefaultY;
}
